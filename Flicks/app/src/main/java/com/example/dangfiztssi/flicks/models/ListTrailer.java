package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DangF on 10/14/16.
 */

public class ListTrailer {
    @SerializedName("results")
    private List<TrailerMovie> trailerMovies;

    public List<TrailerMovie> getTrailerMovies() {
        return trailerMovies;
    }

    public void setTrailerMovies(List<TrailerMovie> trailerMovies) {
        this.trailerMovies = trailerMovies;
    }
}
