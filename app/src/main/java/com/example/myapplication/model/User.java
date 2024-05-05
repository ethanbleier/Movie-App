package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.myapplication.typeConverters.LocalDateTypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int Id;

    @ColumnInfo(name = "username")
    public String Username;

    @ColumnInfo(name = "password")
    public String Password;

    @ColumnInfo(name = "isAdmin")
    public boolean IsAdmin;

    @ColumnInfo(name = "date")
    public long date;

    public User(String username, String password) {
        this.Username = username;
        this.Password = password;
        this.IsAdmin = false;
    }

    public User() {

    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    // ideally the object would have a "final Boolean IsAdmin"
    // and this method would create a new object but this is fine for now
    public void makeAdmin() {
        this.IsAdmin = true;
    }

    public void demote() {
        this.IsAdmin = false;
    }

    public String getUsername() {
        return this.Username;
    }
}
