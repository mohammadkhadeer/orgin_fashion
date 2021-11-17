package com.blue_b.rest.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.blue_b.rest.R;

public class App extends Application {

    public static final String CHANNEL_1_ID="new_items";
    public static final String CHANNEL_2_ID="new_offers";
    public static final String CHANNEL_3_ID="important_message";

    @Override
    public void onCreate() {
        super.onCreate();

        createChannels();
    }

    private void createChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel_1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    getResources().getString(R.string.new_items),
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel_1.setDescription(getResources().getString(R.string.new_items));

            NotificationChannel channel_2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    getResources().getString(R.string.new_offer),
                    NotificationManager.IMPORTANCE_LOW
            );
            channel_2.setDescription(getResources().getString(R.string.new_offer));

            NotificationChannel channel_3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    getResources().getString(R.string.important_message),
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel_3.setDescription(getResources().getString(R.string.important_message));

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel_1);
            manager.createNotificationChannel(channel_2);
            manager.createNotificationChannel(channel_3);
        }
    }
}
