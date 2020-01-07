package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

public class Classroom {
    private int id_classroom;
    private String classroom_name;

    public Classroom(){
    }

    public Classroom(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public int getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(int id_classroom) {
        this.id_classroom = id_classroom;
    }

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }
}
