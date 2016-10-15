package com.example.dangfiztssi.flicks.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.activity.TrailerMovieActivity;
import com.example.dangfiztssi.flicks.models.TrailerMovie;
import com.example.dangfiztssi.flicks.utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DangF on 10/15/16.
 */

public class ItemTrailerAdapter extends RecyclerView.Adapter<ItemTrailerAdapter.MyVieHolder> {

    List<TrailerMovie> trailerMovieList = new ArrayList<>();
    Activity activity;

    public ItemTrailerAdapter(List<TrailerMovie> trailerMovieList, Activity activity) {
        this.trailerMovieList = trailerMovieList;
        this.activity = activity;
    }

    public static class MyVieHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.trailerThumbnail)
        ImageView thumbnailView;
        CardView card;

        public MyVieHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView)

            thumbnailView = (ImageView) itemView.findViewById(R.id.trailerThumbnail);
            card = (CardView) itemView.findViewById(R.id.cardTrailer);
        }
    }

    @Override
    public MyVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyVieHolder holder, int position) {
        final TrailerMovie trailerMovie = trailerMovieList.get(position);

        Picasso.with(activity)
                .load(AppConstant.BASE_YOUTUBE_THUMBNAIL + trailerMovie.getKey() + "/0.jpg")
                .placeholder(R.drawable.ic_place_holder)
                .into(holder.thumbnailView);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(activity, TrailerMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(TrailerMovieActivity.REQUEST, TrailerMovieActivity.FROM_DETAIL_KEY);
                bundle.putString(TrailerMovieActivity.YOUTUBE_SRC_KEY, trailerMovie.getKey());
                i.putExtra(TrailerMovieActivity.DATA_KEY, bundle);
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerMovieList.size();
    }
}
