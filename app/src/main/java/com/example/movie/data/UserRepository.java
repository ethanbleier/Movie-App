package com.example.movie.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.movie.data.UserDao;
import com.example.movie.data.UserRoomDatabase;
import com.example.movie.model.User;

import java.util.List;

class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.UserDao();
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
}