package com.example.myapplication.data;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.data.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE username LIKE :username")
    @Nullable
    User findByUsername(String username);


    //This query seems to be causing nullpointerexceptions
    // @nullable may be helping by allowing the query to return a null obj without raising the
    // exception. Then the problem becomes the program trying to run on the main thread
    @Query("SELECT * FROM user WHERE username LIKE :username AND password LIKE :password")
    @Nullable
    User findByUsernameAndPassword(String username, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long signUp(User user);

    @Query("SELECT username FROM user LIMIT 1")
    LiveData<String> getLoggedInUsername();

    @Query("SELECT * FROM user WHERE username = (SELECT username FROM user LIMIT 1)")
    LiveData<User> getLoggedInUser();

    @Query("DELETE FROM user")
    void deleteUser();
}
