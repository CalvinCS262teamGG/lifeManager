package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter extends ArrayAdapter<ScheduleCard> {

    //the list values in the List of type hero
    List<ScheduleCard> scheduleCardList;

    //activity context
    Activity activity;

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
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView textViewCategory = view.findViewById(R.id.itemCategory);
        TextView textViewDescription = view.findViewById(R.id.itemDescription);
        TextView textViewTime = view.findViewById(R.id.itemTime);
        Button openOptions = view.findViewById(R.id.options);


        //getting the scheduleCard of the specified position
        final ScheduleCard scheduleCard = scheduleCardList.get(position);

        //adding values to the list item
        textViewCategory.setText(scheduleCard.getCardCategory());
        textViewDescription.setText(scheduleCard.getCardDescription());
        textViewTime.setText(scheduleCard.getCardTime());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Success??", "SUCCESS!!");
//                Log.d("Card Category", scheduleCard.getCardCategory());
                //create the popup window
                openDetails(scheduleCardList.indexOf(scheduleCard));
            }
        });

        openOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
//                Log.d("Success??", "SUCCESS!!");
//                Log.d("Card Options", scheduleCard.getCardCategory());
                openOptions(scheduleCardList.indexOf(scheduleCard));
            }
        });



        //finally returning the view
        return view;
    }


    //this method will remove the item from the list
    private void openOptions(final int position) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                scheduleCardList.remove(position);

                //reloading the list
                notifyDataSetChanged();
            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openDetails(final int position) {
        ScheduleCard scheduleCard = scheduleCardList.get(position);
        //adding a click listener to the button to open details
        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater

        Dialog popupDialog = new Dialog(getContext());
        popupDialog.setContentView(R.layout.schedule_popup);

        //getting the view elements of the popup from the view
        TextView popupTextViewCategory = popupDialog.findViewById(R.id.itemCategory);
        TextView popupTextViewDescription = popupDialog.findViewById(R.id.itemDescription);
        TextView popupTextViewTime = popupDialog.findViewById(R.id.itemTime);

        //adding values to the popup item
        popupTextViewCategory.setText(scheduleCard.getCardCategory());
        popupTextViewDescription.setText(scheduleCard.getCardDescription());
        popupTextViewTime.setText(scheduleCard.getCardTime());

        Window window = popupDialog.getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.y = 50;
        popupDialog.show();

    }
}
