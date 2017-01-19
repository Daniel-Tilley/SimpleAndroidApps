package util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by danieltilley on 19/01/2017.
 */

//Shared preferences class

public class Prefs {

    SharedPreferences preferences;

    public Prefs(Activity activity) {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }


    public void setCountry(String country) {
        preferences.edit().putString("country", country).commit();
    }

    //If user has not chose a city, return default!
    public String getCountry() {
        return preferences.getString("country", "Ireland");
    }
}
