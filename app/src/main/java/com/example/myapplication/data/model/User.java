package com.example.myapplication.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "isAdmin")
    public boolean isAdmin;

    @ColumnInfo(name = "date")
    public long date;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // ideally the object would have a "final Boolean IsAdmin"
    // and this method would create a new object but this is fine for now
    public void makeAdmin() {
        this.isAdmin = true;
    }

    public void demote() {
        this.isAdmin = false;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
