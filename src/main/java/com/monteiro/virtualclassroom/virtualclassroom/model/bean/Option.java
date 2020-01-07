package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

public class Option {
    private int id_option;
    private String option_content;

    public Option() {}
    public Option(String option_content) {
        this.option_content = option_content;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }

    public String getOption_content() {
        return option_content;
    }

    public void setOption_content(String option_content) {
        this.option_content = option_content;
    }
}
