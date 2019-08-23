package com.example.loginapp;

public class Person {

    public int User_ID;
    private String Email;
    private String phoneNum;
    private String Address;
    public String F_Name;
    public String L_Name;
    private boolean Add_Permition;
    private boolean Sell_Permition;
    private int Age;
    private int type;
    private boolean Verified;

    public Person(){

    }

    public Person(int User_ID, String Email, String phoneNum, String Address, String F_Name, String L_Name,
                  boolean Add_Permition, boolean Sell_Permition, int Age,
                  int type, boolean Verified){

        this.User_ID = User_ID;
        this.Email = Email;
        this.phoneNum = phoneNum;
        this.Address = Address;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Add_Permition = Add_Permition;
        this.Sell_Permition = Sell_Permition;
        this.Age = Age;
        this.type = type;
        this.Verified = Verified;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getEmail(){
        return this.Email;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getAddress(){
        return this.Address;
    }

    public void setAdd_Permition(boolean Add_Permition){
        this.Add_Permition = Add_Permition;
    }
    public boolean getAdd_Permition(){
        return this.Add_Permition;
    }

    public void setSell_Permition(boolean Sell_Permition){
        this.Sell_Permition = Sell_Permition;
    }
    public boolean getSell_Permition(){
        return this.Sell_Permition;
    }

    public void setAge(int Age){
        this.Age = Age;
    }
    public int getAge(){
        return this.Age;
    }

    public void setType(int type){
        if(type <= UserType.Patient_TYPE_NUMBER && type >= UserType.MANAGER_TYPE_NUMBER){
            this.type = type;
        }else{
            this.type = UserType.Patient_TYPE_NUMBER;
        }
    }
    public int getType(){
        return this.type;
    }

    public void setVerified(boolean Verified){
        this.Verified = Verified;
    }
    public boolean getVerified(){
        return this.Verified;
    }
}
