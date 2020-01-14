package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true)
    private int id_user;

    @DatabaseField
    public String user_name;

    @DatabaseField
    private String user_lastname;

    @DatabaseField
    private String user_email;

    @DatabaseField
    private String user_password;

    @DatabaseField
    private boolean isAdmin;

    @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "id_classroom", foreignAutoCreate = true)
    private Classroom classroom;

    public User(){
        super();
    };

    public User(String user_name){
        this.user_name = user_name;
    };

    public User(String user_email, String user_password){
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public User(String user_name, String user_lastname, String user_email, String user_password){
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public User(String user_name, String user_lastname, String user_email, String user_password, boolean isAdmin,Classroom classroom) {
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.isAdmin = isAdmin;
        this.classroom = classroom;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Classroom get_classroom() {
        return classroom;
    }

    public void set_classroom(Classroom classroom) {
        this.classroom = classroom;
    }


    public String userToString(){
        return "("+ user_email + " "+ user_password +")";
    }
}
