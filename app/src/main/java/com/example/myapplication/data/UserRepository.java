package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model.User;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAll();
    }

    LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    void insert(User User) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(User);
        });
    }

    public User findByUsernameAndPassword(String username, String password) {
        return mUserDao.findByUsernameAndPassword(username, password);
    }
}