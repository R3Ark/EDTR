package com.eferm.edtr.Model;

import io.realm.RealmObject;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class DTRData extends RealmObject {

    private String Barcode;
    private String Name;
    private String Date;
    private String Time;
    private String Type;

    public String getBarcode() {
        return Barcode;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getType() {
        return Type;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setType(String type) {
        Type = type;
    }
}
