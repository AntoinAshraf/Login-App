package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassActivity extends AppCompatActivity {

    EditText passwordEditT, confirmPasswordEditT;
    String mailFromIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        passwordEditT = findViewById(R.id.password);
        confirmPasswordEditT = findViewById(R.id.Confirmpassword);

        Bundle extras = getIntent().getExtras();
        mailFromIntent = extras.getString("Email");
    }

    public void OnChangePassClick(View view) {

        String type = "changePass";

        String passwordString = passwordEditT.getText().toString();
        String confirmPassString = confirmPasswordEditT.getText().toString();

        if(passwordString.equals(confirmPassString)){

            BackGroundWorker backGroundWorker = new BackGroundWorker(this);
            backGroundWorker.execute(type, mailFromIntent, passwordString);

        }else{
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }
    }
}
