package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import lifemanager.edu.calvin.cs262.teamgg.lifemanager.dummy.DummyContent;
import lifemanager.edu.calvin.cs262.teamgg.lifemanager.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Schedule");

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);


        //initializing objects
        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        //adding some values to our list
        scheduleCardList.add(new ScheduleCard("Exercise", "Self-development", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("CS 262", "Indirect Performance", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Study CS 262", "Direct Performance", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Dinner", " Personal", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Sleep", "Etc", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Work", "Etc", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Work", "Etc", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Work", "Etc", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Work", "Etc", "7:30 AM - 8:30 AM"));
        scheduleCardList.add(new ScheduleCard("Work", "Etc", "7:30 AM - 8:30 AM"));


        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.fragment_schedule_list, scheduleCardList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
        return rootView;
    }
}
