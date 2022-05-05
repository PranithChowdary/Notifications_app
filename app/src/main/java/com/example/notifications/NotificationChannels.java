package com.example.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannels extends Application {
    public static final String CHANNEL_1_ID = "1001";
    public static final String CHANNEL_2_ID = "1002";
    public static final String CHANNEL_3_ID = "1003";
    public static final String CHANNEL_4_ID = "1004";

    @Override
    public void onCreate() {
        super.onCreate();

        createChannels();
    }
    private void createChannels(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){




            NotificationChannel notificationChannel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel1.setDescription("testing channel 1");


            NotificationChannel notificationChannel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel2.setDescription("Testing channel 2");

            NotificationChannel notificationChannel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel3.setDescription("This is channel 3");

            NotificationChannel notificationChannel4 = new NotificationChannel(
                    CHANNEL_4_ID,
                    "channel 4",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel3.setDescription("This is channel 4");


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel1);
            manager.createNotificationChannel(notificationChannel2);
            manager.createNotificationChannel(notificationChannel3);
            manager.createNotificationChannel(notificationChannel4);





        }
    }
}
