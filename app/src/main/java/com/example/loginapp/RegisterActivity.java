package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText FnameET, LnameET, PhoneNumET, AddressET, AgeET, emailET, passwordET, conf_passwordET;
    private Spinner userTypeSpinner;
    private ProgressBar loadingBar;
    private Button RegisterBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FnameET = findViewById(R.id.Fname);
        LnameET = findViewById(R.id.Lname);
        PhoneNumET = findViewById(R.id.phoneNum);
        AddressET = findViewById(R.id.address);
        AgeET = findViewById(R.id.age);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        conf_passwordET = findViewById(R.id.Confirmpassword);

        loadingBar = findViewById(R.id.loadingBarRegist);

        RegisterBtn = findViewById(R.id.button_register);

        userTypeSpinner = findViewById(R.id.userType_spenner);
        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.userTypes));
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeAdapter);


    }

    public void BtnRegisterClick(View view) {
        register();
    }

    private void register(){
        showLoadingBar();

        String Fname = FnameET.getText().toString().trim();
        String Lname = LnameET.getText().toString().trim();
        String PhoneNumber = PhoneNumET.getText().toString();
        String Address = AddressET.getText().toString().trim();
        String age = AgeET.getText().toString();
        String SelectedUserType = userTypeSpinner.getItemAtPosition(userTypeSpinner.getSelectedItemPosition()).toString();
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString();
        String conf_password = conf_passwordET.getText().toString();
        if(!(SelectedUserType.equals("Choose User Type"))) {
            if (isNumeric(age) && isNumeric(PhoneNumber) && !(age.isEmpty()) && !(PhoneNumber.isEmpty())) {
                if (password.equals(conf_password)) {
                    if (email.contains("@") && email.contains(".com")) {
                        if (!(Fname.isEmpty()) && !(Lname.isEmpty()) && !(Address.isEmpty())) {
                            if (!(password.equals(""))) {
                                String type = "register";

                                BackGroundWorker backGroundWorker = new BackGroundWorker(this);
                                backGroundWorker.execute(type, Fname, Lname, PhoneNumber, Address, age, SelectedUserType, email, password);

                                hideLoadingBar();
                            } else {
                                hideLoadingBar();
                                Toast.makeText(this, "Enter A password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            hideLoadingBar();
                            Toast.makeText(this, "Type your name & address", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        hideLoadingBar();
                        Toast.makeText(this, "you must type a valid Email", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    hideLoadingBar();
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            } else {
                hideLoadingBar();
                Toast.makeText(this, "Check age and phone number Values", Toast.LENGTH_SHORT).show();
            }
        } else{
            hideLoadingBar();
            Toast.makeText(this, "Choose userType Value", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLoadingBar(){
        loadingBar.setVisibility(View.VISIBLE);
        RegisterBtn.setVisibility(View.GONE);
    }

    private void hideLoadingBar(){
        loadingBar.setVisibility(View.GONE);
        RegisterBtn.setVisibility(View.VISIBLE);
    }

    public void onLoginCLick(View view) {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}


