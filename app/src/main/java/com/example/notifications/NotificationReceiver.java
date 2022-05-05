package com.example.notifications;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        Bundle remoteinput = RemoteInput.getResultsFromIntent(intent);
        if(remoteinput!=null){
            CharSequence messagereceived = remoteinput.getCharSequence("Reply_message");
            Message answer = new Message(messagereceived,null);
            MainActivity.messages.add(answer);

            MainActivity.sendMessageNotif(context);
        }
    }
}
