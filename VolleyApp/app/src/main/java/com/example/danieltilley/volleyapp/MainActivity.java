package com.example.danieltilley.volleyapp;

//This app demonstrates the use of volley to deal with JSON requests

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String url_arr = "https://jsonplaceholder.typicode.com/posts"; //Fake rest API link (array)
    private String url_obj = "http://api.fixer.io/latest"; //API for exchange rates (object)
    private String url_string = "http://magadistudio.com/complete-android-developer-course-source-files/string.html"; //String in html file
    private String image_url = "http://i.imgur.com/e69n7Hj.jpg"; //URL containing an image

    private TextView currency;
    private NetworkImageView networkImageView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currency = (TextView) findViewById(R.id.currencyText);
        networkImageView = (NetworkImageView) findViewById(R.id.imageView);
        imageView = (ImageView) findViewById(R.id.imageView1);

        getJsonArray(url_arr);
        getJsonObject(url_obj);
        getJsonString(url_string);
        getNetworkImageView(image_url);
        getImage(image_url);

    }//end onCreate


    //get image the normal way
    private void getImage(String url){

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                imageView.setImageBitmap(response.getBitmap());
            }//end on response

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }//end getImage


    //Method to get image using networkImageView
    private void getNetworkImageView(String url){

        //ImageLoader class to get image
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        networkImageView.setImageUrl(url, imageLoader);
    }//end getNetworkImageView


    //Method to get String via Json
    private void getJsonString(String url) {

        //create request using volley
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    //Log Response
                    Log.v("String Response", response.toString());

                }catch (Exception e) {
                    e.printStackTrace();
                }//end catch
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Log the response in Volley
                VolleyLog.d("MainActivity", error.getMessage());
            }
        });

        //Use singleton to add the request to a queue.
        //Singleton will then process the request and retrieve the json
        AppController.getInstance().addToRequestQueue(request);
    }//end


    //Method to get a JSON object
    private void getJsonObject(String url) {

        //create request using volley
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    //JSON Object to hold object responce
                    JSONObject ratesObject = response.getJSONObject("rates");

                    //Get the desired exchange rate
                    String currencyText = ratesObject.getString("GBP");

                    //update the text on screen

                    currency.setText("GBP: " + currencyText);

                    Log.v("Rate", currencyText);

                }catch (JSONException e) {
                    e.printStackTrace();
                }//end catch
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Log the response in Volley
                VolleyLog.d("MainActivity", error.getMessage());
            }
        });

        //Use singleton to add the request to a queue.
        //Singleton will then process the request and retrieve the json
        AppController.getInstance().addToRequestQueue(request);

    }//end getJsonObject


    //Method to return a jason array
    private void getJsonArray(String url) {

        //Create a request using volley
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override

            //Provides a json array (response)
            public void onResponse(JSONArray response) {

                //To get an individual object, just loop through data
                for (int i = 0; i < response.length(); i++){

                    //Try catch to get current item into an object
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        //Create new variables
                        String title = jsonObject.getString("title");
                        String id = jsonObject.getString("id");
                        String body = jsonObject.getString("body");

                        //Log output
                        Log.v(
                                "Title is: ", title + "\n" +
                                        "Id is: " + id + "\n" +
                                        "Body is: " + body
                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.v("Data From the web", response.toString());
            }

            //Method to catch errors
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                //Log the response in Volley
                VolleyLog.d("MainActivity", error.getMessage());
            }
        });

        //Use singleton to add the request to a queue.
        //Singleton will then process the request and retrieve the json
        AppController.getInstance().addToRequestQueue(request);
    }//end getJsonArray
}
