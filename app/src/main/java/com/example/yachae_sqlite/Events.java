package com.example.yachae_sqlite;

public class Events {
    String DATE,MONTH,YEAR;

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public Events( String DATE, String MONTH, String YEAR) {
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
    }
}
