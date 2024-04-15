package com.example.movie.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movie.model.Movie;
import com.example.movie.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE username LIKE :username AND " +
            "password LIKE :password LIMIT 1")
    User findByUsernameAndPassword(String username, String password);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

}
