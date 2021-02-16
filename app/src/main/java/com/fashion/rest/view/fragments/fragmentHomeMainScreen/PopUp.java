package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.MultiArea;
import com.fashion.rest.presnter.PassCityAndArea;
import com.fashion.rest.view.Adapters.AdapterMultiArea;

import java.util.ArrayList;
import java.util.Locale;

import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class PopUp extends DialogFragment implements AdapterMultiArea.PassSelected {
    public ArrayList<MultiArea> multiAreasArrayList = new ArrayList<>();
    public ArrayList<MultiArea> multiAreasSelectedArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    AdapterMultiArea adapterMultiArea;
    EditText searchEdt;
    RelativeLayout cancelRL,resetRL,nextRL;
    ImageView cancelIV;

    PassSelectedAreas passSelectedAreas;

    public void showDialog(Activity activity, ArrayList<Area> availableAreas,Context context){
        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_popup_dialog);

        if (context instanceof PassCityAndArea) {
            passSelectedAreas = (PassSelectedAreas) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }

        inti(dialog);
        createRV(availableAreas,context);
        actionListenerToSearchEdt(context);
        actionListenerToRemoveTextInSearchEdt();
        actionListenerToResetAndNextButton(dialog);

        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void actionListenerToSearchEdt(final Context context) {
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
                filter(editable.toString(),context);
            }

        });
    }

    private void actionListenerToResetAndNextButton(final Dialog dialog) {
        resetRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSelectedMultiAreasEmpty();
            }
        });

        nextRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectSelected();
                passSelectedAreas.passSelected(multiAreasSelectedArrayList);
                dialog.dismiss();
            }
        });
    }

    private void detectSelected() {
        multiAreasSelectedArrayList = new ArrayList<>();
        for (int i=0;i<multiAreasArrayList.size();i++)
        {
            if (multiAreasArrayList.get(i).getIsSelected() ==1)
            {
                multiAreasSelectedArrayList.add(multiAreasArrayList.get(i));
            }
        }
    }

    private void makeSelectedMultiAreasEmpty() {
        for (int i = 0; i < multiAreasArrayList.size(); i++) {
            multiAreasArrayList.get(i).setIsSelected(0);
            adapterMultiArea.notifyItemChanged(i);
        }
    }

    private void actionListenerToRemoveTextInSearchEdt() {
        cancelRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdt.setText("");
            }
        });
    }

    private void filter(String text,Context context) {
        ArrayList<MultiArea> multiAreasArrayList2 = new ArrayList<MultiArea>();
        for (MultiArea area : multiAreasArrayList) {
            if (Locale.getDefault().getLanguage().equals("en")) {
                if (area.getArea_en().toLowerCase().contains(text.toLowerCase())) {
                    multiAreasArrayList2.add(area);
                }
            }else {
                if (area.getArea_local().toLowerCase().contains(text.toLowerCase())) {
                    multiAreasArrayList2.add(area);
                }
            }
            adapterMultiArea.filterList(multiAreasArrayList2);
        }
    }

    private void makeCancelTitleIVGONE() {
        cancelIV.setVisibility(View.GONE);
    }

    private void makeCancelTitleIVVISIBLE() {
        cancelIV.setVisibility(View.VISIBLE);
    }

    private void createRV(ArrayList<Area> availableAreas,Context context) {
        multiAreasArrayList =fillAvailableAreas(availableAreas);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterMultiArea = new AdapterMultiArea(context, multiAreasArrayList,this);
        recyclerView.setAdapter(adapterMultiArea);
    }

    private ArrayList<MultiArea> fillAvailableAreas(ArrayList<Area> availableAreas) {
        multiAreasArrayList = new ArrayList<>();
        for (int i=0;i<availableAreas.size();i++)
        {
            MultiArea multiArea = new MultiArea(availableAreas.get(i).getName_en(),availableAreas.get(i).getName_local(),0);
            multiAreasArrayList.add(multiArea);
        }
        return multiAreasArrayList;
    }

    private void inti(Dialog dialog) {
        recyclerView = (RecyclerView) dialog.findViewById(R.id.fragment_car_options_RV);
        searchEdt = (EditText) dialog.findViewById(R.id.fragment_car_options_searchEdt);
        cancelRL = (RelativeLayout) dialog.findViewById(R.id.fragment_car_options_RL);
        resetRL = (RelativeLayout) dialog.findViewById(R.id.fragment_car_options_resetRL);
        nextRL = (RelativeLayout) dialog.findViewById(R.id.fragment_car_options_nextRL);
        cancelIV = (ImageView) dialog.findViewById(R.id.fragment_car_options_ImageV);
        //                dialog.dismiss();
    }

    @Override
    public void onOptionClicked(MultiArea carOption, int position) {
        multiAreasArrayList.get(position).setIsSelected(carOption.getIsSelected());
    }

    public interface PassSelectedAreas{
        void passSelected(ArrayList<MultiArea> multiAreasArrayList);
    }
}
