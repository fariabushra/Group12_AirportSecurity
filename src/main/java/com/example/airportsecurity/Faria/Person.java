package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class Person implements Serializable {
    String name, phoneNo, nidNo, passportNo, email, address, nationality;

    public Person(String name, String phoneNo, String nidNo, String passportNo, String email, String address, String nationality) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.nidNo = nidNo;
        this.passportNo = passportNo;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNidNo() {
        return nidNo;
    }

    public void setNidNo(String nidNo) {
        this.nidNo = nidNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", nidNo='" + nidNo + '\'' +
                ", passportNo='" + passportNo + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
