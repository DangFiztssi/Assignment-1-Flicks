package com.example.dangfiztssi.flicks.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dangfiztssi.flicks.BR;
import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.activity.DetailMovieActivity;
import com.example.dangfiztssi.flicks.activity.MainActivity;
import com.example.dangfiztssi.flicks.models.Movie;
import com.example.dangfiztssi.flicks.presenter.MovieClickHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DangF on 10/12/16.
 */

public class ItemMovieAdapter  extends RecyclerView.Adapter<ItemMovieAdapter.MyViewHolder>{

    MainActivity activity;
    List<Movie> movieList;

    public ItemMovieAdapter(MainActivity activity, List<Movie> movieList) {
        this.movieList = movieList;
        this.activity = activity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ViewDataBinding binding;
        @BindView(R.id.img_play_trailer) ImageView playIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding(){
            return binding;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.getBinding().setVariable(BR.movie, movie);

        if(movie.isPopular())
            holder.playIcon.setVisibility(View.VISIBLE);
        else
            holder.playIcon.setVisibility(View.GONE);

        holder.getBinding().setVariable(BR.click, new MovieClickHandler() {
            @Override
            public void onClickTrailer(View view) {
                Toast.makeText(activity, "has click trailer", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(activity, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                i.putExtra("data",bundle);
                activity.startActivity(i);
            }

            @Override
            public void onClickDetail(View view) {
                Toast.makeText(activity, "has click detail", Toast.LENGTH_SHORT).show();
            }
        });

        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
