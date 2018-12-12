package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<ScheduleCard> myScheduleCardList = new ArrayList<>();
    public static final String TAG = "MyLog";

    public static String jsonFile;

    public static String currentDate, selectedDate, simpleCurrentDate, simpleSelectedDate;




    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //needed to add to fix a network connect issue
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.activity_main);

        if (!isUser()) {
            // Open login page
            loadFragment(new LoginFragment());
        } else {
            loadFragment(new newEvent());
        }


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
        format.setTimeZone(cal.getTimeZone());


        simpleCurrentDate = format.format(cal.getTime());
        simpleSelectedDate = simpleCurrentDate;
        String dirPath = getFilesDir().getAbsolutePath();
        jsonFile = getPath(simpleCurrentDate);

        Log.d(TAG, " currentDate" +  simpleCurrentDate);
        Log.d(TAG, "dirPath" + dirPath);


        File file = new File("/data/data/lifemanager.edu.calvin.cs262.teamgg.lifemanager/files");

        if (!file.exists()) {
            file.mkdirs();
        }

        File schedule = new File(jsonFile);
        if (!schedule.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(schedule);
                fos.close();
            } catch (IOException e) {}
        } else {
            myScheduleCardList = readSchedule(simpleCurrentDate, getBaseContext());
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        myScheduleCardList.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myScheduleCardList.clear();
        myScheduleCardList = readSchedule(simpleCurrentDate, getBaseContext());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_schedule:
                    Calendar cal = Calendar.getInstance();
                    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
                    format.setTimeZone(cal.getTimeZone());

                    String tempDate = format.format(cal.getTime());

                    fragment = ScheduleFragment.newInstance(tempDate);
                    break;

                case R.id.navigation_new_event:
                    fragment = new newEvent();
                    break;

                case R.id.navigation_analytics:
                    fragment = new Analytics();
                    break;

            }
        return loadFragment(fragment);
    }

    public void setActionBarTitle(String title) { getSupportActionBar().setTitle(title); }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    public static ArrayList<ScheduleCard> readSchedule(String d, Context ctx) {
        String res = null;
        InputStream inputStream = null;
        ArrayList<ScheduleCard> readList = new ArrayList<ScheduleCard>();
        try{
            inputStream = ctx.openFileInput(d + ".json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String str ="";

            while((str = br.readLine()) != null){
                sb.append(str);
            }
            // store contents of json file into res
            res = sb.toString();
            JSONObject obj = new JSONObject(res);
            JSONArray jsonArray = new JSONArray(obj.getString("data"));
            for (int i = 0; i < jsonArray.length(); i++) {
                String title =  jsonArray.getJSONObject(i).getString("title");
                String category =  jsonArray.getJSONObject(i).getString("category");
                String description =  jsonArray.getJSONObject(i).getString("description");
                String date =  jsonArray.getJSONObject(i).getString("date");
                String time =  jsonArray.getJSONObject(i).getString("time");
                String startTime =  jsonArray.getJSONObject(i).getString("startTime");
                String endTime =  jsonArray.getJSONObject(i).getString("endTime");
                String label =  jsonArray.getJSONObject(i).getString("label");
                String note =  jsonArray.getJSONObject(i).getString("note");
                int totalHr =  jsonArray.getJSONObject(i).getInt("totalHr");
                int totalMin =  jsonArray.getJSONObject(i).getInt("totalMin");


                readList.add(new ScheduleCard(title, category, description, date, time, startTime, endTime, label, note, totalHr, totalMin));
                Log.d(TAG, "save!" + title );
            }
        } catch (Exception e) {
            Log.e("readSchedule", e.toString());
        }
        return readList;
    }

    public void reloadSchedule(View view) {
        TextView text = findViewById(R.id.pickDateText);
        selectedDate = text.getText().toString();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ScheduleFragment.newInstance(selectedDate))
                .commit();
    }

    public static String getPath(String date) {
        return "/data/data/lifemanager.edu.calvin.cs262.teamgg.lifemanager/files/" + date + ".json";
    }



    private boolean isUser() {
        String res = null;
        InputStream inputStream = null;
        ArrayList<ScheduleCard> readList = new ArrayList<ScheduleCard>();
        try{
            inputStream = openFileInput( "user.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String str ="";
            while((str = br.readLine()) != null){
                sb.append(str);
            }

            res = sb.toString();
            JSONObject obj = new JSONObject(res);

            String userName =  obj.getString("name");
            String userEmail =  obj.getString("email");


            // TODO: Here is where we would search the data base for name and email !!!!!
            return isNameAndEmailInDatabase(userName, userEmail);
        } catch (Exception e) {
            Log.e("readSchedule", e.toString());
            return false;
        }
    }

    private boolean isNameAndEmailInDatabase(String userName, String userEmail) {

        String origName = userName;
        String origEmail = userEmail;


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
                return false;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return false;
            }
            resultJSONString = buffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
            return false;

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
                return false;
            }
            Log.d("JSON", resultJSONString);
        }

        //get a JSON array of the data
        try {
            JSONObject jsonObject = new JSONObject(resultJSONString);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++){
                String name;
                String email;
                JSONObject userInfo = itemsArray.getJSONObject(i);

                try {
                    name = userInfo.getString("name");
                } catch (Exception e){
                    e.printStackTrace();
                    name = "no name";
                }
                try {
                    email = userInfo.getString("emailAddress");
                } catch (Exception e){
                    e.printStackTrace();
                    email = "no email address";
                }
                if (name.equals(origName)){
                    if (email.equals(origEmail)){
                        return true;
                    }
                }
            }
        } catch (Exception e){
            Log.e("isNameAndEmailInDataba", e.toString());
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void signIn(View view) {
        EditText nameText = findViewById(R.id.editName);
        EditText mailText = findViewById(R.id.editEmail);

        String userName = nameText.getText().toString();
        String userMail = mailText.getText().toString();

        JSONObject obj = new JSONObject();
        try {
            obj.put("name", userName);
            obj.put("email", userMail);

            System.out.println(obj.toString());

            try {
                Writer output = null;
                File file = new File(MainActivity.getPath("user"));

                output = new BufferedWriter(new FileWriter(file));
                output.write(obj.toString());
                output.close();
            } catch (Exception e) {}


        } catch (Exception e) {
            Log.e("Error with Signin", e.toString());
        }



        // TODO : This is where we push data to data base 'userName' and 'userMail' are the strings
        pushToServer(userName, userMail);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new newEvent())
                .commit();
    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void pushToServer(String userName, String userMail) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {

            URL requestURL;
            //turn the base url and parameters into the final URL string
            requestURL = new URL("https://calvincs262-fall2018-teamgg.appspot.com/lifemanager/v1/lifeuser");


            //create the connection and request the information from the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
//            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            String data = "{\"id\": \"20\", \"name\":\"" +  userName + "\"" + "," + "\"emailAddress\":\"" + userMail + "\"}";
            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            Log.e("output", data.getBytes().toString());
            OutputStream outputPost = new BufferedOutputStream(urlConnection.getOutputStream());
            outputPost.write(out);
//            writeStream(outputPost);
            outputPost.flush();
            outputPost.close();
            Log.d("POST REQUEST", "success maybe");
        } catch (IOException e) {
            Log.d("POST REQUEST", "failed");
            e.printStackTrace();

        }finally {
            Log.d("POST REQUEST", "finally");
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

        }

    }
*/
    private void pushToServer(String userName, String userMail) {
        JSONObject data = new JSONObject();
        try {
            data.put("emailAddress", userName);
            data.put("name", userMail);

        } catch (JSONException e) {
            Log.d("POST", "Failed to build request object");
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://calvincs262-fall2018-teamgg.appspot.com/lifemanager/v1/lifeuser", data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("POST SUCCESS", response.toString());
                        displayToast("Success");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayToast("Failed");
                Log.d("POST FAILED", error.toString());
            }
        });

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    public void helpButton(View view) {

        try{
            DialogFragment newFragment = MyDialogFragment.newInstance(getSupportActionBar().getTitle().toString());
            newFragment.show(getSupportFragmentManager(), "dialog");

        }catch (Exception e){
            Log.e("error", e.toString());
        }
    }
}

