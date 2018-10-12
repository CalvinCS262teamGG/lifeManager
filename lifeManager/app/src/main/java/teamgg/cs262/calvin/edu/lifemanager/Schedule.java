package teamgg.cs262.calvin.edu.lifemanager;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //initializing objects
        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);

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
        MyListAdapter adapter = new MyListAdapter(this, R.layout.layout_schedulecard, scheduleCardList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }


}
