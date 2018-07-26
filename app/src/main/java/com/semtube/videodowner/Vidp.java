package com.semtube.videodowner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class Vidp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     // setContentView(R.layout.activity_vidp);
        String ur = getIntent().getStringExtra("ur");
        String ad = getIntent().getStringExtra("ad");
       /* JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.vp);
        jzVideoPlayerStandard.setUp(ur, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, ad);*/
        JZVideoPlayerStandard.startFullscreen(this, JZVideoPlayerStandard.class,ur,ad) ;
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
