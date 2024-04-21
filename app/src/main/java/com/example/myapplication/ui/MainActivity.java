package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserManager;

public class MainActivity extends AppCompatActivity {
    private final UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
    UserDao userDao = db.userDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username = findViewById(R.id.tvUsername);

        String currentUserUsername = getLoggedInUsername();
        if (currentUserUsername != null) {
            username.setText(currentUserUsername);
        } else {
            username.setText("DB ERROR\nNo Username Found");
        }
    }

    private String getLoggedInUsername() {
        User loggedInUser = UserManager.getLoggedInUser();
        if (loggedInUser != null) {
            return loggedInUser.getmUsername();
        }
        return "No user found";
    }
}