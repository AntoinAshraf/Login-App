package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void LoginActivityLaunch(View view) {
        Intent loginLaunch = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginLaunch);
    }

    public void RegisterActivityLaunch(View view) {
        Intent loginLaunch = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(loginLaunch);
    }
}
