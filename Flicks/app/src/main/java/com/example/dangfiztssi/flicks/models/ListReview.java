package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DangF on 10/15/16.
 */

public class ListReview {

    @SerializedName("results")
    List<Review> reviewList;

    public List<Review> getReviewList() {
        return reviewList;
    }
}
