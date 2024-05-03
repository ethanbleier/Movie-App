package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static volatile RatingRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RatingRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RatingRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RatingRoomDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
