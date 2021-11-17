package com.blue_b.rest.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.NotificationModel;
import com.squareup.picasso.Picasso;

import static com.blue_b.rest.view.activity.mainScreem.MainActivity.setLocale;

public class AboutUs extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    NotificationModel notificationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(this);
        setContentView(R.layout.activity_about_us);
        statusBarColor();
        getNotificationFromIntent();
        inti();
        fillImageViewAndTV();
        changeFont();
    }

    private void fillImageViewAndTV() {
        Picasso.get()
                .load(notificationModel.getImage_url())
                .fit()
                .centerCrop()
                .into(imageView);
        if (!notificationModel.getOptional_en().equals("optional"))
        {
            textView.setText(Functions.getTextEngOrLocal(this,notificationModel.getOptional_en(),notificationModel.getOptional_local()));
        }else{
            textView.setText(Functions.getTextEngOrLocal(this,notificationModel.getDes_en(),notificationModel.getDes_local()));
        }
    }

    private void getNotificationFromIntent() {
        notificationModel = (NotificationModel) getIntent().getParcelableExtra("item_object");
    }

    private void inti() {
        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.about_us_activity_text);
    }

    private void changeFont() {
        textView.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }

    private void statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

}
