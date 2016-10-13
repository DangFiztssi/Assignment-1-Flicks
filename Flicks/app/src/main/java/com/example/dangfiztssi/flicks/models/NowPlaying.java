package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DangF on 10/12/16.
 */

public class NowPlaying implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("results")
    private List<Movie> mMovies;

    public NowPlaying(List<Movie> mMovies) {
        this.mMovies = mMovies;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }
}
