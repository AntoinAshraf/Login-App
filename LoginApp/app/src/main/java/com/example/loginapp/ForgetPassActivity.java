package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassActivity extends AppCompatActivity {

    EditText forgetEmailEditT, MailConfirmEditT;
    TextView sendCodeAgainTV;
    Button sendMailBtn, confirmCodeBtn;
    String confirmDigits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        forgetEmailEditT = findViewById(R.id.ForgetEmailEditText);
        MailConfirmEditT = findViewById(R.id.ConfirmMail);

        sendCodeAgainTV = findViewById(R.id.sendCodeAgainTV);
        sendMailBtn = findViewById(R.id.sendMailBtn);
        confirmCodeBtn = findViewById(R.id.ConfirmMailBtn);

        confirmDigits = null;

        Bundle extras = getIntent().getExtras();
        String EmailString = extras.getString("ForgetEmail");
        forgetEmailEditT.setText(EmailString);

        if(!(extras.getString("confirmDigits") == null)){
            confirmDigits = extras.getString("confirmDigits");
            MailConfirmEditT.setVisibility(View.VISIBLE);
            sendCodeAgainTV.setVisibility(View.VISIBLE);
            sendMailBtn.setVisibility(View.GONE);
            confirmCodeBtn.setVisibility(View.VISIBLE);
        }
    }

    public void OnSendMailClick(View view) {

        String MailText = forgetEmailEditT.getText().toString().trim();
        if(MailText.contains("@") && MailText.contains(".com")) {

            String type = "forgetPass";

            BackGroundWorker backGroundWorker = new BackGroundWorker(this);
            backGroundWorker.execute(type, MailText);

        }else{
            Toast.makeText(this, "Enter A valid mail", Toast.LENGTH_SHORT).show();
        }

    }

    public void OnConfirmMailClick(View view) {
        String userEnteredCode = MailConfirmEditT.getText().toString();
        if(userEnteredCode.equals(confirmDigits)){
            Intent changePasswordIntent = new Intent(ForgetPassActivity.this, ChangePassActivity.class);
            changePasswordIntent.putExtra("Email", forgetEmailEditT.getText().toString().trim());
            startActivity(changePasswordIntent);
        }else{
            Toast.makeText(this, "Check the code again", Toast.LENGTH_SHORT).show();
        }
    }
}
