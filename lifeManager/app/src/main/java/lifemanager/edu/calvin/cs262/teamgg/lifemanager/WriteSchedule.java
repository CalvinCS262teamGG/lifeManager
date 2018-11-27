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
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.currentDate;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.jsonFile;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;

/*
 * write arrayList of myScheduleCardList as a JSON format
 * the outputted JSON file is gonna look like
 * {
 * "date": "Wed, Oct 31, 2018", // from currentDate
 * "data": [
 *      {
 *      "title": "Exercise",
 *      "category": "Self-development",
 *      "description": "DESCRIPTION",
 *      "date": "Wed, Oct 31, 2018",
 *      "time": "7:30 AM - 1:30 PM",
 *      "startTime": "730",
 *      "endTime": "1330"
 *      "label": "Label",
 *      "note": "note"
 *      },
 *      {
 *      "title": "Exercise",
 *      "category": "Self-development",
 *      "description": "DESCRIPTION",
 *      "date": "Wed, Oct 31, 2018",
 *      "time": "2:30 PM - 3:30 PM",
 *      "startTime": "1430",
 *      "endTime": "1530",      // the time is 24hr base.
 *      "label": "Label",
 *      "note": "note"
 *      }
 *      ]
 * }
 *
 * the file will be stored in /data/data/lifemanager.edu.calvin.cs262.teamgg.lifemanager/files/ which is local storage.
 * the file name will be currentDate.json //note - currentDate = SimpleDateFormat("MMddyyyy")
 *
 *
 */

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
                sObject.put("totalHr", myScheduleCardList.get(j).getCardTotalHr());
                sObject.put("totalMin", myScheduleCardList.get(j).getCardTotalMin());


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
