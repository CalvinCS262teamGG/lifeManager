package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter extends ArrayAdapter<ScheduleCard> {

    //the list values in the List of type hero

    //activity context
    Activity activity;
    View myview;
    ArrayList<ScheduleCard> scheduleCardList;



    //the layout resource file for the list items
    int resource;


    //constructor initializing the values

    public MyListAdapter(Activity activity, int resource, ArrayList<ScheduleCard> scheduleCardList) {
        super(activity, resource, scheduleCardList);
        this.activity = activity;
        this.resource = resource;
        this.scheduleCardList = scheduleCardList;

    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        //getting the view
        myview = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView textViewCategory = myview.findViewById(R.id.itemCategory);
        TextView textViewDescription = myview.findViewById(R.id.itemDescription);
        TextView textViewTime = myview.findViewById(R.id.itemTime);
//        final TextView textViewOptions = myview.findViewById(R.id.textViewOptions);


        //getting the scheduleCard of the specified position
        final ScheduleCard scheduleCard = scheduleCardList.get(position);

        //adding values to the list item
        textViewCategory.setText(scheduleCard.getCardTitle());
        textViewDescription.setText(scheduleCard.getCardCategory());
        textViewTime.setText(scheduleCard.getCardTime());

        //finally returning the view
        return myview;
    }
}
