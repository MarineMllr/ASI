package com.sp.dto;

public class Login {

    private String surname;
    private String password;

    public Login(String surname, String password) {
        this.surname = surname;
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
