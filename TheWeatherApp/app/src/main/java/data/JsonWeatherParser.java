package data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by danieltilley on 18/01/2017.
 */

//parsing class for HTTP requested data (Json)

public class JsonWeatherParser {

    public static Weather getWeather(String data){

        Weather weather = new Weather();

        //create JSON object from data
        try {

            //create JsonObject from data we received through http
            JSONObject jsonObject = new JSONObject(data);

            //create new place
            Place place = new Place();

            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat",coordObj));
            place.setLon(Utils.getFloat("lon",coordObj));

            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastUpdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));

            //Create new weather
            //Weather is in an array in Json
            weather.place = place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0); //get first object at array position 0

            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            //Create new wind
            JSONObject windObject = Utils.getObject("wind", jsonObject);

            weather.wind.setSpeed(Utils.getFloat("speed", windObject));
            weather.wind.setDeg((Utils.getFloat("deg", windObject)));

            //Create Clouds
            JSONObject cloudsObject = Utils.getObject("clouds", jsonObject);

            weather.clouds.setPrecipitation(Utils.getInt("all", cloudsObject));

            //Create Main Details
            JSONObject jsonMain = Utils.getObject("main", jsonObject);

            weather.currentCondition.setTemperature(Utils.getFloat("temp",jsonMain));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min",jsonMain));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max",jsonMain));
            weather.currentCondition.setHumidity(Utils.getFloat("humidity", jsonMain));
            weather.currentCondition.setPressure(Utils.getFloat("pressure", jsonMain));


            //return weather object that holds all parsed data
            return weather;
        } catch (JSONException e) {
            Log.v("JSON Parser", "Error parsing data " + e.toString());
        }

        return null;
    }
}

/*

Example of Json response


{
   "coord":{
      "lon":-0.13,
      "lat":51.51
   },

   "weather":[
      {
         "id":800,
         "main":"Clear",
         "description":"clear sky",
         "icon":"01d"
      }
   ],

   "base":"stations",

   "main":{
      "temp":277.38,
      "pressure":1038,
      "humidity":56,
      "temp_min":276.15,
      "temp_max":279.15
   },

   "visibility":10000,

   "wind":{
      "speed":2.1,
      "deg":60
   },

   "clouds":{
      "all":0
   },

   "dt":1484749200,

   "sys":{
      "type":1,
      "id":5091,
      "message":0.0048,
      "country":"GB",
      "sunrise":1484726155,
      "sunset":1484756816
   },

   "id":2643743,

   "name":"London",

   "cod":200
}

 */
