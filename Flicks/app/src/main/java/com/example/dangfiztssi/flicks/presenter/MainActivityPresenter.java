package com.example.dangfiztssi.flicks.presenter;

import com.example.dangfiztssi.flicks.API.MovieApi;
import com.example.dangfiztssi.flicks.activity.MainActivity;
import com.example.dangfiztssi.flicks.adapter.ItemMovieAdapter;
import com.example.dangfiztssi.flicks.models.Movie;
import com.example.dangfiztssi.flicks.models.NowPlaying;
import com.example.dangfiztssi.flicks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DangF on 10/12/16.
 */

public class MainActivityPresenter {

    MainActivity activity;
    List<Movie> lstMain  = new ArrayList<>();
    ItemMovieAdapter adapter;
    private MovieApi mMovieApi;

    public MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
        adapter = new ItemMovieAdapter(activity,lstMain);
    }

    public void setLstMain(List<Movie> movies){
        lstMain.clear();
        lstMain.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    public ItemMovieAdapter getAdapter(){
        return adapter;
    }

    public void getData(){
        mMovieApi = Utils.getRetrofit().create(MovieApi.class);
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                fetchData(response);
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                if(activity.dialog.isShowing())
                    activity.dialog.dismiss();
            }
        });


//        mMovieApi.getTrailer(209112).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("response", String.valueOf(response.body()));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }

    public List<Movie> getMovies(){
        return lstMain;
    }

    private void fetchData(Response<NowPlaying> response){
        lstMain.addAll(response.body().getMovies());
        adapter.notifyDataSetChanged();
        if(activity.dialog.isShowing())
            activity.dialog.dismiss();

        activity.swipeRefreshLayout.setRefreshing(false);
    }
}
