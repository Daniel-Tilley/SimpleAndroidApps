package com.example.danieltilley.moodscanner;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//App runs through animations by frame

public class MainActivity extends Activity {

    private ImageView thumbPrint;
    private TextView result;
    private AnimationDrawable thumbAnimation; //animation class used for the animation
    private String[] moodResults;
    private Runnable mRunnable; //delay class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moodResults = new String[] {
          "Tired", "Happy", "Sad", "Angry", "Hungover", "Excited"
        };

        thumbPrint = (ImageView) findViewById(R.id.thumbPrint);
        thumbPrint.setBackgroundResource(R.drawable.thumb_animation);
        thumbAnimation = (AnimationDrawable) thumbPrint.getBackground();

        result = (TextView) findViewById(R.id.resultText);

        //Long click adds delay to click
        thumbPrint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //Start animation, show result (stop animation in method)
                thumbAnimation.start();
                showResult();
                return true;
            }
        });
    }//end onCreate

    public void showResult(){

        //used to have a delay on running code
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * moodResults.length);
                result.setText(moodResults[rand]);

                //stop animation
                thumbAnimation.stop();
            }
        };

        Handler mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000); //Handler used to add delay time of 5 seconds
    }
}
