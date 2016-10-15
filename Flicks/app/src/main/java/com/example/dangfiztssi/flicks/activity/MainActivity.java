package com.example.dangfiztssi.flicks.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.models.NowPlaying;
import com.example.dangfiztssi.flicks.presenter.MainActivityPresenter;
import com.example.dangfiztssi.flicks.utils.AppConstant;
import com.example.dangfiztssi.flicks.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMain) RecyclerView rvMain;
    @BindView(R.id.swipeRefresh)
    public SwipeRefreshLayout swipeRefreshLayout;

    public Dialog dialog;

    MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dialog = Utils.getWaitingDialog(this);

        presenter = new MainActivityPresenter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvMain.setLayoutManager(manager);
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(presenter.getAdapter());

        if(savedInstanceState != null){
            NowPlaying savedData = (NowPlaying) savedInstanceState.get(AppConstant.SAVE_DATA_KEY);

            presenter.setLstMain(savedData.getMovies());
        }
        else{
            dialog.show();
            presenter.getData();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(AppConstant.SAVE_DATA_KEY,new NowPlaying(presenter.getMovies()));
        super.onSaveInstanceState(outState);
    }

}
