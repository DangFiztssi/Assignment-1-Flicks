package com.example.dangfiztssi.flicks.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.dangfiztssi.flicks.API.MovieApi;
import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.databinding.ActivityMainBinding;
import com.example.dangfiztssi.flicks.models.NowPlaying;
import com.example.dangfiztssi.flicks.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private MovieApi mMovieApi;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mMovieApi = RetrofitUtils.getRetrofit().create(MovieApi.class);
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                Log.d("response", String.valueOf(response.isSuccessful()) + "\n" + response.body().getMovies());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.e("error", t.getMessage() + "");
            }
        });

    }
}
