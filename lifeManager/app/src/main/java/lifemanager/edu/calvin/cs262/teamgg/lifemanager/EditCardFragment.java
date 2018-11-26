package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.newEvent.currentDate;


public class EditCardFragment extends android.support.v4.app.Fragment {

//    private OnFragmentInteractionListener mListener;
    private int position;
    String categoryString;
    TextView pickDate, pickStartTime, pickEndTime;
    EditText titleText;
    DialogFragment newFragment;

    public EditCardFragment() {

    }


    public static EditCardFragment newInstance(int position) {
        EditCardFragment fragment = new EditCardFragment();
        Bundle args = new Bundle();
        args.putInt("Position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("Position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScheduleCard scheduleCard = myScheduleCardList.get(position);
        View view =  inflater.inflate(R.layout.fragment_new_event, container, false);

        titleText = view.findViewById(R.id.editTextTitle);
        pickDate = view.findViewById(R.id.enterDate);
        pickStartTime = view.findViewById(R.id.enterStart);
        pickEndTime = view.findViewById(R.id.enterEnd);
        Button enterButton = view.findViewById(R.id.enterButton);
        RadioGroup rg = view.findViewById(R.id.eventCategory);

        titleText.setText(scheduleCard.getCardTitle());
        pickDate.setText(scheduleCard.getCardDate());
        pickStartTime.setText(scheduleCard.getCardStartTime());
        pickEndTime.setText(scheduleCard.getCardEndTime());






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
                String title = titleText.getText().toString() ;
                String category = categoryString;
                String time = (pickStartTime.getText() + " - " + pickEndTime.getText());

                newEvent.time cardTime = new newEvent.time(pickStartTime.getText().toString(), pickEndTime.getText().toString());

                String cardStart = cardTime.getCardStart();
                String cardEnd = cardTime.getCardEnd();
                int totalHr = cardTime.getTotalHr();
                int totalMin = cardTime.getTotalMin();

//                String label;
//                String note;
                if (!title.equals("") & category != null) {
                    myScheduleCardList.add(new ScheduleCard(title, category, "Description", "October 9", time, cardStart, cardEnd, "LABEL", "note", totalHr, totalMin));

//                    Log.d(TAG, "pickStartTime" + pickStartTime.getText().toString() );
//                    Log.d(TAG, "pickEndTime" + pickEndTime.getText().toString() );

                    newEvent.sortScheduleCard();

                    WriteSchedule  ws = new WriteSchedule();
                    ws.writeSchedule();
                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
                enterData();

            }

        });


        return view;
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
