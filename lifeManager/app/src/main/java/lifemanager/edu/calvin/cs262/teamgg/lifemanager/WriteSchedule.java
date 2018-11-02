package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.TAG;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.jsonFile;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.newEvent.currentDate;

public class WriteSchedule {

    public void writeSchedule() {
        JSONObject obj = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            for (int j = 0; j < myScheduleCardList.size(); j++)
            {
                JSONObject sObject = new JSONObject();
                sObject.put("title", myScheduleCardList.get(j).getCardTitle());
                sObject.put("category", myScheduleCardList.get(j).getCardCategory());
                sObject.put("description", myScheduleCardList.get(j).getCardDescription());
                sObject.put("date", myScheduleCardList.get(j).getCardDate());
                sObject.put("time", myScheduleCardList.get(j).getCardTime());
                sObject.put("startTime", myScheduleCardList.get(j).getCardStartTime());
                sObject.put("endTime", myScheduleCardList.get(j).getCardEndTime());
                sObject.put("label", myScheduleCardList.get(j).getCardLabel());
                sObject.put("note", myScheduleCardList.get(j).getCardNote());

                jArray.put(sObject);
            }
            obj.put("date", currentDate);
            obj.put("data", jArray);

            System.out.println(obj.toString());

            try {
                Writer output = null;
                File file = new File(jsonFile);

                output = new BufferedWriter(new FileWriter(file));
                output.write(obj.toString());
                output.close();
            } catch (Exception e) {}

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
