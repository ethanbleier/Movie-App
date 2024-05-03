package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratings",
    foreignKeys = {
        @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "userId"),
        @ForeignKey(entity = Movie.class,
            parentColumns = "id",
            childColumns = "movieId")
    })
public class Rating {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "movieId")
    private int movieId;

    @ColumnInfo(name = "rating")
    private float rating;

    @ColumnInfo(name = "timestamp")
    private long timestamp;
}
