package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by danieltilley on 18/01/2017.
 */

//Static values

public class Utils {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q="; //base URL for api call
    public static final String ICON_URL = "http://openweathermap.org/img/w/"; //icon URL for api call
    public static final String API_KEY = "&APPID=df17f8bb6d60783ecb3ef48b2b36cfbc";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{

        JSONObject jsonObj = jsonObject.getJSONObject(tagName);
        return jsonObj;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{

        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{

        return (float) jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}
