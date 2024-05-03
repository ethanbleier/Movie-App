package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reviews",
    foreignKeys = {
        @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "userId"),
        @ForeignKey(entity = Movie.class,
            parentColumns = "id",
            childColumns = "movieId")
    })
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "movieId")
    private int movieId;

    @ColumnInfo(name = "content")
    private String content;

    public Review(int userId, int movieId, String content) {
        this.userId = userId;
        this.movieId = movieId;
        this.content = content;
    }
}
