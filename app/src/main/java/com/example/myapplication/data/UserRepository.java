package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.model.User;

import java.util.List;
import java.util.concurrent.Executor;

public class UserRepository {
    private static UserDao userDao = null;
    private static LiveData<List<User>> users;
    public Executor databaseWriteExecutor;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        users = userDao.getAll();
    }

    public LiveData<User> getLoggedInUser() {
        return userDao.getLoggedInUser();
    }

    public LiveData<List<User>> getAllUsers() {
        return users;
    }

    // ... deletes all the users from db
    public void deleteAllUsers() {
        UserRoomDatabase.databaseWriteExecutor.execute(userDao::deleteAllUsers);
    }

    public long insert(User user) {
        return userDao.insert(user);
    }

    public static User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public LiveData<String> getUsername() {
        return userDao.getLoggedInUsername();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}