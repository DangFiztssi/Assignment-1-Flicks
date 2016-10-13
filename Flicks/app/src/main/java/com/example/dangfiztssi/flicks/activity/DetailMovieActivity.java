package com.example.dangfiztssi.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.img_video)
    ImageView imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

//        imgVideo = (ImageView) findViewById(R.id.img_video);

        Intent i =getIntent();
        Bundle bundle = i.getExtras().getBundle("data");
        Movie m = (Movie) bundle.get("movie");

        Log.e("movie", m + "");

        Glide.with(this)
                .load(m.getBackdrop())
                .into(imgVideo);
    }
}
