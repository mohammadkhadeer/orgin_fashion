package com.fashion.rest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Sub_Cat;
import com.fashion.rest.view.Adapters.AdapterSubCategorySeeAll;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.fillSubCatArrayL;

public class SubCategory extends AppCompatActivity implements AdapterSubCategorySeeAll.PassSubCategory{

    AdapterSubCategorySeeAll adapterSubCategorySeeAll;
    RecyclerView recyclerView;
    EditText searchEdt;
    RelativeLayout cancelRL;
    ImageView cancelIV;
    TextView sub_cat_title;
    String cat_name;
    ArrayList<Sub_Cat> sub_catArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        statusBarColor();
        sub_catArrayList = new ArrayList<>();
        getInfoFromCat();
        inti();
        changeFont();
        fillSubCategoryTitle();
        createRV();
        actionListenerToSearchEdt();
        actionListenerToRemoveTextInSearchEdt();
    }

    private void fillSubCategoryTitle() {
        sub_cat_title.setText(cat_name);
    }

    private void changeFont() {
        sub_cat_title.setTypeface(Functions.changeFontGeneral(this));
    }

    private void getInfoFromCat() {
        Bundle bundle = getIntent().getExtras();
        cat_name =bundle.getString("cat_name");
        sub_catArrayList = (ArrayList<Sub_Cat>) getIntent().getSerializableExtra("sub_cat");
        Log.i("TAG sub cat ", String.valueOf(sub_catArrayList.size()));

        for (int i=0;i<sub_catArrayList.size();i++)
        {
            Log.i("TAG sub cat ",sub_catArrayList.get(i).getName_en());
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
        ArrayList<Sub_Cat> multiAreasArrayList2  = new ArrayList<Sub_Cat>();
        for (Sub_Cat subCategory1 : sub_catArrayList) {
            if (subCategory1.getName_en().toLowerCase().contains(text.toLowerCase())) {
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
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterSubCategorySeeAll = new AdapterSubCategorySeeAll(getApplicationContext(), sub_catArrayList,this);
        recyclerView.setAdapter(adapterSubCategorySeeAll);
    }

    private void inti() {
        recyclerView = (RecyclerView) findViewById(R.id.sub_category_popup_RV);
        searchEdt = (EditText) findViewById(R.id.fragment_car_options_searchEdt);
        cancelRL = (RelativeLayout) findViewById(R.id.fragment_car_options_RL);
        cancelIV = (ImageView) findViewById(R.id.fragment_car_options_ImageV);
        sub_cat_title = (TextView) findViewById(R.id.sub_cat_title);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onClickedSubCategory(Sub_Cat subCategory) {
        moveToResultActivity();
    }

    private void moveToResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }
}