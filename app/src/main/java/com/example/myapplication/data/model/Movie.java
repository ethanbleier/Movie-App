package com.example.myapplication.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "title")
    public String mTitle;

    @ColumnInfo(name = "director")
    public String mDirector;

    @ColumnInfo(name = "genre")
    public String mGenre;

    @ColumnInfo(name = "year")
    public String mYear;

    public Movie(String title, String director, String genre, String year) {
        this.mTitle = title;
        this.mDirector = director;
        this.mGenre = genre;
        this.mYear = year;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDirector() {
        return mDirector;
    }

    public String getmGenre() {
        return mGenre;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDirector(String mDirector) {
        this.mDirector = mDirector;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }
}
