package com.fashion.rest.view.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.ReportType;
import com.fashion.rest.view.Adapters.AdapterReportType;
import java.util.ArrayList;
import java.util.Locale;
import static com.fashion.rest.functions.Functions.fillReportArrayL;

public class ReportActivity extends AppCompatActivity implements AdapterReportType.PassReport{

    RecyclerView recyclerView;
    TextView textView;
    ProgressBar progressBar;
    AdapterReportType adapterReportType;
    public ArrayList<ReportType> reportTypesArrayL ;
    AdapterReportType.PassReport passReport;
    ItemTest itemTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setElevation(15);

        passReport = (AdapterReportType.PassReport) this;

        statusBarColor();
        actionBarTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getInfoFromIntent();
        inti();
        changeFont();
        createRV();
    }
    private void createRV() {
        reportTypesArrayL = new ArrayList<>();
        reportTypesArrayL =fillReportArrayL(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterReportType = new AdapterReportType(getApplicationContext(), reportTypesArrayL,passReport);
        recyclerView.setAdapter(adapterReportType);
    }

    private void changeFont() {
        textView.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }

    private void inti() {
        progressBar = (ProgressBar) findViewById(R.id.report_activity_progress);
        textView = (TextView) findViewById(R.id.report_activity_tv);
        recyclerView = (RecyclerView) findViewById(R.id.report_activity_rv);
    }

    private void getInfoFromIntent() {
        itemTest = (ItemTest) getIntent().getParcelableExtra("item_object");
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void actionBarTitle() {
        Typeface typeface;
        final ActionBar abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.actionbar_custom_title_view_centered, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        if (Locale.getDefault().getLanguage().equals("ar")) {
            typeface = Typeface.createFromAsset(getAssets(), "GE_DINAR_ONE_LIGHT.TTF");
        }else{
            typeface = Typeface.createFromAsset(getAssets(), "NTAILU.TTF");
        }
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setTextColor(Color.parseColor("#FF0000"));
        textviewTitle.setText(getResources().getString(R.string.reports));
        textviewTitle.setTypeface(typeface);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(false);
        abar.setHomeButtonEnabled(false);
    }

    @Override
    public void onReportClicked(ReportType reportType) {
        //progressBar.setVisibility(View.VISIBLE);
//        ReportDetails reportDetails = new ReportDetails(reportType.getReport()
//                ,reportType.getReportS(),"NOTYET",userID,itemID,itemCategory,getDATE(),"0","0");
//        addReportToFireStore(reportDetails,this);
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("result", "Done");
//
//                setResult(Activity.RESULT_OK, resultIntent);
//                finish();
//            }
//        }, 2000);
        finish();
        Log.i("TAG",reportType.getReport());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}