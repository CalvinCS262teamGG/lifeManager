package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.TAG;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;


//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter extends ArrayAdapter<ScheduleCard> {

    //the list values in the List of type hero

    //activity context
    Activity activity;
    View myview;



    //the layout resource file for the list items
    int resource;


    //constructor initializing the values

    public MyListAdapter(Activity activity, int resource, ArrayList<ScheduleCard> scheduleCardList) {
        super(activity, resource, scheduleCardList);
        this.activity = activity;
        this.resource = resource;
//        this.scheduleCardList = myScheduleCardList;

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
        final ScheduleCard scheduleCard = myScheduleCardList.get(position);

        //adding values to the list item
        textViewCategory.setText(scheduleCard.getCardTitle());
        textViewDescription.setText(scheduleCard.getCardCategory());
        textViewTime.setText(scheduleCard.getCardTime());



//        textViewOptions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            //open the options menu
//                //openOptions(scheduleCardList.indexOf(scheduleCard));
//
//            PopupMenu popup = new PopupMenu(getContext(), textViewOptions);
//
//            popup.inflate(R.menu.card_menu);
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.action_view:
////                            openDetails(position);
//                            break;
//                        case R.id.action_edit:
////                            ScheduleFragment.editCard(position);
//                            break;
//                        case R.id.action_share:
//                            //shareCard(position);
//                            break;
//                        case R.id.action_delete:
//                            deleteCard(position);
//                            break;
//                    }
//                    return false;
//                }
//            });
//
//            //displaying the popup
//            popup.show();
//
//            }
//
//        });

        //finally returning the view
        return myview;
    }


    //this method will remove the item from the list
//    public void deleteCard(final int position) {
//        //Creating an alert dialog to confirm the deletion
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
//        builder.setTitle("Are you sure you want to delete this?");
//
//        //if the response is positive in the alert
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                //removing the item
//                myScheduleCardList.remove(position);
//
//                //reloading the list
//                notifyDataSetChanged();
//
//                WriteSchedule  ws = new WriteSchedule();
//                ws.writeSchedule();
//            }
//        });
//
//        //if response is negative nothing is being done
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//
//        //creating and displaying the alert dialog
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

//    private void openDetails(final int position) {
//        ScheduleCard scheduleCard = scheduleCardList.get(position);
//
//        Dialog popupDialog = new Dialog(getContext());
//        popupDialog.setContentView(R.layout.schedule_popup);
//
//        //getting the view elements of the popup from the view
//        TextView popupTextViewCategory = popupDialog.findViewById(R.id.itemCategory);
//        TextView popupTextViewDescription = popupDialog.findViewById(R.id.itemDescription);
//        TextView popupTextViewTime = popupDialog.findViewById(R.id.itemTime);
//
//        //adding values to the popup item
//        popupTextViewCategory.setText(scheduleCard.getCardTitle());
//        popupTextViewDescription.setText(scheduleCard.getCardCategory());
//        popupTextViewTime.setText(scheduleCard.getCardTime());
//
//        Window window = popupDialog.getWindow();
//
//        WindowManager.LayoutParams wlp = window.getAttributes();
//
//        wlp.gravity = Gravity.TOP;
//        wlp.y = 50;
//        popupDialog.show();
//
//    }

//
//    private void editCard(final int position) {
//        ScheduleCard scheduleCard = myScheduleCardList.get(position);
//
////        DialogAdapter editDialogAdaptor = DialogAdapter.newInstance(position);
////
////        Dialog editDialog = new Dialog(getContext());
////        if (editDialogAdaptor.getMyView().equals(null)) {
////            editDialog.setContentView(editDialogAdaptor.getMyView());
////        }
//
//        Dialog editDialog = new Dialog(getContext());
//        editDialog.setContentView(R.layout.fragment_new_event);
//
//        titleText = (EditText) editDialog.findViewById(R.id.editTextTitle);
//
//        pickDate = (TextView) editDialog.findViewById(R.id.enterDate);
//        pickStartTime = (TextView) editDialog.findViewById(R.id.enterStart);
//        pickEndTime = (TextView) editDialog.findViewById(R.id.enterEnd);
//        Button enterButton = (Button) editDialog.findViewById(R.id.enterButton);
//
//        RadioGroup rg = (RadioGroup) editDialog.findViewById(R.id.eventCategory);
//
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if ( i == R.id.direct) {
//                    categoryString = "Direct";
//                } else if (i == R.id.indirect) {
//                    categoryString = "Indirect";
//                } else if (i == R.id.personal) {
//                    categoryString = "Personal";
//                } else if (i == R.id.selfDev) {
//                    categoryString = "Self-development";
//                } else if (i == R.id.etc) {
//                    categoryString = "Etc";
//                }
//            }
//        });
//
//
//        Calendar cal = Calendar.getInstance();
//        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
//        format.setTimeZone(cal.getTimeZone());
//
//        currentDate = format.format(cal.getTime());
//        pickDate.setText(currentDate);
//
//        String ampm = "";
//        String defTIme = (cal.get(Calendar.HOUR) == 0) ?"12":cal.get(Calendar.HOUR)+"";
//        if (cal.get(Calendar.AM_PM) == Calendar.AM) {
//            ampm = "AM";
//        } else if (cal.get(Calendar.AM_PM) == Calendar.PM) {
//            ampm = "PM";
//        }
//        pickStartTime.setText(defTIme +":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + ampm );
//        pickEndTime.setText(defTIme +":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " " + ampm);
//
//        pickDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                newFragment = new DatePickerFragment(pickDate);
////                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
//                Log.d("pickDate", "DATE PICKED");
//            }
//
//        });
//
//
//        pickStartTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                newFragment = new TimePickerFragment(pickStartTime);
////                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
//                Log.d("pickDate", "DATE PICKED");
//            }
//
//        }
//        );
//        pickEndTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                newFragment = new TimePickerFragment(pickEndTime);
////                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
//                Log.d("endTime", "ENDTIME");
//            }
//
//        });
//
//        enterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("enterButton", "ENTER PRESSED");
////                String title = titleText.getText().toString() ;
//////                String category;
////                String category = categoryString;
//////                String date;
////                String time = (pickStartTime.getText() + " - " + pickEndTime.getText());
////
////                newEvent.time cardTime = new newEvent.time(pickStartTime.getText().toString(), pickEndTime.getText().toString());
////
////                String cardStart = cardTime.getCardStart();
////                String cardEnd = cardTime.getCardEnd();
////                int totalHr = cardTime.getTotalHr();
////                int totalMin = cardTime.getTotalMin();
////
//////                String label;
//////                String note;
////                if (!title.equals("") & category != null) {
////                    myScheduleCardList.add(new ScheduleCard(title, category, "Description", "October 9", time, cardStart, cardEnd, "LABEL", "note", totalHr, totalMin));
////
////                    Log.d(TAG, "pickStartTime" + pickStartTime.getText().toString() );
////                    Log.d(TAG, "pickEndTime" + pickEndTime.getText().toString() );
////
////                    sortScheduleCard();
////
////                    WriteSchedule  ws = new WriteSchedule();
////                    ws.writeSchedule();
////                }
////                FragmentTransaction ft = getFragmentManager().beginTransaction();
////                ft.detach(this).attach(this).commit();
////                enterData();
//
//            }
//
//        });
//
//        editDialog.show();
//
//    }



}
