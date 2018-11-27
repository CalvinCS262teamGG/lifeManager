package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.currentDate;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.selectedDate;


public class ScheduleFragment extends Fragment {

    String categoryString, newDay;
    TextView pickDate, pickStartTime, pickEndTime, dateText;
    EditText titleText;
    DialogFragment newFragment;

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
            newDay = getArguments().getString("selectedDate");
            selectedDate = newDay;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Schedule");



        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        dateText = rootView.findViewById(R.id.pickDateText);

        if (selectedDate.equals(currentDate)) {
            dateText.setText(selectedDate);
        }

        dateText.setText(currentDate);

        //initializing objects
//        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        ListView listView = rootView.findViewById(R.id.listView);

        //adding some values to our list
//        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));

        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.fragment_schedule_list, myScheduleCardList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment editFragment = EditCardFragment.newInstance(position);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, editFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                editCard(position);

                return false;
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


    private void openDetails(final int position) {
        ScheduleCard scheduleCard = myScheduleCardList.get(position);

        Dialog popupDialog = new Dialog(getContext());
        popupDialog.setContentView(R.layout.schedule_popup);

        //getting the view elements of the popup from the view
        TextView popupTextViewCategory = popupDialog.findViewById(R.id.itemCategory);
        TextView popupTextViewDescription = popupDialog.findViewById(R.id.itemDescription);
        TextView popupTextViewTime = popupDialog.findViewById(R.id.itemTime);

        //adding values to the popup item
        popupTextViewCategory.setText(scheduleCard.getCardTitle());
        popupTextViewDescription.setText(scheduleCard.getCardCategory());
        popupTextViewTime.setText(scheduleCard.getCardTime());

        Window window = popupDialog.getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.y = 50;
        popupDialog.show();

    }


    private void editCard(final int position) {
        ScheduleCard scheduleCard = myScheduleCardList.get(position);


        Dialog editDialog = new Dialog(getContext());
        editDialog.setContentView(R.layout.fragment_new_event);

        titleText = editDialog.findViewById(R.id.editTextTitle);

        pickDate = editDialog.findViewById(R.id.enterDate);
        pickStartTime = editDialog.findViewById(R.id.enterStart);
        pickEndTime = editDialog.findViewById(R.id.enterEnd);
        Button enterButton = editDialog.findViewById(R.id.enterButton);

        RadioGroup rg = editDialog.findViewById(R.id.eventCategory);


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

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFragment = new DatePickerFragment(pickDate);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                Log.d("pickDate", "DATE PICKED");
            }

        });


        pickStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFragment = new TimePickerFragment(pickStartTime);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });

        pickEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFragment = new TimePickerFragment(pickEndTime);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });

        editDialog.show();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {

            return true;
        }
        return false;
    }
}
