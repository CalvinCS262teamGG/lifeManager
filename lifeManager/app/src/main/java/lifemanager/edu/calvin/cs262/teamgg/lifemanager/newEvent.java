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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class newEvent extends Fragment implements View.OnClickListener {

    private ArrayList<ScheduleCard> myScheduleCardList = MainActivity.myScheduleCardList;

    private String id;
    private EditText title;
    private String category;
    private String description;
    private String date;
    private String startTime;
    private String endTime;
    private String label;
    private String note;

    public newEvent() {

    }

    EditText titleText;

    TextView pickDate, pickStartTime, pickEndTime;

    RadioGroup rg;

    String categoryString;

    static String currentDate;


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("New Event");

        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        titleText = (EditText) rootView.findViewById(R.id.editTextTitle);

        pickDate = (TextView) rootView.findViewById(R.id.enterDate);
        pickStartTime = (TextView) rootView.findViewById(R.id.enterStart);
        pickEndTime = (TextView) rootView.findViewById(R.id.enterEnd);
        Button   enterButton = (Button) rootView.findViewById(R.id.enterButton);

        rg = (RadioGroup) rootView.findViewById(R.id.eventCategory);
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
//                String category;
                String description = categoryString;
//                String date;
                String time = (pickStartTime.getText() + " - " + pickEndTime.getText());
//                String label;
//                String note;
                if (!title.equals("") & description != null) {
                    myScheduleCardList.add(new ScheduleCard(title, "Self-development", description, "October 9", time, pickStartTime.getText().toString(), pickEndTime.getText().toString(), "LABEL", "note"));

                    WriteSchedule  ws = new WriteSchedule();
                    ws.writeSchedule();
                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
//                enterData();
        }
    }


}
