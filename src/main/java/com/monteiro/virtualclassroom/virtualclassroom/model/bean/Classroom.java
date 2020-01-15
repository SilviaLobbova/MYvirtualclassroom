package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable( tableName = "classrooms")
public class Classroom {

    @DatabaseField(columnName = "id_classroom", generatedId = true)
    private int id_classroom;

    @DatabaseField
    private String classroom_name;

    public Classroom(){
    }

    public Classroom(String classroom_name) {
        this.classroom_name = classroom_name;
    }



    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    @Override
    public String toString(){
        return "("+ classroom_name +")";
    }
}
