package org.thoughtworkers.simplestock.home;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pixate.freestyle.PixateFreestyle;


public class ShowActivity extends Activity implements View.OnClickListener {
    private static final Intent SMS_SENT = new Intent();
    private static final Intent SMS_DELIVERED = new Intent();
    private Button buttonConfirmReceive;
    private Button buttonSendSms;
    private TextView textTotalAmount;
    private EditText textInputAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PixateFreestyle.init(this);

        setContentView(R.layout.activity_show);

        buttonConfirmReceive = (Button) findViewById(R.id.buttonReceive);
        buttonConfirmReceive.setOnClickListener(this);

        buttonSendSms = (Button) findViewById(R.id.btnSendSms);
        buttonSendSms.setOnClickListener(this);

        textTotalAmount = (TextView) findViewById(R.id.totalAmount);
        textInputAmount = (EditText) findViewById(R.id.textAmount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonReceive:
                displayReceiveAmount();
                break;
            case R.id.btnSendSms:
                sendReceiveAmountAsSms();
                break;
            default:
                throw new RuntimeException("Where are you clicking? - " + view.getId());
        }
    }

    private void sendReceiveAmountAsSms() {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);
        smsManager.sendTextMessage("+256793330319", null, "Guess what? This is from an Android app...", piSend, piDelivered);
    }

    private void displayReceiveAmount() {
        textTotalAmount.setText(textInputAmount.getEditableText());
    }
}
