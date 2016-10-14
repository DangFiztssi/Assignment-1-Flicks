package com.example.dangfiztssi.flicks.presenter;

import android.app.Activity;

import com.example.dangfiztssi.flicks.API.MovieApi;
import com.example.dangfiztssi.flicks.models.ListTrailer;
import com.example.dangfiztssi.flicks.models.TrailerMovie;
import com.example.dangfiztssi.flicks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DangF on 10/14/16.
 */

public class TrailerMoviePresenter {
    private static final String TAG = "TrailerMoviePresenter";
    Activity activity;
    List<TrailerMovie> trailerMovieList = new ArrayList<>();

    public TrailerMoviePresenter(Activity activity) {
        this.activity = activity;
    }

    public void getSourceYoutube(String id, final SimpleCallBack cb) {

        MovieApi cl = Utils.getRetrofit().create(MovieApi.class);
        cl.getTrailer(id).enqueue(new Callback<ListTrailer>() {
            @Override
            public void onResponse(Call<ListTrailer> call, Response<ListTrailer> response) {
                fetchData(response);
                cb.onSuccess();
            }

            @Override
            public void onFailure(Call<ListTrailer> call, Throwable t) {
                cb.onFailure();
            }
        });
    }

    public String getFirstResource(){
        if(trailerMovieList.size() > 1)
        return trailerMovieList.get(0).getKey();
        else  return "";
    }

    public List<TrailerMovie> getTrailerMovieList(){
        return trailerMovieList;
    }

    private void fetchData(Response<ListTrailer> response) {
        trailerMovieList.clear();
        trailerMovieList.addAll(response.body().getTrailerMovies());
    }
}
