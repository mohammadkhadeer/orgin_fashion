package com.fashion.rest.view.activity;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.NotificationModel;
import com.fashion.rest.service.FcmMessagingService;
import com.squareup.picasso.Picasso;

import java.net.URL;

import static com.fashion.rest.app.App.CHANNEL_1_ID;
import static com.fashion.rest.app.App.CHANNEL_3_ID;
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
        if (!notificationModel.getOptional_en().equals("optional"))
        {
            textView.setText(getTextEngOrLocal(this,notificationModel.getOptional_en(),notificationModel.getOptional_local()));
        }else{
            textView.setText(getTextEngOrLocal(this,notificationModel.getDes_en(),notificationModel.getDes_local()));
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
