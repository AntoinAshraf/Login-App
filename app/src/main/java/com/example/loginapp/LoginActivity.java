package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;
    private ProgressBar loadingBar;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.EmailEditText);
        passwordET = findViewById(R.id.passEditText);
        loadingBar = findViewById(R.id.loadingBarLogin);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void Loginclick(View view) {
        showLoadingBar();

        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString();

        if(username.contains("@") && username.contains(".com")) {
            if(!(password.equals(""))) {
                String type = "login";

                BackGroundWorker backGroundWorker = new BackGroundWorker(this);
                backGroundWorker.execute(type, username, password);

                hideLoadingBar();
            } else{
                hideLoadingBar();
                Toast.makeText(this, "Enter Your Passwrod", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Write a valid Email", Toast.LENGTH_SHORT).show();
            hideLoadingBar();
        }
    }

    private void showLoadingBar(){
        loadingBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
    }

    private void hideLoadingBar(){
        loadingBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }

    public void onRegisterCLick(View view) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
