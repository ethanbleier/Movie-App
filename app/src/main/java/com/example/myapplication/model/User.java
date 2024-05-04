package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "username")
    public String mUsername;

    @ColumnInfo(name = "password")
    public String mPassword;

    @ColumnInfo(name = "isAdmin")
    public boolean mIsAdmin;

    public User(@NonNull String username, String password, boolean isAdmin) {
        this.mIsAdmin = false;
        this.mUsername = username;
        this.mPassword = password;
    }

    public boolean checkPassword(String attempt) {
        return attempt.equals(mPassword);
    }

    public boolean isAdmin() {
        return this.mIsAdmin;
    }

    public void makeAdmin() {
        this.mIsAdmin = true;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getmUsername() {
        return this.mUsername;
    }
}
