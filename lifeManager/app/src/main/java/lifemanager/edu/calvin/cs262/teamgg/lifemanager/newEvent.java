package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.TAG;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.currentDate;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.simpleCurrentDate;

public class newEvent extends Fragment implements View.OnClickListener {

    private String id;
    private EditText title;
    private String category;
    private String description;
    private String date;
    private String startTime;
    private String endTime;
    private String label;
    private String note;
    private static int position;

    public newEvent() {

    }

    public static newEvent newEventInstance(int cardPosition) {
        position = cardPosition;
        newEvent myEvent = new newEvent();

        // Supply position input as argument
        Bundle args = new Bundle();
        args.putInt("position", position);
        myEvent.setArguments(args);

        return myEvent;
    }

<<<<<<< HEAD
    EditText titleText;
=======
    EditText titleText, activityText, labelText, noteText;
>>>>>>> f72853f464cbb26ffbcef6b14503b86450ceb1ae

    TextView pickDate, pickStartTime, pickEndTime;

    RadioGroup rg;

    String categoryString;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("New Event");

        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        titleText = rootView.findViewById(R.id.editTextTitle);

        pickDate = rootView.findViewById(R.id.enterDate);
        pickStartTime = rootView.findViewById(R.id.enterStart);
        pickEndTime = rootView.findViewById(R.id.enterEnd);
        Button   enterButton = rootView.findViewById(R.id.enterButton);
        activityText = rootView.findViewById(R.id.editTextActivity);
        labelText = rootView.findViewById(R.id.editTextLabel);
        noteText = rootView.findViewById(R.id.editTextNote);

        rg = rootView.findViewById(R.id.eventCategory);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ( i == R.id.direct) {
                    categoryString = "Direct";
                } else if (i == R.id.indirect) {
                    categoryString = "Indirect";
                } else if (i == R.id.personal) {
                    categoryString = "Personal";
                } else if (i == R.id.selfDev) {
                    categoryString = "Self-development";
                } else if (i == R.id.etc) {
                    categoryString = "Etc";
                }
            }
        });


        Calendar cal = Calendar.getInstance();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        format.setTimeZone(cal.getTimeZone());

        currentDate = format.format(cal.getTime());
        pickDate.setText(currentDate);

        String ampm = "";
        String defTIme = (cal.get(Calendar.HOUR) == 0) ?"12":cal.get(Calendar.HOUR)+"";
        if (cal.get(Calendar.AM_PM) == Calendar.AM) {
            ampm = "AM";
        } else if (cal.get(Calendar.AM_PM) == Calendar.PM) {
            ampm = "PM";
        }
        pickStartTime.setText(defTIme +":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + ampm );
        pickEndTime.setText(defTIme +":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + ampm);

        pickDate.setOnClickListener(this);
        pickStartTime.setOnClickListener(this);
        pickEndTime.setOnClickListener(this);
        enterButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        DialogFragment newFragment;
        switch (view.getId()) {
            case R.id.enterDate:
                newFragment = new DatePickerFragment(pickDate);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;

            case R.id.enterStart:
                newFragment = new TimePickerFragment(pickStartTime);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                break;

            case R.id.enterEnd:
                newFragment = new TimePickerFragment(pickEndTime);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                break;

            case R.id.enterButton:
                String title = titleText.getText().toString() ;
                String category = categoryString;
                String activity = labelText.getText().toString();
                String label = labelText.getText().toString();
                String note = noteText.getText().toString();
                String time = (pickStartTime.getText() + " - " + pickEndTime.getText());
                String date = pickDate.getText().toString();

                Log.d("DATE", date);
                time cardTime = new time(pickStartTime.getText().toString(), pickEndTime.getText().toString());

                String cardStart = cardTime.getCardStart();
                String cardEnd = cardTime.getCardEnd();
                int totalHr = cardTime.getTotalHr();
                int totalMin = cardTime.getTotalMin();

                if (totalHr < 0 || totalMin < 0) {
                    Toast toast = Toast.makeText(getContext(), "End time must be after the start time!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 450);
                    toast.show();
                    break;
                } else if (title.equals("")) {
                    Toast toast = Toast.makeText(getContext(), "A Title is necessary to create a new item!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 450);
                    toast.show();
                    break;
                } else if (category == null) {
                    Toast toast = Toast.makeText(getContext(), "A Category is necessary to create a new item!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 450);
                    toast.show();
                    break;
                }

                ScheduleCard newCard = new ScheduleCard(title, category, activity, date, time, cardStart, cardEnd, label, note, totalHr, totalMin);

                ArrayList<ScheduleCard> tempList;
                tempList = MainActivity.readSchedule(ScheduleFragment.getDateFromString(date), getContext());
                tempList.add(newCard);
                tempList = sortScheduleCard(tempList);
                WriteSchedule  ws = new WriteSchedule();
                ws.writeSchedule(tempList, ScheduleFragment.getDateFromString(date));

                Toast toast = Toast.makeText(getContext(), title + " has been added to the schedule" , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 450);
                toast.show();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                
                break;
        }
    }

    // output the start and end time and the total hours and total mins for calculation of analytics
    public static class time {

        String cardStart, cardEnd;
        int totalHr, totalMin;

        public time(String start, String end) {
            int colIndex = start.lastIndexOf(":");
            String startHr = start.substring(0,colIndex);
            String startMin = start.substring(colIndex+1,colIndex+3);
            String startAm_pm = start.substring(colIndex+4,colIndex+6);

            int startHrInt = Integer.parseInt(startHr);
            int startMinInt = Integer.parseInt(startMin);
            if (startAm_pm.equals("PM")) {
                if (startHrInt != 12) {
                    startHrInt = startHrInt + 12;
                }
            } else if (startAm_pm.equals("AM")) {
                if (startHrInt == 12) {
                    startHrInt = 0;
                }
            }
            Log.d(TAG, "check!!!: " + Integer.toString(startHrInt) + startMin + startAm_pm);

            colIndex = end.lastIndexOf(":");
            String endHr = end.substring(0,colIndex);
            String endMin = end.substring(colIndex+1,colIndex+3);
            String endAm_pm = end.substring(colIndex+4,colIndex+6);

            int endHrInt = Integer.parseInt(endHr);
            int endMinInt = Integer.parseInt(endMin);
            if (endAm_pm.equals("PM")) {
                if (endHrInt != 12) {
                    endHrInt = endHrInt + 12;
                }
            } else if (endAm_pm.equals("AM")) {
                if (endHrInt == 12) {
                    endHrInt = 0;
                }
            }
            Log.d(TAG, "check!!!: " + Integer.toString(endHrInt) + endMin + endAm_pm);

            totalHr = endHrInt - startHrInt;

            totalMin = endMinInt - startMinInt;
            if (totalMin < 0) {
                totalHr = totalHr - 1;
                totalMin = totalMin + 60;
            }

            totalMin = totalMin + (totalHr * 60);

            Log.d(TAG, "check!!!: hr diff: " + Integer.toString(totalHr)  + " min diff: " + Integer.toString(totalMin));

            cardStart = Integer.toString(startHrInt) + startMin;
            cardEnd = Integer.toString(endHrInt) + endMin;
        }

        public String getCardStart() {
            return cardStart;
        }
        public String getCardEnd() {
            return cardEnd;
        }
        public int getTotalHr() {
            return totalHr;
        }
        public int getTotalMin() {
            return totalMin;
        }

    }

    // sort the schedule card list
    public static ArrayList<ScheduleCard> sortScheduleCard(ArrayList<ScheduleCard> sortList) {
        Collections.sort(sortList, new Comparator<ScheduleCard>(){
            @Override
            public int compare(ScheduleCard s1, ScheduleCard s2) {

                if (Integer.parseInt(s1.getCardStartTime()) > Integer.parseInt(s2.getCardStartTime())) {
                    return 1;
                } else if (Integer.parseInt(s1.getCardStartTime()) < Integer.parseInt(s2.getCardStartTime())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return sortList;
    }

}
