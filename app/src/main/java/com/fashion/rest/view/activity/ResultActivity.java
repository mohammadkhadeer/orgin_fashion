package com.fashion.rest.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.model.Sub_Cat;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults;

import java.util.ArrayList;

import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class ResultActivity extends AppCompatActivity {

    FragmentResults fragmentResults = new FragmentResults();
    String cat_id,sub_cat_id;
    ImageView backIV;
    RelativeLayout back_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getInfoFromCat();
        inti();
        fillBackImageViewDepLanguage();
        actionListenerToBack();
        handelResultFragment();
    }

    private void actionListenerToBack() {
        back_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fillBackImageViewDepLanguage() {
        if (getLanguageFromSP(this).equals("en"))
        {
            backIV.setImageResource(R.drawable.arrow_en);
        } else
        {
            backIV.setImageResource(R.drawable.arrow_ar);
        }
    }

    private void inti() {
        backIV = (ImageView) findViewById(R.id.backIV);
        back_rl = (RelativeLayout) findViewById(R.id.back_rl);
    }

    private void getInfoFromCat() {
        Bundle bundle = getIntent().getExtras();
        sub_cat_id =bundle.getString("sub_cat_id");
        cat_id =bundle.getString("cat_id");
        //sub_catArrayList = (ArrayList<Sub_Cat>) getIntent().getSerializableExtra("sub_cat");
    }

    private void handelResultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("sub_cat_id",sub_cat_id);
        bundle.putString("cat_id",cat_id);

        fragmentResults.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_result_activity, fragmentResults)
                .commit();
    }

}