package com.example.dangfiztssi.flicks.presenter;

import com.example.dangfiztssi.flicks.API.MovieApi;
import com.example.dangfiztssi.flicks.activity.DetailMovieActivity;
import com.example.dangfiztssi.flicks.adapter.ItemReviewAdapter;
import com.example.dangfiztssi.flicks.models.ListReview;
import com.example.dangfiztssi.flicks.models.Review;
import com.example.dangfiztssi.flicks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DangF on 10/13/16.
 */

public class DetailMoviePresenter {

    DetailMovieActivity activity;
    ItemReviewAdapter reviewAdapter;
    List<Review> reviewList = new ArrayList<>();


    public DetailMoviePresenter(DetailMovieActivity activity) {
        this.activity = activity;
        reviewAdapter = new ItemReviewAdapter(reviewList);
    }

    public ItemReviewAdapter getReviewAdapter() {
        return reviewAdapter;
    }

    public void getReviewData(int id) {
        MovieApi client = Utils.getRetrofit().create(MovieApi.class);
        Call<ListReview> call = client.getReview(id);
        call.enqueue(new Callback<ListReview>() {
            @Override
            public void onResponse(Call<ListReview> call, Response<ListReview> response) {
//                Log.e("onResponse", response + "");
                fetchReview(response);
            }

            @Override
            public void onFailure(Call<ListReview> call, Throwable t) {
            }
        });
    }

    private void fetchReview(Response<ListReview> response) {
        reviewList.clear();
        reviewList.addAll(response.body().getReviewList());
        reviewAdapter.notifyDataSetChanged();
    }
}
