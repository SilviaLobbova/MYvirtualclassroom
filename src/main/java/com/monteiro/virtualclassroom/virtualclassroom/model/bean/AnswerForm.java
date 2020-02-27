package com.monteiro.virtualclassroom.virtualclassroom.model.bean;


public class AnswerForm {

    int[] multiCheckboxSelectedValues;

    int radioOption;


    public AnswerForm() {
    }


    public int[] getMultiCheckboxSelectedValues() {
        return multiCheckboxSelectedValues;
    }

    public void setMultiCheckboxSelectedValues(int[] multiCheckboxSelectedValues) {
        this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
    }

    public int getRadioOption() {
        return radioOption;
    }

    public void setRadioOption(int radioOption) {
        this.radioOption = radioOption;
    }
}
