package com.orgin_fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.model.Sub_Cat;
import com.orgin_fashion.rest.presnter.PassCityAndArea;
import com.orgin_fashion.rest.view.Adapters.AdapterSubCategorySeeAll;
import java.util.ArrayList;
import java.util.Locale;

public class PopUpCategory extends DialogFragment implements AdapterSubCategorySeeAll.PassSubCategory {
    RecyclerView recyclerView;

    AdapterSubCategorySeeAll adapterSubCategorySeeAll;

    EditText searchEdt;
    RelativeLayout cancelRL;
    ImageView cancelIV;

    Dialog dialog;
    PassSelectedSubCategory passSelectedSubCategory;

    public void showDialog(Activity activity, ArrayList<Sub_Cat> subCategory,Context context){
        dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_popup_dialog_category);

        if (context instanceof PassCityAndArea) {
            passSelectedSubCategory = (PassSelectedSubCategory) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }

        inti(dialog);
        createRV(subCategory,context);
        actionListenerToSearchEdt(subCategory);
        actionListenerToRemoveTextInSearchEdt();

        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void actionListenerToSearchEdt(final ArrayList<Sub_Cat> subCategory) {
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() != 0)
                    makeCancelTitleIVVISIBLE();
                else
                    makeCancelTitleIVGONE();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString(),subCategory);
            }

        });
    }

    private void actionListenerToRemoveTextInSearchEdt() {
        cancelRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdt.setText("");
            }
        });
    }

    private void filter(String text,ArrayList<Sub_Cat> subCategory) {
        ArrayList<Sub_Cat> multiAreasArrayList2  = new ArrayList<Sub_Cat>();
        for (Sub_Cat subCategory1 : subCategory) {
            if (Locale.getDefault().getLanguage().equals("en"))
            {
                if (subCategory1.getName_en().toLowerCase().contains(text.toLowerCase())) {
                    multiAreasArrayList2.add(subCategory1);
                }
            }else{
                if (subCategory1.getName_local().toLowerCase().contains(text.toLowerCase())) {
                    multiAreasArrayList2.add(subCategory1);
                }
            }

        }
        adapterSubCategorySeeAll.filterList(multiAreasArrayList2);
    }

    private void makeCancelTitleIVGONE() {
        cancelIV.setVisibility(View.GONE);
    }

    private void makeCancelTitleIVVISIBLE() {
        cancelIV.setVisibility(View.VISIBLE);
    }

    private void createRV(ArrayList<Sub_Cat> subCategoryArrayList,Context context) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterSubCategorySeeAll = new AdapterSubCategorySeeAll(context, subCategoryArrayList,this);
        recyclerView.setAdapter(adapterSubCategorySeeAll);
    }

    private void inti(Dialog dialog) {
        recyclerView = (RecyclerView) dialog.findViewById(R.id.sub_category_popup_RV);
        searchEdt = (EditText) dialog.findViewById(R.id.fragment_car_options_searchEdt);
        cancelRL = (RelativeLayout) dialog.findViewById(R.id.fragment_car_options_RL);
        cancelIV = (ImageView) dialog.findViewById(R.id.fragment_car_options_ImageV);
        //                dialog.dismiss();
    }

    @Override
    public void onClickedSubCategory(Sub_Cat subCategory) {
        passSelectedSubCategory.passSelectedSubCategory(subCategory);
        dialog.dismiss();
    }

    public interface PassSelectedSubCategory{
        void passSelectedSubCategory(Sub_Cat subCategory);
    }
}
