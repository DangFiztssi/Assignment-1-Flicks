package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DangF on 10/15/16.
 */

public class Review {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String urlReview;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrlReview() {
        return urlReview;
    }
}
