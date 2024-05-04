package com.example.agricare;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.agricare.models.Agriculture;
import com.google.android.material.appbar.MaterialToolbar;

public class VideoViewActivity extends AppCompatActivity {
    private static final String TAG = "VideoViewActivity";
    private static final String AGRICULTURE = "agriculture";
    private VideoView videoView;
    private MaterialToolbar toolbar;
    private Agriculture agriculture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoView = findViewById(R.id.videoView);
        toolbar = findViewById(R.id.topAppBar);
        agriculture = (Agriculture) getIntent().getSerializableExtra(AGRICULTURE);
        toolbar.setTitle(agriculture.getLabel());
        toolbar.setTitleCentered(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.setNavigationOnClickListener(v -> finish());
        initVideoPlayer();
    }

    private void initVideoPlayer(){
        Log.d(TAG, "initVideoPlayer: ");
        MediaController mediaController = new MediaController(this);
        mediaController.setEnabled(true);
        int rawId = getResources().getIdentifier(agriculture.getImage(), "raw", getPackageName());
        /*if(rawId == 0){
            Log.d(TAG, "initVideoPlayer: rawId not found");
            videoView.setVisibility(View.GONE);
        }*/
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+rawId);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnInfoListener((mediaPlayer, what, extra) -> {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
//                    b.videoLoading.setVisibility(View.GONE);
                    return true;
                }
                case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
//                    b.videoLoading.setVisibility(View.VISIBLE);
                    return true;
                }
            }
            return false;
        });
        videoView.setOnCompletionListener(mediaPlayer -> {
            Log.d(TAG, "setOnCompletionListener: ");
        });
        videoView.setOnPreparedListener(mp -> {
            Log.d(TAG, "setOnPrepare");
            videoView.start();
           /* b.progressBar.setVisibility(View.GONE);
            b.videoView.seekTo(position);
            if(position == 0)
                b.videoView.start();
            else
                b.videoView.pause();*/
        });
        videoView.setOnErrorListener((mediaPlayer, i, i1) -> {
//            b.progressBar.setVisibility(View.GONE);
            Log.d(TAG, "setOnErrorListener: ");
            return false;
        });
    }
}
