package com.daniellamishan;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.daniellamishan.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    VideoView bgVideo;
    TextView tv;
    Handler mainHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bgVideo = view.findViewById(R.id.home_video);
        tv = view.findViewById(R.id.home_tv);

        mainHandler = new Handler(Looper.getMainLooper());

        bgVideo.setVideoPath("android.resource://" + requireContext().getPackageName() + "/" + R.raw.bg2);
        Log.d("tag", "android.resource://" + requireContext().getPackageName() + "/" + R.raw.bg2);

        bgVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true); // Set the video to loop
                bgVideo.start(); // Start playing the video
            }
        });

        TranslateAnimation animation = new TranslateAnimation(1500, -1500, 0, 0);
        animation.setDuration(9000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());

        tv.setAnimation(animation);

        return view;
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        ExecutorService es;

        public MyAnimationListener() {
            this.es = Executors.newSingleThreadExecutor();
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleTextChange(animation);
                        }
                    });
                }
            });
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        private void handleTextChange(Animation animation) {
            if (tv.getText().equals("My Name Is Daniella Mishan")) {
                tv.setText("3rd year Computer Science student");
                tv.startAnimation(animation);
            } else if (tv.getText().equals("3rd year Computer Science student")) {
                tv.setText("Looking for an Android developer role");
                tv.startAnimation(animation);
            } else {
                tv.setText("My Name Is Daniella Mishan");
                tv.startAnimation(animation);
            }
        }
    }
}
