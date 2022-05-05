package com.example.notifications;

import static com.example.notifications.NotificationChannels.CHANNEL_1_ID;
import static com.example.notifications.NotificationChannels.CHANNEL_2_ID;
import static com.example.notifications.NotificationChannels.CHANNEL_3_ID;
import static com.example.notifications.NotificationChannels.CHANNEL_4_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.Notification.MediaStyle;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.*;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText title, desc;
    Button c1, c2, c3, c4, c5;
    private static int id = 0;
    NotificationManagerCompat manager;
    static List<Message> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = NotificationManagerCompat.from(getApplicationContext());
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        c1 = findViewById(R.id.channel1);
        c2 = findViewById(R.id.channel2);
        c3 = findViewById(R.id.channel3);
        c4 = findViewById(R.id.channel4);
        c5 = findViewById(R.id.channel5);
        messages = new ArrayList<>();
        messages.add(new Message("Hi","Siddhu"));
        messages.add(new Message("Hello",null));
        messages.add(new Message("Are u busy rn?","Siddhu"));


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent OpenonTap = new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                        0,OpenonTap,0);

                Intent broadcast = new Intent(getApplicationContext(), NotificationReceiverToast.class);
                broadcast.putExtra("message","Helllooo");
                PendingIntent broadcaster = PendingIntent.getBroadcast(getApplicationContext(),
                        0,broadcast, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_airport)
                        .setContentTitle(title.getText().toString())
                        .setContentText(desc.getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setContentIntent(pendingIntent)
                        .addAction(R.mipmap.ic_launcher,"Toast",broadcaster)
                        .setAutoCancel(true)
                        .build();
                manager.notify(id++, notification);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.ic_tractor)
                        .setContentTitle(title.getText().toString())
                        .setContentText(desc.getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                manager.notify(id++, notification);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap large = BitmapFactory.decodeResource(getResources(), R.drawable.large);
                Bitmap small = BitmapFactory.decodeResource(getResources(),R.drawable.small);
                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_3_ID)
                        .setSmallIcon(R.drawable.ic_airport)
                        .setContentTitle(title.getText().toString())
                        .setContentText(desc.getText().toString())
                        .setLargeIcon(small)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(large)
                                .bigLargeIcon(null))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                manager.notify(id++, notification);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap large = BitmapFactory.decodeResource(getResources(), R.drawable.large);
                Bitmap small = BitmapFactory.decodeResource(getResources(),R.drawable.small);

                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_4_ID)
                        .setSmallIcon(R.drawable.ic_tractor)
                        .setContentTitle(title.getText().toString())
                        .setLargeIcon(small)
                        .setContentText(desc.getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .addAction(R.drawable.ic_dislike,"dislike",null)
                        .addAction(R.drawable.ic_previous,"previous",null)
                        .addAction(R.drawable.ic_pause,"pause",null)
                        .addAction(R.drawable.ic_next,"next",null)
                        .addAction(R.drawable.ic_like,"like",null)
                        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(1,2,3))
                        .setSubText("Playing now")
                        .build();
                manager.notify(id++, notification);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                sendMessageNotif(getApplicationContext());
            }
        });

    }

    public static void sendMessageNotif(Context context){

        RemoteInput remoteInput = new RemoteInput.Builder("Reply_message")
                .setLabel("Reply")
                .build();
        Intent reply = new Intent( context,NotificationReceiver.class );
        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(
                context, 0, reply, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Action replyaction = new NotificationCompat.Action.Builder(
                R.drawable.ic_airport,
                "Reply",
                replyPendingIntent
        ).addRemoteInput(remoteInput).build();

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle("Me");
        messagingStyle.setConversationTitle("Messages");

        for(Message message:messages){
            NotificationCompat.MessagingStyle.Message notifmessage = new NotificationCompat.MessagingStyle.Message(
                    message.getText(),message.getTimestamp(),message.getSender()
            );
            messagingStyle.addMessage(notifmessage);
        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_4_ID)
                .setSmallIcon(R.drawable.ic_tractor)
                .setContentTitle("Messages")
                .setContentText("Replyable Notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setStyle(messagingStyle)
                .addAction(replyaction)
                .setColor(Color.CYAN)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(id++, notification);
    }
}