package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.data.model.Movie;
import com.example.myapplication.data.model.Rating;
import com.example.myapplication.data.model.User;
import com.example.myapplication.data.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Rating.class, User.class, Movie.class}, version = 2, exportSchema = false)
public abstract class RatingRoomDatabase extends RoomDatabase {
    public abstract RatingDao ratingDao();
    private static volatile RatingRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RatingRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RatingRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RatingRoomDatabase.class, "rating_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
