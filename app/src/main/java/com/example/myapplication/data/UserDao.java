package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE username LIKE :username")
    User findByUsername(String username);

    @Query("SELECT * FROM user WHERE password LIKE :password")
    User findByPassword(String password);

    @Query("SELECT * FROM user WHERE username LIKE :username AND password LIKE :password")
    User findByUsernameAndPassword(String username, String password);

    @Insert()
    void insert(User user);

    @Query("SELECT EXISTS (SELECT * FROM user WHERE username=:username)")
    boolean is_taken(String username);

    @Query("SELECT EXISTS (SELECT * FROM user WHERE username=:username AND password=:password)")
    boolean login(String username, String password);
}
