package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<ScheduleCard> myScheduleCardList = new ArrayList<>();
    public static final String TAG = "MyLog";

    public static String jsonFile;

    public static String currentDate, selectedDate, simpleCurrentDate, simpleSelectedDate;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new newEvent());

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
//        String res = null;

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


                readList.add(new ScheduleCard(title, category, description, date, time, startTime, endTime,label, note, totalHr, totalMin));
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

//        WriteSchedule ws = new WriteSchedule();
//        simpleSelectedDate = ScheduleFragment.getDateFromString(selectedDate);
//        String tempDate;
//
//        //First write schedule to our file at the day we're at
//        if (myScheduleCardList.size() > 0) {
//            tempDate = myScheduleCardList.get(0).getCardDate();
//            ws.writeSchedule(myScheduleCardList, ScheduleFragment.getDateFromString(tempDate));
//        }
//
//        myScheduleCardList.clear();
//
//        myScheduleCardList = readSchedule(simpleSelectedDate, getBaseContext());
        Log.d("EEEEEEEEEEEEEEEE", selectedDate);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ScheduleFragment.newInstance(selectedDate))
                .commit();
    }

    public static String getPath(String date) {
        return "/data/data/lifemanager.edu.calvin.cs262.teamgg.lifemanager/files/" + date + ".json";
    }
}
