package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * this class us used for our online help documentation page
 */
public class MyDialogFragment extends DialogFragment {
    private String actionString;
    static MyDialogFragment newInstance(String actionTitle) {
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", actionTitle);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null){
            actionString = getArguments().getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help_dialog, container, false);
        TextView tv = v.findViewById(R.id.textView);


        tv.setText(actionString);

        switch (actionString){
            case "New Event":
                newEvent(tv);
                break;
            case "Schedule":
                schedule(tv);
                break;
            case "Analytics":
                analytics(tv);
                break;
        }


        Log.e("this", actionString);
        return v;
    }

    private void schedule(TextView tv) {
        String text = "Section 1: Edit Item on Schedule\n" +
                "\n" +
                "You can the edit feature of LifeManager to edit items that have previously been added to your schedule.\n" +
                "\n" +
                "Imagine that you want to edit any part of an item on your schedule. The detailed steps in this section show you how to:\n" +
                "\t1. initiate the editing process,\n" +
                "\t2. change the item information,\n" +
                "\t3. save any changes to schedule.\n" +
                "\n" +
                "Step 1:\n" +
                "\tTap on the Schedule tab on the bottom left of the screen.\n" +
                "\n" +
                "Step 2:\n" +
                "\tTap on the Schedule item that you wish to change.\n" +
                "\n" +
                "Step 3:\n" +
                "\tTap on any information that you wish to change and change it.\n" +
                "\n" +
                "Step 4:\n" +
                "\tTap on the \"Save Changes\" button.\n" +
                "\n" +
                "Now you are done!";
        tv.setText(text);

    }

    private void analytics(TextView tv) {
        String text = "View Analytics \nImagine that you want to be able to see an overview of how you spend your time. " +
                "The detailed steps in this section show you how to do this:\n" +
                "\t1. Click on the analysis button at the bottom of the screen.\n" +
                "\t2. Look through the list of analytics.\n" +
                "\t3. View how you spend your time in each of the different categories.\n";
        tv.setText(text);

    }

    private void newEvent(TextView tv) {

        tv.setText("Enter New Event\n" +
                "\n" +
                "You can create a new event through the new event page and add it to the schedule of your choice of date.\n" +
                "\n" +
                "Imagine that you want to add a new event to the current schedule. The detailed steps show you how to:\n" +
                "1. Click New Event on the bottom bar, to move to the new event page,\n" +
                "2. Enter the title of your new event,\n" +
                "3. Select one of the given areas to categorize the new event,\n" +
                "4. Select date, start time and end time for the new event,\n" +
                "5. Optionally enter a detail description into 'What activity',\n" +
                "6. Optionally enter a label and a note according to user’s purpose,\n" +
                "7. Click the enter button to add the new event into the schedule.");
    }
}