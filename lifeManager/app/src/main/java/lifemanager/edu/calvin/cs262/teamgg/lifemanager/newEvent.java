package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.ScheduleFragment.scheduleCardList;

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

        Calendar cal = Calendar.getInstance();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        format.setTimeZone(cal.getTimeZone());

        String currentDate = format.format(cal.getTime());
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
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
//                enterData();
        }
    }


}
