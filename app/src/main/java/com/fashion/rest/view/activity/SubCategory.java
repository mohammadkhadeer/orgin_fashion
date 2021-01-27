package com.fashion.rest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.view.Adapters.AdapterSubCategorySeeAll;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.fillSubCatArrayL;

public class SubCategory extends AppCompatActivity implements AdapterSubCategorySeeAll.PassSubCategory{

    AdapterSubCategorySeeAll adapterSubCategorySeeAll;
    RecyclerView recyclerView;
    ArrayList<com.fashion.rest.model.SubCategory> subCategoriesArrayL = new ArrayList<>();
    EditText searchEdt;
    RelativeLayout cancelRL;
    ImageView cancelIV;
    String cat_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        statusBarColor();
        getInfoFromCat();
        inti();
        fillEditText();
        createRV();
        actionListenerToSearchEdt();
        actionListenerToRemoveTextInSearchEdt();
    }

    private void fillEditText() {

    }

    private void getInfoFromCat() {
        Bundle bundle = getIntent().getExtras();
        cat_name =bundle.getString("cat_name");
    }

    private void actionListenerToRemoveTextInSearchEdt() {
        cancelRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdt.setText("");
            }
        });
    }

    private void actionListenerToSearchEdt() {
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
                filter(editable.toString());
            }

        });
    }

    private void filter(String text) {
        ArrayList<com.fashion.rest.model.SubCategory> multiAreasArrayList2  = new ArrayList<com.fashion.rest.model.SubCategory>();
        for (com.fashion.rest.model.SubCategory subCategory1 : subCategoriesArrayL) {
            if (subCategory1.getSub_category_en().toLowerCase().contains(text.toLowerCase())) {
                multiAreasArrayList2.add(subCategory1);
            }
        }
        adapterSubCategorySeeAll.filterList(multiAreasArrayList2);
    }

    private void makeCancelTitleIVVISIBLE() {
        cancelIV.setVisibility(View.VISIBLE);
    }

    private void makeCancelTitleIVGONE() {
        cancelIV.setVisibility(View.GONE);
    }

    private void createRV() {
        subCategoriesArrayL = fillSubCatArrayL(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterSubCategorySeeAll = new AdapterSubCategorySeeAll(getApplicationContext(), subCategoriesArrayL,this);
        recyclerView.setAdapter(adapterSubCategorySeeAll);
    }

    private void inti() {
        recyclerView = (RecyclerView) findViewById(R.id.sub_category_popup_RV);
        searchEdt = (EditText) findViewById(R.id.fragment_car_options_searchEdt);
        cancelRL = (RelativeLayout) findViewById(R.id.fragment_car_options_RL);
        cancelIV = (ImageView) findViewById(R.id.fragment_car_options_ImageV);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onClickedSubCategory(com.fashion.rest.model.SubCategory subCategory) {
        moveToResultActivity();
    }

    private void moveToResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }
}