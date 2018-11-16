package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Analytics.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Analytics#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Analytics extends Fragment {

    public static ArrayList<ScheduleCard> analyticsCardList = new ArrayList<>();

    private TextView todayText;
    private TextView yesterdayText;
    private TextView thisMonthText;

    public Analytics() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Analytics");

        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View rootView = inflater.inflate(R.layout.fragment_analytics, null);
//        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        todayText = rootView.findViewById(R.id.today);
        yesterdayText = rootView.findViewById(R.id.yesterday);
        thisMonthText = rootView.findViewById(R.id.thisMonth);



        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
        //add values to the test array
        scheduleCardList.add(new ScheduleCard("Exercise", "Self-development",
                "DESCRIPTION", "11/13/2018","3:30 AM - 4:30 PM",
                "3:30 PM", "4:30 PM","LABEL" ,
                "NOTE", 5, 300));
        scheduleCardList.add(new ScheduleCard("Exercise", "Self-development",
                "DESCRIPTION", "11/12/2018","3:30 AM - 4:30 PM",
                "3:30 PM", "4:30 PM","LABEL" ,
                "NOTE", 3, 180));
        scheduleCardList.add(new ScheduleCard("Study", "Study",
                "DESCRIPTION", "11/07/2018","3:30 AM - 4:30 PM",
                "3:30 PM", "4:30 PM","LABEL" ,
                "NOTE", 2, 120));
        scheduleCardList.add(new ScheduleCard("Study", "Homework",
                "DESCRIPTION", "10/07/2018","3:30 AM - 4:30 PM",
                "3:30 PM", "4:30 PM","LABEL" ,
                "NOTE", 2, 120));

        //getting current date information
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Calendar cal = Calendar.getInstance();
        Date parsedDate = null;


        String concatResults = "";

        //create a hashmap of each category with total hours for today
        HashMap<String, Integer> todayHash = new HashMap<String, Integer>();
        for (ScheduleCard card : scheduleCardList) {
            if (card.getCardDate().equals(df.format(cal.getTime()).trim())) //if date = today
            {
                Integer oldValue = todayHash.get(card.getCardCategory());
                if (oldValue == null) {oldValue = 0;}
                //update the total hours of specific category
                todayHash.put(card.getCardCategory(), oldValue + card.getCardTotalHr());
            }
        }
        for (Map.Entry<String, Integer> entry : todayHash.entrySet()){
            concatResults += entry.getKey() + " \ttotal hours: " + entry.getValue() + "\n";
        }
        todayText.setText(concatResults);


        concatResults = "";
        //create a hashmap of each category with total hours for yesterday
        Map<String, Integer> yesterdayHash = new HashMap<String, Integer>();
        cal.add(Calendar.DATE, -1);
        for (ScheduleCard card : scheduleCardList) {
            if (card.getCardDate().equals(df.format(cal.getTime()).trim())) //if date = yesterday
            {
                Integer oldValue = yesterdayHash.get(card.getCardCategory());
                if (oldValue == null) {oldValue = 0;}
                //update the total hours of specific category
                yesterdayHash.put(card.getCardCategory(), oldValue + card.getCardTotalHr());
            }
        }
        for (Map.Entry<String, Integer> entry : yesterdayHash.entrySet()){
            concatResults += entry.getKey() + " \ttotal hours: " + entry.getValue() + "\n";

        }
        yesterdayText.setText(concatResults);




        concatResults = "";
        //create a hashmap of each category with total hours for this month
        HashMap<String, Integer> thisMonthHash = new HashMap<String, Integer>();
        for (ScheduleCard card : scheduleCardList){
            try{
                parsedDate = df.parse(card.getCardDate());
                //System.out.println("parsedDate month: " + parsedDate.getMonth());
            }
            catch (Exception e) {
                Log.e("Analytics: last month", "failed to parse date");
            }
            if (parsedDate.getMonth() + 1 == now.getMonthValue()) //+1 because getMonth starts at 0
            {
                Integer oldValue = yesterdayHash.get(card.getCardCategory());
                if (oldValue == null) {oldValue = 0;}
                //update the total hours of specific category
                thisMonthHash.put(card.getCardCategory(), oldValue + card.getCardTotalHr());
            }
        }
        for (Map.Entry<String, Integer> entry : thisMonthHash.entrySet()){
            concatResults += entry.getKey() + " \ttotal hours: " + entry.getValue() + "\n";
        }
        thisMonthText.setText(concatResults);

        return rootView;

    }






//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public Analytics() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Analytics.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Analytics newInstance(String param1, String param2) {
//        Analytics fragment = new Analytics();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_analytics, container, false);
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
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
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
