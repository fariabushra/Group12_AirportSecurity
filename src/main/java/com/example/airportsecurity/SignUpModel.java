package com.example.airportsecurity;

import java.util.ArrayList;

public class SignUpModel {
    private String name,password,email,number;

    public static ArrayList<SignUpModel> accountHolders = new ArrayList<>();


    public SignUpModel(String name, String password, String email, String number) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static ArrayList<SignUpModel> getAccountHolders() {
        return accountHolders;
    }

    public static void setAccountHolders(ArrayList<SignUpModel> accountHolders) {
        SignUpModel.accountHolders = accountHolders;
    }

    @Override
    public String toString() {
        return "SignUpModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
