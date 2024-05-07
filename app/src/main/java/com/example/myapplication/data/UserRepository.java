package com.example.myapplication.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.model.User;

import java.util.List;

public class UserRepository {
    private static UserDao userDao = null;
    private static LiveData<List<User>> users;

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
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.deleteAllUsers();
        });
    }

    void insert(User User) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.signUp(User);
        });
    }

    public static User findByUsernameAndPassword(String username, String password) {
        Log.d("SG", "INSIDE USERREPO.JAVA IN FBUAP()"); //reached
        //adjust this next line
        if(userDao.findByUsernameAndPassword(username, password) == null){
            Log.d("SG", "INSIDE USERREPO.JAVA IN FBUAP() IN IF ==NULL");
        }
        return userDao.findByUsernameAndPassword(username, password);
    }

    public LiveData<String> getUsername() {
        return userDao.getLoggedInUsername();
    }

}