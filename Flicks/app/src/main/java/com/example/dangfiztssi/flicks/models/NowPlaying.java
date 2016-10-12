package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DangF on 10/12/16.
 */

public class NowPlaying {

    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }
}
