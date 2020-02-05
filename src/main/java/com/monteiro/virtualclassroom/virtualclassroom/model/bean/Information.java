package com.monteiro.virtualclassroom.virtualclassroom.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "information")
public class Information {
    @DatabaseField(generatedId = true)
    private int id_information;

    @DatabaseField(canBeNull = false)
    private String information_label;

    @DatabaseField(canBeNull = false)
    private String information_url;

    @DatabaseField(canBeNull = false)
    private long id_classroom;

    public Information() {}

    public Information(String information_label, String information_url, long id_classroom) {
        this.information_label = information_label;
        this.information_url = information_url;
        this.id_classroom = id_classroom;
    }

    public int getId_information() {
        return id_information;
    }

    public void setId_information(int id_information) {
        this.id_information = id_information;
    }

    public String getInformation_label() {
        return information_label;
    }

    public void setInformation_label(String information_label) {
        this.information_label = information_label;
    }

    public String getInformation_url() {
        return information_url;
    }

    public void setInformation_url(String information_url) {
        this.information_url = information_url;
    }
}
