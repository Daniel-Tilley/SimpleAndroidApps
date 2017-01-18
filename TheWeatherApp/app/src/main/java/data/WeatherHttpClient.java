package data;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by danieltilley on 18/01/2017.
 */

//Class that uses HTTP to get data

public class WeatherHttpClient {

    public String getWeatherData(String place){

        HttpURLConnection connection = null; //connection
        InputStream inputStream = null; //input data

        try {

            //setup connection
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + place + Utils.API_KEY)).openConnection();
            connection.setRequestMethod("GET"); //HTTP GET Request
            connection.setDoInput(true); //receive data
            connection.setDoOutput(true); //send data
            connection.connect(); //open connection

            //Read response
            StringBuffer stringBuffer = new StringBuffer(); //buffer to hold read data
            inputStream = connection.getInputStream(); //input stream to hold server responce

            //Readable class used to store data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;

            //add terminating characters to each line
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\r\n");
            }//end while

            //close stream and connection
            inputStream.close();
            connection.disconnect();

            //return the read string

            String data = stringBuffer.toString();
            return data;

        }//end try

        catch (IOException e) {
            e.printStackTrace();
        }//end catch

        //return null if nothing to read
        return null;
    }//end getWeatherData
}
