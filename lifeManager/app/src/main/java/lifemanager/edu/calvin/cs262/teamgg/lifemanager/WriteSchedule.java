package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

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

    public void writeSchedule(ArrayList<ScheduleCard> myCards, String myDate) {
        JSONObject obj = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();
            for (int j = 0; j < myCards.size(); j++)
            {
                JSONObject sObject = new JSONObject();
                sObject.put("title", myCards.get(j).getCardTitle());
                sObject.put("category", myCards.get(j).getCardCategory());
                sObject.put("description", myCards.get(j).getCardDescription());
                sObject.put("date", myCards.get(j).getCardDate());
                sObject.put("time", myCards.get(j).getCardTime());
                sObject.put("startTime", myCards.get(j).getCardStartTime());
                sObject.put("endTime", myCards.get(j).getCardEndTime());
                sObject.put("label", myCards.get(j).getCardLabel());
                sObject.put("note", myCards.get(j).getCardNote());
                sObject.put("totalHr", myCards.get(j).getCardTotalHr());
                sObject.put("totalMin", myCards.get(j).getCardTotalMin());


                jArray.put(sObject);
            }
            obj.put("date", myDate);
            obj.put("data", jArray);

            System.out.println(obj.toString());

            try {
                Writer output = null;
                File file = new File(MainActivity.getPath(myDate));

                output = new BufferedWriter(new FileWriter(file));
                output.write(obj.toString());
                output.close();
            } catch (Exception e) {}

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
