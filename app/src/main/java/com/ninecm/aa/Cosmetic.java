package com.ninecm.aa;

/* 관리할 품목 클래스 */
public class Cosmetic {
    private int id;
    private String title;
    private String endDay;
    private String memo;
    private int star;

    // Constructor
    public Cosmetic(int id, String title, String endDay,  String memo, int star) {
        this.id = id;
        this.title = title;
        this.endDay = endDay;
        this.memo = memo;
        this.star = star;
    }

    // getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
