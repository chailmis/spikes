package org.thoughtworkers.simplestock.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSSync extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        sendSms();
        Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
    }

    private void sendSms() {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent piSend = PendingIntent.getBroadcast(context, 0, new Intent(new Intent()), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, new Intent(new Intent()), 0);
        smsManager.sendTextMessage("+256773090348", null, "This is a scheduled sms", piSend, piDelivered);
    }
}
