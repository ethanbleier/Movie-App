package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ReviewRoomDatabase extends RoomDatabase {
    public abstract ReviewDao reviewDao();
    private static volatile  ReviewRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReviewRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReviewRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ReviewRoomDatabase.class, "review_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
