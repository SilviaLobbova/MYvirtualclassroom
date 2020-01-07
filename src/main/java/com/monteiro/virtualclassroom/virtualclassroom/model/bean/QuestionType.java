package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

public class QuestionType {
    private int id_question_type;
    private String question_type;

    public QuestionType() {}

    public QuestionType(String question_type) {
        this.question_type = question_type;
    }

    public int getId_question_type() {
        return id_question_type;
    }

    public void setId_question_type(int id_question_type) {
        this.id_question_type = id_question_type;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }
}
