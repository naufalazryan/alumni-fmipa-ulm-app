package com.naufalazryan.alumnimipaulm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.naufalazryan.alumnimipaulm.biodata.BiodataActivity;

public class AboutActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        youtubePlayerView = findViewById(R.id.youtube_player_view);


        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("ZdRou0Mf2mk");
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Inisialisasi Gagal", Toast.LENGTH_SHORT).show();
            }
        };


        youtubePlayerView.initialize("AIzaSyDo_SDa4yinCXWkt3RCYFX-5J9uMjrsTwQ", listener);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, BiodataActivity.class));
        overridePendingTransition(R.anim.slide_from_top, android.R.anim.accelerate_decelerate_interpolator);
    }
}