package com.example.dangfiztssi.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.models.Movie;

import butterknife.BindView;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.img_video)
    ImageView imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent i =getIntent();
        Bundle bundle = i.getExtras();
        Movie m = (Movie) bundle.get("movie");

//        Glide.with(this)
//                .load(m.getBackdrop())
//                .into(imgVideo);
    }
}
