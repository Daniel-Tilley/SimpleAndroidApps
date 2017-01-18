package com.example.danieltilley.theweatherapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Date;

import Util.Utils;
import data.CityPreference;
import data.JsonWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

public class MainActivity extends Activity {

    //instance variables

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private Button changeCity;
    private String city;
    private CityPreference cityPref;

    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialise();
        renderWeatherData(cityPref.getCity());

        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }//end onCreate

    private void initialise() {

        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbNailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);
        changeCity = (Button) findViewById(R.id.changeCity);

        cityPref = new CityPreference(MainActivity.this);
    }//end initialise



    public void renderWeatherData(String city){

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});
    }


    private void showInputDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change City");

        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Bray,IE");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CityPreference cityPref = new CityPreference(MainActivity.this);
                cityPref.setCity(cityInput.getText().toString());

                String newCity = cityPref.getCity();

                renderWeatherData(newCity);
            }
        });

        builder.show();

    }

    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            iconView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return downlaodImage(strings[0]);
        }

        private Bitmap downlaodImage (String code) {
            String imageURL = Utils.ICON_URL + code + ".png";

            Log.v("Image",imageURL);

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("Image",imageURL);
            }
            return bitmap;
        }
    }


    private class WeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... strings) {

            String data = (new WeatherHttpClient().getWeatherData(strings[0]));

            weather = JsonWeatherParser.getWeather(data);

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);


            //date format
            DateFormat df = DateFormat.getTimeInstance();

            String sunriseDate = df.format(new Date(weather.place.getSunrise()));
            String sunsetDate = df.format(new Date(weather.place.getSunset()));
            String updateDate = df.format(new Date(weather.place.getLastUpdate()));

            //Decimal format
            DecimalFormat decimalFormat =  new DecimalFormat("#.#");

            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            cityName.setText(weather.place.getCity() + " , " + weather.place.getCountry());
            temp.setText("" + tempFormat + " °C");
            humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + " %");
            pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            wind.setText("Wind: " + weather.wind.getSpeed() + " mps");
            sunrise.setText("Sunrise: " + sunriseDate);
            sunset.setText("Sunset: " + sunsetDate);
            updated.setText("Last Updated: " + updateDate);
            description.setText("Condition: " + weather.currentCondition.getCondition() +
            " (" +  weather.currentCondition.getDescription() + ")");

            //run task to get image
            DownloadImageAsyncTask downloadImage = new DownloadImageAsyncTask();
            downloadImage.execute(weather.currentCondition.getIcon());
        }
    }//end WeatherTask
}
