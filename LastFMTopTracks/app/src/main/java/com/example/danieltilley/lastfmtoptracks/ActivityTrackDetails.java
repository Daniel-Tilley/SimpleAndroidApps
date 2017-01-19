package com.example.danieltilley.lastfmtoptracks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import model.Song;

public class ActivityTrackDetails extends AppCompatActivity {

    private Song song;
    private String trackName;
    private int trackRank;
    private int numOfListens;
    private String artistName;
    private String artistURL;
    private String artistImageURL;
    private Button button;
    private NetworkImageView artistImage;
    ImageLoader imageLoader =  AppController.getInstance().getImageLoader();

    private TextView trackNameTextView;
    private TextView trackRankTextView;
    private TextView numOfListensTextView;
    private TextView artistNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        initialise();
    }

    private void initialise() {

        song = (Song) getIntent().getSerializableExtra("songObj");

        trackName = song.getTackName();
        trackRank = song.getTrackRank();
        numOfListens = song.getNumListeners();
        artistName = song.getArtistName();
        artistURL = song.getArtistURL();
        artistImageURL = song.getArtistImageURL();
        setTitle(artistName + ": " + trackName);
        button = (Button) findViewById(R.id.detailsButton);
        button.setText("Check out more from " + artistName);

        artistNameTextView = (TextView) findViewById(R.id.artistName_detailsPage);
        numOfListensTextView = (TextView) findViewById(R.id.numPlays_detailsPage);
        trackNameTextView = (TextView) findViewById(R.id.trackName_detailsPage);
        trackRankTextView = (TextView) findViewById(R.id.rank_detailsPage);
        artistImage = (NetworkImageView) findViewById(R.id.artistImage_detailsPage);

        artistNameTextView.setText("Artist: " + artistName);
        numOfListensTextView.setText("Number of plays: " + numOfListens);
        trackRankTextView.setText("Rank: " + trackRank);
        trackNameTextView.setText("Tack: " + trackName);
        artistImage.setImageUrl(artistImageURL, imageLoader);
    }

    public void goToArtistPage(View v){

        if (!artistURL.equals("")) {
            Intent i =  new Intent(Intent.ACTION_VIEW, Uri.parse(artistURL));
            startActivity(i);
        }else {
            Toast.makeText(getApplicationContext(), "Website not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_event_details, menu);
        return true;
    }//end onCreateOptionsMenu


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_websiteId) {

            String url = song.getTrackURL();

            if (!url.equals("")) {
                Intent i =  new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Website not available", Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
