package com.example.ippypro;

import java.util.Date;

public class Timezone implements java.io.Serializable {
    private String name;
    private String datetime;
    private String datetimelocal;
    private String ianaName;

    public Timezone(String name, String datetime, String ianaName) {
        this.name = name;
        this.datetime = datetime;
        this.ianaName = ianaName;
    }

    public String getString() {
        Date localTime = new Date();
        this.datetimelocal = localTime.toString();
        String timeStr = "\nTime Zone: " + name + "\n" + "Time: " + datetime + "\nLocal Time: " +
                datetimelocal + "\nIana Name: " + ianaName + "\n";
        return timeStr;
    }
}