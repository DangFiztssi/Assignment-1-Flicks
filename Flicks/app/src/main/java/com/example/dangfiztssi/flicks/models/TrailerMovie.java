package com.example.dangfiztssi.flicks.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DangF on 10/14/16.
 */

public class TrailerMovie implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("id")
    String id;
    @SerializedName("iso_639_1")
    String iso639;
    @SerializedName("iso_3166_1")
    String iso3166;
    @SerializedName("key")
    String key;
    @SerializedName("Official Trailer")
    String name;
    @SerializedName("type")
    String type;

    public String getId() {
        return id;
    }

    public String getIso639() {
        return iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
