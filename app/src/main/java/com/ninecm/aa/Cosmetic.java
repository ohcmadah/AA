package com.ninecm.aa;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* 관리할 품목 클래스 */
@Entity(tableName = "cosmetic_table")
public class Cosmetic {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String endDay;
    private int star;
    private String memo;

    // Constructor
    public Cosmetic(String title, String endDay, int star, String memo) {
        this.title = title;
        this.endDay = endDay;
        this.star = star;
        this.memo = memo;
    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @NonNull
    @Override
    public String toString() {
        final String str = title+" "+endDay+" "+String.valueOf(star)+" "+memo;
        return str;
    }
}
