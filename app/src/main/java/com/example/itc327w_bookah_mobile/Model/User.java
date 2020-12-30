package com.example.itc327w_bookah_mobile.Model;

public class User {
    private String PhoneNumber, FirstName, LastName, IDNumber, EmailAddress, Password;
    public double balance;

    public User() {
    }

    public User(String phoneNumber, String firstName, String lastName, String IDNumber, String emailAddress, String password) {
        PhoneNumber = phoneNumber;
        FirstName = firstName;
        LastName = lastName;
        this.IDNumber = IDNumber;
        EmailAddress = emailAddress;
        Password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCellphone() {
        return PhoneNumber;
    }

    public void setCellphone(String cellphone) {
        PhoneNumber = cellphone;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

