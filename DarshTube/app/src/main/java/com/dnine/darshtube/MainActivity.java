package com.dnine.darshtube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void playv(View view){
        VideoView ironn = findViewById(R.id.ironvideo);
        ironn.setVideoPath("android.resource://" + getPackageName() + "/" +R.raw.iron);
        MediaController mediaController = new MediaController(this);
        ironn.setMediaController(mediaController);
        mediaController.setAnchorView(ironn);
//        ironn.start();
    }
}