package com.monteiro.virtualclassroom.virtualclassroom.model.bean;


public class User {
    private int id_user;
    private String user_name;
    private String user_lastname;
    private String user_email;
    private String user_password;

    public User(){};

    public User(String user_name, String user_lastname, String user_email, String user_password) {
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
