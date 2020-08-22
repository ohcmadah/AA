package com.ninecm.aa;

public class Costemic {
    private String title;
    private int endDay;
    private int openDay;
    private String memo;
    private int star;

    public Costemic(String title, int endDay, int openDay, String memo, int star) {
        this.title = title;
        this.endDay = endDay;
        this.openDay = openDay;
        this.memo = memo;
        this.star = star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getOpenDay() {
        return openDay;
    }

    public void setOpenDay(int openDay) {
        this.openDay = openDay;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
