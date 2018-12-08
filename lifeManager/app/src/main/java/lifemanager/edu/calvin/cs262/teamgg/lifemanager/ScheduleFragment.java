package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.currentDate;

public class ScheduleFragment extends Fragment {

    String givenDate, simpleDate;
    public String previous;
    TextView dateText;
    ArrayList<ScheduleCard> mySchedule;

    public ScheduleFragment() {

    }

    public static ScheduleFragment newInstance(String date) {

        ScheduleFragment mySchedule = new ScheduleFragment();

        // Supply position input as argument
        Bundle args = new Bundle();
        args.putString("selectedDate", date);
        mySchedule.setArguments(args);

        return mySchedule;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            givenDate = getArguments().getString("selectedDate");
            simpleDate = getDateFromString(givenDate);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Schedule");



        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        dateText = rootView.findViewById(R.id.pickDateText);

        Calendar cal = Calendar.getInstance();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        format.setTimeZone(cal.getTimeZone());

        currentDate = format.format(cal.getTime());

        dateText.setText(givenDate);

        //initializing objects
        ListView listView = rootView.findViewById(R.id.listView);

        //adding some values to our list
        mySchedule = MainActivity.readSchedule(simpleDate,getContext());

        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.fragment_schedule_list, mySchedule);

        //attaching adapter to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment editFragment = EditCardFragment.newInstance(position, givenDate);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, editFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newDateFragment = new DatePickerFragment(dateText);
                newDateFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        return rootView;
    }

    public static String getDateFromString(String givenDate) {
        givenDate = givenDate.substring(givenDate.indexOf(" ") + 1);
        //November 27, 2018
        String dayYear = givenDate.substring(givenDate.indexOf(" ") + 1);
        //27, 2018
        String year = dayYear.substring(dayYear.indexOf(" ") + 1);

        String day = dayYear.substring(0, dayYear.indexOf(","));

        if (day.length() < 2) {
            day = "0" + day;
        }

        String month = givenDate.substring(0, givenDate.indexOf(" "));

        switch (month) {
            case "January":
                month = "1";
                break;
            case "February":
                month = "2";
                break;
            case "March":
                month = "3";
                break;
            case "April":
                month = "4";
                break;
            case "May":
                month = "5";
                break;
            case "June":
                month = "6";
                break;
            case "July":
                month = "7";
                break;
            case "August":
                month = "8";
                break;
            case "September":
                month = "9";
                break;
            case "October":
                month = "10";
                break;
            case "November":
                month = "11";
                break;
            case "December":
                month = "12";
                break;
        }
        givenDate = month + day + year;
        return givenDate;
    }

}
