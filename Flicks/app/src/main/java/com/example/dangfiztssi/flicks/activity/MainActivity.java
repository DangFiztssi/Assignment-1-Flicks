package com.example.dangfiztssi.flicks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.dangfiztssi.flicks.API.MovieApi;
import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.adapter.ItemMovieAdapter;
import com.example.dangfiztssi.flicks.models.Movie;
import com.example.dangfiztssi.flicks.models.NowPlaying;
import com.example.dangfiztssi.flicks.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieApi mMovieApi;

    ItemMovieAdapter adapter;
    RecyclerView rvMain;
    TextView tv;

    List<Movie> lstMain  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(R.layout.activity_main);

        rvMain = (RecyclerView) findViewById(R.id.rvMain);

        adapter = new ItemMovieAdapter(this,lstMain);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvMain.setLayoutManager(manager);
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adapter);

        mMovieApi = RetrofitUtils.getRetrofit().create(MovieApi.class);
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                lstMain.addAll(response.body().getMovies());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.e("error", t.getMessage() + "");
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
}
