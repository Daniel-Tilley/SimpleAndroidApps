package com.example.danieltilley.lastfmtoptracks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import data.CustomListviewAdapter;
import model.Song;
import util.Prefs;

public class MainActivity extends AppCompatActivity {

    private CustomListviewAdapter adapter;
    private ArrayList<Song> songs = new ArrayList<>();
    private String urlLeft = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=";
    private String urlRight = "&api_key=fab9c240e1270cdd99e50e827bc641e5&format=json";
    private ListView listView;
    private TextView selectedCity;
    private ProgressDialog pDialog;
    private String startCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise variables
        initialise();

        showSongs(startCountry);
    }//end onCreate


    //-----------------------------------------------------------------------------------------------------------------------
    // DATA
    //-----------------------------------------------------------------------------------------------------------------------
    private void initialise(){

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListviewAdapter(MainActivity.this, R.layout.custom_row, songs);
        listView.setAdapter(adapter);

        Prefs prefs = new Prefs(MainActivity.this);
        startCountry = prefs.getCountry();

        selectedCity = (TextView) findViewById(R.id.selectedLocationText);
        selectedCity.setText("Selected country: " + startCountry);
    }//end initialise()


    //-----------------------------------------------------------------------------------------------------------------------
    // NETWORK DATA
    //-----------------------------------------------------------------------------------------------------------------------
    private void showSongs(String city) {

        //Check if we have internet access
        if (isNetworkConnected()){
            //clear data first
            songs.clear();

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

            String finalUrl = urlLeft+city+urlRight;

            JsonObjectRequest eventsRequest = new JsonObjectRequest(Request.Method.GET,
                    finalUrl, (JSONObject)null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    hidePDialog();

                    try {
                        JSONObject songsObject = response.getJSONObject("tracks");

                        JSONArray songsArray = songsObject.getJSONArray("track");

                        for (int i = 0; i < songsArray.length(); i++) {
                            JSONObject jsonObject = songsArray.getJSONObject(i);

                            //Get artist object
                            JSONObject artistObject = jsonObject.getJSONObject("artist");
                            String artistName = artistObject.getString("name");
                            String artistURL =  artistObject.getString("url");

                            //Get attr Object
                            JSONObject attObject = jsonObject.getJSONObject("@attr");
                            int trackRank = attObject.getInt("rank") + 1;


                            //Get Image array
                            JSONArray imageArray = jsonObject.getJSONArray("image");
                            JSONObject largeImage = imageArray.getJSONObject(3);
                            //Get actual image url
                            String artistImageURL = largeImage.getString("#text");

                            //Get remaining attributes
                            String trackName = jsonObject.getString("name");
                            int numListeners = jsonObject.getInt("listeners");
                            String trackURL = jsonObject.getString("url");

                            //Create new song object and add data to it
                            Song song = new Song();

                            song.setArtistImageURL(artistImageURL);
                            song.setArtistName(artistName);
                            song.setArtistURL(artistURL);
                            song.setNumListeners(numListeners);
                            song.setTackName(trackName);
                            song.setTrackRank(trackRank);
                            song.setTrackURL(trackURL);

                            //add song to array list
                            songs.add(song);
                        }

                        //method used to auto refresh list
                        adapter.notifyDataSetChanged();

                    }//end try
                    catch (JSONException e) {
                        e.printStackTrace();
                    }//end catch

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    hidePDialog();

                }
            });

            AppController.getInstance().addToRequestQueue(eventsRequest);
        }//end if internet access

        else{
            Toast.makeText(getApplicationContext(),"No Internet Access",Toast.LENGTH_SHORT).show();
        }//end else internet access
    }//end showSongs


    //-----------------------------------------------------------------------------------------------------------------------
    // ALERT DIALOG
    //-----------------------------------------------------------------------------------------------------------------------
    private void showInputDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change Country");

        final EditText countryInput = new EditText(MainActivity.this);
        countryInput.setInputType(InputType.TYPE_CLASS_TEXT);
        countryInput.setHint("Ireland");
        builder.setView(countryInput);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //check for empty string
                if (countryInput.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Error, you left the country blank!",Toast.LENGTH_LONG).show();
                }//end if

                else{
                    //update shared prefs and on screen text
                    Prefs countryPreference = new Prefs(MainActivity.this);
                    String newCountry = countryInput.getText().toString();
                    countryPreference.setCountry(newCountry);
                    selectedCity.setText("Selected country: " + newCountry);


                    //re-render everything again
                    showSongs(newCountry);
                }//end else
            }//end onClick
        });

        builder.show();
    }//end showInputDialog


    //-----------------------------------------------------------------------------------------------------------------------
    // MENU OPTIONS
    //-----------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//end onCreateOptionsMenu


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_locationId) {

            showInputDialog();

        }//end if

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected


    //-----------------------------------------------------------------------------------------------------------------------
    // NETWORK
    //-----------------------------------------------------------------------------------------------------------------------
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }//end isNetworkConnected


    //-----------------------------------------------------------------------------------------------------------------------
    // OTHER
    //-----------------------------------------------------------------------------------------------------------------------
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }//end if
    }//end hidePDialog
}//end MainActivity
