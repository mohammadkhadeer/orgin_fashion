package com.fashion.rest.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.NotificationModel;
import com.squareup.picasso.Picasso;

import static com.fashion.rest.functions.Functions.getTextEngOrLocal;


public class AboutUs extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    NotificationModel notificationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        textView.setText(getTextEngOrLocal(this,notificationModel.getOptional_en(),notificationModel.getOptional_local()));
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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
