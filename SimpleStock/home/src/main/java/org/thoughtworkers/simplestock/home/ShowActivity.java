package org.thoughtworkers.simplestock.home;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pixate.freestyle.PixateFreestyle;


public class ShowActivity extends Activity implements View.OnClickListener {
    private TextView textTotalAmount;
    private EditText textInputAmount;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PixateFreestyle.init(this);

        setContentView(R.layout.activity_show);

        Button buttonConfirmReceive = (Button) findViewById(R.id.buttonReceive);
        buttonConfirmReceive.setOnClickListener(this);

        Button buttonSendSms = (Button) findViewById(R.id.btnSendSms);
        buttonSendSms.setOnClickListener(this);

        textTotalAmount = (TextView) findViewById(R.id.totalAmount);
        textInputAmount = (EditText) findViewById(R.id.textAmount);

        Intent alarmIntent = new Intent(this, SMSSync.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
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
                setAlarmForSendingSms();
                break;
            default:
                throw new RuntimeException("Where are you clicking? - " + view.getId());
        }
    }

    private void displayReceiveAmount() {
        textTotalAmount.setText(textInputAmount.getEditableText());
    }

    private void setAlarmForSendingSms() {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent);
        Toast.makeText(this, "SMS Scheduled.", Toast.LENGTH_SHORT).show();
    }
}
