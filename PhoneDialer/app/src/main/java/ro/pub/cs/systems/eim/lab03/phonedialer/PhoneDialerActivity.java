package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, star, dial;
    ImageButton back, call, hang, contacts;
    EditText nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(onClickListener);
        b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(onClickListener);
        b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(onClickListener);
        b4 = (Button)findViewById(R.id.button4);
        b4.setOnClickListener(onClickListener);
        b5 = (Button)findViewById(R.id.button5);
        b5.setOnClickListener(onClickListener);
        b6 = (Button)findViewById(R.id.button6);
        b6.setOnClickListener(onClickListener);
        b7 = (Button)findViewById(R.id.button7);
        b7.setOnClickListener(onClickListener);
        b8 = (Button)findViewById(R.id.button8);
        b8.setOnClickListener(onClickListener);
        b9 = (Button)findViewById(R.id.button9);
        b9.setOnClickListener(onClickListener);
        b0 = (Button)findViewById(R.id.button0);
        b0.setOnClickListener(onClickListener);
        star = (Button)findViewById(R.id.buttonStar);
        star.setOnClickListener(onClickListener);
        dial = (Button)findViewById(R.id.buttonDial);
        dial.setOnClickListener(onClickListener);
        back = (ImageButton)findViewById(R.id.backBtn);
        back.setOnClickListener(onClickListener);
        call = (ImageButton)findViewById(R.id.callBtn);
        call.setOnClickListener(onClickListener);
        hang = (ImageButton)findViewById(R.id.rejectBtn);
        hang.setOnClickListener(onClickListener);
        nr = (EditText)findViewById(R.id.editText);
        contacts = findViewById(R.id.contacts);
        contacts.setOnClickListener(onClickListener);
    }

    private ButtonClickListener onClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view){

            String nr_text = nr.getText().toString();

            if (view.getId() == R.id.button0 || view.getId() == R.id.button1 || view.getId() == R.id.button2 ||
                    view.getId() == R.id.button3 || view.getId() == R.id.button4 || view.getId() == R.id.button5 ||
                    view.getId() == R.id.button6 || view.getId() == R.id.button7 || view.getId() == R.id.button8 ||
                    view.getId() == R.id.button9 || view.getId() == R.id.buttonStar || view.getId() == R.id.buttonDial) {
                nr.setText(nr_text + ((Button)view).getText().toString());
            }
            if (view.getId() == R.id.backBtn) {
                if (nr_text.length() > 0) {
                    nr.setText(nr_text.substring(0, nr_text.length() - 1));
                }
            }
            if (view.getId() == R.id.callBtn) {
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + nr.getText().toString()));
                    startActivity(intent);
                }
            }
            if (view.getId() == R.id.rejectBtn) {
                finish();
            }
            if(view.getId() == R.id.contacts) {
                String phoneNumber = nr.getText().toString();
                if (phoneNumber.length() > 0) {
                    Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                    intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(getApplication(), "Incorrect number", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
