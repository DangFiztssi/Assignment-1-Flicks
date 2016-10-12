package com.example.dangfiztssi.flicks.API;

import com.example.dangfiztssi.flicks.models.NowPlaying;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by DangF on 10/12/16.
 */

public interface MovieApi {

    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();


    @GET("{id}/trailers")
    Call<ResponseBody> getTrailer(@Path("id") Integer id);
}
