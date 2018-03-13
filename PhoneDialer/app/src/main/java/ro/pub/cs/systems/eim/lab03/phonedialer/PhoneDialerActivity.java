package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
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


public class PhoneDialerActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private ImageButton callImageButton;
    public Button genericButton;

    public static int buttonIds[] = {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.star,
            R.id.dialer
    };
    public static int PERMISSION_REQUEST_CALL_PHONE = 1;

    private CallImageButtonClickListener callImageButtonClickListener = new CallImageButtonClickListener();
    private class CallImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }

    private HangupImageButtonClickListener hangupImageButtonClickListener = new HangupImageButtonClickListener();
    private class HangupImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
    private class BackspaceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
            phoneNumberEditText.setText(phoneNumber);
        }
    }

    private GenericButtonClickListener genericButtonClickListener = new GenericButtonClickListener();
    private class GenericButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString() + ((Button)view).getText().toString();
            phoneNumberEditText.setText(phoneNumber);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        phoneNumberEditText = findViewById(R.id.editText);
        callImageButton = findViewById(R.id.call_button);
        callImageButton.setOnClickListener(callImageButtonClickListener);
        ImageButton hangupImageButton = findViewById(R.id.hangup_button);
        hangupImageButton.setOnClickListener(hangupImageButtonClickListener);
        ImageButton backspaceImageButton = findViewById(R.id.backspace_image_button);
        backspaceImageButton.setOnClickListener(backspaceButtonClickListener);
        for (int buttonId : buttonIds) {
            genericButton = findViewById(buttonId);
            genericButton.setOnClickListener(genericButtonClickListener);
        }
    }


}

