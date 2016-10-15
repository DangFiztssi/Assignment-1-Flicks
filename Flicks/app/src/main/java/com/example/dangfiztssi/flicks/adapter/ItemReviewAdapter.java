package com.example.dangfiztssi.flicks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DangF on 10/15/16.
 */

public class ItemReviewAdapter extends RecyclerView.Adapter<ItemReviewAdapter.MyViewHolder> {

    private List<Review> reviewList = new ArrayList<>();

    public ItemReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvReview;
        TextView tvAuthor;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvReview = ((TextView) itemView.findViewById(R.id.tvReview));
            tvAuthor = ((TextView) itemView.findViewById(R.id.tvAuthorReview));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Review rv = reviewList.get(position);

        holder.tvReview.setText(rv.getContent());
        holder.tvAuthor.setText("- " + rv.getAuthor() + " -");
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
