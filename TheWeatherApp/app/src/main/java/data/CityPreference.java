package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by danieltilley on 18/01/2017.
 */

public class CityPreference {

    SharedPreferences pref;

    public CityPreference(Activity activity){
        pref = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity(){
        return pref.getString("city", "Bray,IE");
    }

    public void setCity(String city){
        pref.edit().putString("city", city).commit();
    }
}
