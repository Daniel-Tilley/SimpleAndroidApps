package com.example.danieltilley.soundbox;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button playButton;
    private Button prevButton;
    private Button nextButton;
    private MediaPlayer mediaPlayer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.test);

        textView = (TextView) findViewById(R.id.artist);

        playButton = (Button) findViewById(R.id.playButton);
        prevButton = (Button) findViewById(R.id.prevButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.prevButton:{
                prevMusic();
                break;
            }

            case R.id.playButton:{

                if (mediaPlayer.isPlaying()){
                    pauseMusic();
                }

                else{
                    playMusic();
                }

                break;
            }

            case R.id.nextButton:{
                nextMusic();
                break;
            }
        }
    }

    void playMusic (){
        if (mediaPlayer != null){
            mediaPlayer.start();
            textView.setText("Its playing!");
            playButton.setBackground(getResources().getDrawable(android.R.drawable.ic_media_pause));
        }

    }

    void pauseMusic (){
        if (mediaPlayer != null){
            mediaPlayer.pause();
            textView.setText("Its paused!");
            playButton.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        }

    }


    void prevMusic (){
        if (mediaPlayer != null){
            mediaPlayer.seekTo(mediaPlayer.getDuration() - mediaPlayer.getDuration());
            textView.setText("Back to start!");
            playButton.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        }

    }

    void nextMusic (){
        if (mediaPlayer != null){
            mediaPlayer.seekTo(mediaPlayer.getDuration() + mediaPlayer.getDuration());
            textView.setText("Its over!");
            playButton.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        }

    }

    @Override
    protected void onDestroy() {

        //free up the player to free memory
        if (mediaPlayer != null && mediaPlayer.isPlaying()){

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }
}
