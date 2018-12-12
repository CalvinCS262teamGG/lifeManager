package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class for using getting information from the players web service.
 */
class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String MONOPOLY_BASE_URL =  "https://calvincs262-fall2018-teamgg.appspot.com/lifemanager/v1/lifeuser"; //Monopoly API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string.


    static String getUserInfo(String queryString){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJSONString = null;


        try {

            URL requestURL;
                //turn the base url and parameters into the final URL string
                requestURL = new URL("https://calvincs262-fall2018-teamgg.appspot.com/lifemanager/v1/lifeusers");


            //create the connection and request the information from the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            resultJSONString = buffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }finally {

            //close the reader and url connections
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (resultJSONString == null){
                return null;
            }
            Log.d(LOG_TAG, resultJSONString);

        }
        return resultJSONString;
    }
}
