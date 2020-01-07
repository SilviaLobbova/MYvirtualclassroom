package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

public class Question {
    private int id_question;
    private String question_content;

    public Question(){};

    public Question(String question_content) {
        this.question_content = question_content;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }
}
