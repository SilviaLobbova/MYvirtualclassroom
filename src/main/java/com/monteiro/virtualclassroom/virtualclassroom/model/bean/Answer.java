package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "answers")
public class Answer {
    @DatabaseField(columnName = "id_answer", generatedId = true)
    private long id_answer;
    @DatabaseField
    private int id_user;
    @DatabaseField
    private int id_option;

    public Answer(){
    }

    public Answer(int id_user, int id_option) {
        this.id_user = id_user;
        this.id_option = id_option;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }

}







