package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE username LIKE :username")
    User findByUsername(String username);

    @Query("SELECT * FROM user WHERE username LIKE :username AND password LIKE :password")
    User findByUsernameAndPassword(String username, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long signUp(User user);
}
