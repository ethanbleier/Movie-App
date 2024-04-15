/**
 * Movie class
 */

package com.example.movie.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {
    // Auto generate unique keys
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    private final String mMovie;

    public Movie(@NonNull String movie) {
        this.mMovie = movie;
    }

    @NonNull
    public String getMovie() {
        return this.mMovie;
    }
}
