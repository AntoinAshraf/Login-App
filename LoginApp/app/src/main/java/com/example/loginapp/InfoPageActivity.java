package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPageActivity extends AppCompatActivity {

    TextView F_NameTextV, L_NameTextV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        F_NameTextV = findViewById(R.id.F_NameTextV);
        L_NameTextV = findViewById(R.id.L_NameTextV);

        Bundle extras = getIntent().getExtras();
        String results[] = extras.getStringArray("PersonData");

        Person newPerson = makeObjectOfPerson(results[1], results[2], results[3], results[4], results[5], results[6],
                results[7], results[8], results[9],
                results[10], results[11]);

        ChangeTextViews(newPerson.F_Name, newPerson.L_Name);

        if(!newPerson.getVerified()){
            Toast.makeText(this, "Please Verify your Mail", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChangeTextViews(String F_Name, String L_Name){
        F_NameTextV.setText(F_Name);
        L_NameTextV.setText(L_Name);
    }

    public Person makeObjectOfPerson(String User_ID, String Email, String phoneNum, String Address, String F_Name, String L_Name,
                                     String Add_Permition, String Sell_Permition, String Age,
                                     String type, String Verified){
        Person newPerson = new Person();

        newPerson.User_ID = Integer.parseInt(User_ID);
        newPerson.setEmail(Email);
        newPerson.setPhoneNum(phoneNum);
        newPerson.setAddress(Address);
        newPerson.F_Name = F_Name;
        newPerson.L_Name = L_Name;

        if(Add_Permition.equals("0")){
            newPerson.setAdd_Permition(false);
        }else{
            newPerson.setAdd_Permition(true);
        }

        if(Sell_Permition.equals("0")){
            newPerson.setSell_Permition(false);
        }else{
            newPerson.setSell_Permition(true);
        }

        newPerson.setAge(Integer.parseInt(Age));
        newPerson.setType(Integer.parseInt(type));

        if(Verified.equals("0")){
            newPerson.setVerified(false);
        }else{
            newPerson.setVerified(true);
        }

        return newPerson;
    }
}
