package com.example.dangfiztssi.flicks.API;

import com.example.dangfiztssi.flicks.models.NowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DangF on 10/12/16.
 */

public interface MovieApi {

    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();
}
