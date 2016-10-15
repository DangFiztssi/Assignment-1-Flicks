package com.example.dangfiztssi.flicks.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.adapter.ItemTrailerAdapter;
import com.example.dangfiztssi.flicks.models.Movie;
import com.example.dangfiztssi.flicks.models.TrailerMovie;
import com.example.dangfiztssi.flicks.presenter.DetailMoviePresenter;
import com.example.dangfiztssi.flicks.presenter.SimpleCallBack;
import com.example.dangfiztssi.flicks.presenter.TrailerMoviePresenter;
import com.example.dangfiztssi.flicks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    //    @BindView(R.id.img_video)
//    ImageView imgVideo;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_overview)
    TextView tvOverview;
    @BindView(R.id.rvTrailers)
    RecyclerView rvTrailers;
    @BindView(R.id.rvReview)
    RecyclerView rvReview;

    TrailerMoviePresenter presenter;
    DetailMoviePresenter detailMoviePresenter;
    Dialog loading;
    ItemTrailerAdapter adapter;
    List<TrailerMovie> trailerMovieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        loading = Utils.getWaitingDialog(this);
        loading.show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Movie");

        Intent i = getIntent();
        Bundle bundle = i.getExtras().getBundle("data");
        Movie m = (Movie) bundle.get("movie");

        presenter = new TrailerMoviePresenter(this);
        detailMoviePresenter = new DetailMoviePresenter(this);
//        adapter = new ItemTrailerAdapter(trailerMovieList, this);

        //RecyclerView trailers
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTrailers.setLayoutManager(manager);
        rvTrailers.setItemAnimator(new DefaultItemAnimator());
        rvTrailers.setAdapter(presenter.getAdapter());


        //RecyclerView reviews
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvReview.setLayoutManager(manager1);
        rvReview.setItemAnimator(new DefaultItemAnimator());
        rvReview.setAdapter(detailMoviePresenter.getReviewAdapter());
        rvReview.setNestedScrollingEnabled(false);

        getData(m.getId() + "");
        detailMoviePresenter.getReviewData(m.getId());

        bindData(m);
    }

    public void getData(String id) {
        presenter.getSourceYoutube(id, new SimpleCallBack() {
            @Override
            public void onSuccess() {
//                trailerMovieList.addAll(presenter.getTrailerMovieList());
//                adapter.notifyDataSetChanged();
                if (loading.isShowing())
                    loading.dismiss();
            }

            @Override
            public void onFailure(Exception e) {

                if (loading.isShowing())
                    loading.dismiss();
            }

            @Override
            public void onFailure() {

                if (loading.isShowing())
                    loading.dismiss();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindData(Movie movie) {
        getSupportActionBar().setTitle(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverview());
    }
}
