package com.example.movie.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "username")
    public String mUsername;

    @ColumnInfo(name = "password")
    public String mPassword;

    public User(@NonNull String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    public String getmUsername() {
        return mUsername;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
