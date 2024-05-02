package com.example.myapplication.model;

import android.app.Application;
import com.example.myapplication.data.UserRepository;

public class UserManager {
    private final UserRepository userRepository;
    private static User loggedInUser;

    public UserManager(Application application) {
        userRepository = new UserRepository(application);
    }

    public static boolean login(String username, String password) {
        User user = UserRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            loggedInUser = user;
            return true;
        }
        return false;
    }



    public void logout() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}