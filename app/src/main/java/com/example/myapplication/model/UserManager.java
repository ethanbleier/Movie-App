package com.example.myapplication.model;

import android.app.Application;
import com.example.myapplication.data.UserRepository;

public class UserManager {
    private final UserRepository userRepository;
    private static User loggedInUser;

    public UserManager(Application application) {
        userRepository = new UserRepository(application);
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            loggedInUser = user;
            // Perform additional login operations, if needed
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
        // Perform additional logout operations, if needed
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}