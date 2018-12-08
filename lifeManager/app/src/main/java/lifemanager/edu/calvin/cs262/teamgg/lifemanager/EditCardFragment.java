package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.simpleCurrentDate;


public class EditCardFragment extends android.support.v4.app.Fragment {

//    private OnFragmentInteractionListener mListener;
    private int position;
    String categoryString;
    TextView pickDate, pickStartTime, pickEndTime;
    EditText titleText, activity, labelText, noteText;
    DialogFragment newFragment;
    String givenDate, simpleDate;

    public EditCardFragment() {

    }


    public static EditCardFragment newInstance(int position, String date) {
        EditCardFragment fragment = new EditCardFragment();
        Bundle args = new Bundle();
        args.putInt("Position", position);
        args.putString("Date", date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("Position");
            givenDate = getArguments().getString("Date");
            simpleDate = ScheduleFragment.getDateFromString(givenDate);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_edit_card, container, false);

        final ScheduleCard scheduleCard = myScheduleCardList.get(position);

        titleText = view.findViewById(R.id.editTextTitle);
        pickDate = view.findViewById(R.id.enterDate);
        pickStartTime = view.findViewById(R.id.enterStart);
        pickEndTime = view.findViewById(R.id.enterEnd);
        activity = view.findViewById(R.id.editTextActivity);
        labelText = view.findViewById(R.id.editTextLabel);
        noteText = view.findViewById(R.id.editTextNote);

        Button enterButton = view.findViewById(R.id.enterButton);
        Button deleteButton = view.findViewById(R.id.deleteButton);
        enterButton.setText("Save Changes");
        RadioGroup rg = view.findViewById(R.id.eventCategory);

        titleText.setText(scheduleCard.getCardTitle());
        activity.setText(scheduleCard.getCardDescription());
        labelText.setText(scheduleCard.getCardLabel());
        noteText.setText(scheduleCard.getCardNote());
        pickDate.setText(scheduleCard.getCardDate());

        String startTime = scheduleCard.getCardStartTime();
        String endTime = scheduleCard.getCardEndTime();

        String startHour, startMinute, endHour, endMinute, startAMPM, endAMPM;

        startMinute = startTime.substring(startTime.length()-2);
        startHour = startTime.substring(0, startTime.length()-2);
        endMinute = endTime.substring(endTime.length()-2);
        endHour = endTime.substring(0, endTime.length()-2);

        if (Integer.parseInt(startHour) >= 12) {
            startHour = Integer.toString(Integer.parseInt(startHour) - 12);
            startAMPM = "PM";
        } else {
            startAMPM = "AM";
        }
        if (startHour.equals("0")) {
            startHour = "12";
        }

        if (Integer.parseInt(endHour) >= 12) {
            endHour = Integer.toString(Integer.parseInt(endHour) - 12);
            endAMPM = "PM";
        } else {
            endAMPM = "AM";
        }
        if (endHour.equals("0")) {
            endHour = "12";
        }

        pickStartTime.setText(startHour +":" + startMinute + " " + startAMPM );
        pickEndTime.setText(endHour +":" + endMinute +  " " + endAMPM);


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



        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFragment = new DatePickerFragment(pickDate);
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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
                newEvent.time cardTime = new newEvent.time(pickStartTime.getText().toString(), pickEndTime.getText().toString());
                int totalHr = cardTime.getTotalHr();
                int totalMin = cardTime.getTotalMin();

                scheduleCard.setCardTitle( titleText.getText().toString() );
                scheduleCard.setCardCategory( categoryString );
                scheduleCard.setCardDescription( activity.getText().toString() );
                scheduleCard.setCardTime( pickStartTime.getText() + " - " + pickEndTime.getText() );
                scheduleCard.setCardDate( pickDate.getText().toString() );
                scheduleCard.setCardLabel( labelText.getText().toString() );
                scheduleCard.setCardNote( noteText.getText().toString() );
                scheduleCard.setCardStartTime( cardTime.getCardStart() );
                scheduleCard.setCardEndTime( cardTime.getCardEnd() );
                scheduleCard.setCardTotalHr( totalHr );
                scheduleCard.setCardTotalMin( totalMin );

                newEvent.sortScheduleCard();

                WriteSchedule  ws = new WriteSchedule();
                ws.writeSchedule(myScheduleCardList, ScheduleFragment.getDateFromString(pickDate.getText().toString()));
                }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCard(position);
            }
        });

        return view;
    }

    //this method will remove the item from the list
    public void deleteCard(final int position) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                ArrayList<ScheduleCard> delList = MainActivity.readSchedule(simpleDate, getContext());
                Log.d("EEEEEEEEEEEEEEEEE", Integer.toString(delList.size()));

                delList.remove(position);
                //reloading the list
                WriteSchedule  ws = new WriteSchedule();
                ws.writeSchedule(delList, simpleDate);
                Log.d("EEEEEEEEEEEEEEEEE", Integer.toString(delList.size()));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, ScheduleFragment.newInstance(givenDate));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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


//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }



}
