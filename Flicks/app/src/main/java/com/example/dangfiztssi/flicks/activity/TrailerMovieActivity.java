package com.example.dangfiztssi.flicks.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.dangfiztssi.flicks.R;
import com.example.dangfiztssi.flicks.presenter.SimpleCallBack;
import com.example.dangfiztssi.flicks.presenter.TrailerMoviePresenter;
import com.example.dangfiztssi.flicks.utils.AppConstant;
import com.example.dangfiztssi.flicks.utils.Utils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerMovieActivity extends YouTubeBaseActivity {

    public static final String ID_MOVIE_KEY = "id_movie";
    public static final String REQUEST = "request";
    public static final String FROM_MAIN_KEY = "main";
    public static final String FROM_DETAIL_KEY = "detail";
    public static final String DATA_KEY= "data";
    public static final String YOUTUBE_SRC_KEY = "src_youtube";

    @BindView(R.id.player)
    YouTubePlayerView player;

    Dialog loading;

    TrailerMoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loading = Utils.getWaitingDialog(this);
        loading.show();

        setContentView(R.layout.activity_trailer_movie);
        ButterKnife.bind(this);

        presenter = new TrailerMoviePresenter(this);

        Bundle bundle = getIntent().getExtras().getBundle(DATA_KEY);
        switch (bundle.getString(REQUEST)) {
            case FROM_MAIN_KEY:
                String id = bundle.getString(ID_MOVIE_KEY);
                Log.e("src youtube", "onCreate: " + id );
                presenter.getSourceYoutube(id, new SimpleCallBack() {
                    @Override
                    public void onSuccess() {
                        loadVideo(presenter.getFirstResource());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        loading.dismiss();
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        loading.dismiss();
                        finish();
                    }
                });
                break;
            case FROM_DETAIL_KEY:
                loadVideo(bundle.getString(YOUTUBE_SRC_KEY));
                break;
            default:
                break;
        }


    }

    public void loadVideo(final String src) {
        player.initialize(AppConstant.YOUTUBE_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(loading.isShowing())
                    loading.dismiss();
                youTubePlayer.loadVideo(src);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
