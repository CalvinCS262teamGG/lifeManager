package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment {

    public static ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Schedule");

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);


        //initializing objects
//        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        //adding some values to our list
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));
        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));


        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.fragment_schedule_list, scheduleCardList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
        return rootView;
    }


}
