package com.ninecm.aa;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/* 관리할 품목 클래스 */
@Entity(tableName = "cosmetic_table")
public class Cosmetic implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String endDay;
    private int star;
    private String memo;
    private boolean except;

    // Constructor
    public Cosmetic(String title, String endDay, int star, String memo) {
        this.title = title;
        this.endDay = endDay;
        this.star = star;
        this.memo = memo;
        this.except = false;
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

    public boolean getExcept() {
        return this.except;
    }

    public void setExcept(boolean except) {
        this.except = except;
    }

    @NonNull
    @Override
    public String toString() {
        final String str = title+" "+endDay+" "+String.valueOf(star)+" "+memo;
        return str;
    }
}
