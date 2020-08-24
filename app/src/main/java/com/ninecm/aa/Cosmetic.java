package com.ninecm.aa;

public class Cosmetic {
    private String title;
    private String endDay;
    private String openDay;
    private String memo;
    private int star;

    public Cosmetic(String title, String endDay, String openDay, String memo, int star) {
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

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getOpenDay() {
        return openDay;
    }

    public void setOpenDay(String openDay) {
        this.openDay = openDay;
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
