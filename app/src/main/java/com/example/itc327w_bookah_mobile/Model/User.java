package com.example.itc327w_bookah_mobile.Model;

public class User {
    public String phoneNumber, firstName, lastName, idNumber, emailAddress, password;

    public User() {
    }

    public User(String phoneNumber, String firstName, String lastName, String idNumber, String emailAddress, String password) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public User(String phoneNumber, String firstName, String lastName, String IDNumber, String emailAddress, String password) {
        this.PhoneNumber = phoneNumber;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.IDNumber = IDNumber;
        this.EmailAddress = emailAddress;
        this.Password = password;
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
    }*/
}

