package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.newEvent.currentDate;


public class ScheduleFragment extends Fragment {

    String categoryString;
    TextView pickDate, pickStartTime, pickEndTime;
    EditText titleText;
    DialogFragment newFragment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Schedule");

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);


        //initializing objects
//        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        //adding some values to our list
//        scheduleCardList.add(new ScheduleCard("1", "Exercise", "Self-development", "DESCRIPTION", "October 9", "7:30 AM - 8:30 AM","LABEL", "note"));

        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.fragment_schedule_list, myScheduleCardList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetails(position);
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                editCard(position);

                return false;
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

        titleText = (EditText) editDialog.findViewById(R.id.editTextTitle);

        pickDate = (TextView) editDialog.findViewById(R.id.enterDate);
        pickStartTime = (TextView) editDialog.findViewById(R.id.enterStart);
        pickEndTime = (TextView) editDialog.findViewById(R.id.enterEnd);
        Button enterButton = (Button) editDialog.findViewById(R.id.enterButton);

        RadioGroup rg = (RadioGroup) editDialog.findViewById(R.id.eventCategory);


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

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("enterButton", "ENTER PRESSED");
//                String title = titleText.getText().toString() ;
////                String category;
//                String category = categoryString;
////                String date;
//                String time = (pickStartTime.getText() + " - " + pickEndTime.getText());
//
//                newEvent.time cardTime = new newEvent.time(pickStartTime.getText().toString(), pickEndTime.getText().toString());
//
//                String cardStart = cardTime.getCardStart();
//                String cardEnd = cardTime.getCardEnd();
//                int totalHr = cardTime.getTotalHr();
//                int totalMin = cardTime.getTotalMin();
//
////                String label;
////                String note;
//                if (!title.equals("") & category != null) {
//                    myScheduleCardList.add(new ScheduleCard(title, category, "Description", "October 9", time, cardStart, cardEnd, "LABEL", "note", totalHr, totalMin));
//
//                    Log.d(TAG, "pickStartTime" + pickStartTime.getText().toString() );
//                    Log.d(TAG, "pickEndTime" + pickEndTime.getText().toString() );
//
//                    sortScheduleCard();
//
//                    WriteSchedule  ws = new WriteSchedule();
//                    ws.writeSchedule();
//                }
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.detach(this).attach(this).commit();
//                enterData();

            }

        });

        editDialog.show();

    }



}
