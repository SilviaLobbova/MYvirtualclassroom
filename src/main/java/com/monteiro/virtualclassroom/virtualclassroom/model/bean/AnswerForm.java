package com.monteiro.virtualclassroom.virtualclassroom.model.bean;


public class AnswerForm {

    boolean singleCheckboxField;

    int[] multiCheckboxSelectedValues;


    int id_option;


    public AnswerForm() {
    }

    public boolean isSingleCheckboxField() {
        return singleCheckboxField;
    }

    public void setSingleCheckboxField(boolean singleCheckboxField) {
        this.singleCheckboxField = singleCheckboxField;
    }

    public int[] getMultiCheckboxSelectedValues() {
        return multiCheckboxSelectedValues;
    }

    public void setMultiCheckboxSelectedValues(int[] multiCheckboxSelectedValues) {
        this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }
}
