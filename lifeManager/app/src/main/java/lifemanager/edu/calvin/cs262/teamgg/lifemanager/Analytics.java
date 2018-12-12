package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

<<<<<<< HEAD
import android.content.Context;
import android.net.Uri;
=======
import android.annotation.SuppressLint;
>>>>>>> f72853f464cbb26ffbcef6b14503b86450ceb1ae
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
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

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.TAG;
import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.myScheduleCardList;
=======
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.readSchedule;
>>>>>>> f72853f464cbb26ffbcef6b14503b86450ceb1ae


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
<<<<<<< HEAD
    private TextView thisMonthText;
=======
    private TextView weekText;
    private TextView monthText;
    ArrayList<ScheduleCard> todayList = new ArrayList<ScheduleCard>();
    ArrayList<ScheduleCard> yesterdayList = new ArrayList<ScheduleCard>();
    ArrayList<ScheduleCard> weekList = new ArrayList<ScheduleCard>();
    ArrayList<ScheduleCard> monthList = new ArrayList<ScheduleCard>();
    ArrayList<Integer> todayTot, yesterdayTot, weekTot, monthTot = new ArrayList<>(5);

>>>>>>> f72853f464cbb26ffbcef6b14503b86450ceb1ae

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
<<<<<<< HEAD
        thisMonthText = rootView.findViewById(R.id.thisMonth);



//        ArrayList<ScheduleCard> scheduleCardList = new ArrayList<>();
//        //add values to the test array
//        scheduleCardList.add(new ScheduleCard("Exercise", "Self-development",
//                "DESCRIPTION", "11/13/2018","3:30 AM - 4:30 PM",
//                "3:30 PM", "4:30 PM","LABEL" ,
//                "NOTE", 5, 300));
//        scheduleCardList.add(new ScheduleCard("Exercise", "Self-development",
//                "DESCRIPTION", "11/12/2018","3:30 AM - 4:30 PM",
//                "3:30 PM", "4:30 PM","LABEL" ,
//                "NOTE", 3, 180));
//        scheduleCardList.add(new ScheduleCard("Study", "Study",
//                "DESCRIPTION", "11/07/2018","3:30 AM - 4:30 PM",
//                "3:30 PM", "4:30 PM","LABEL" ,
//                "NOTE", 2, 120));
//        scheduleCardList.add(new ScheduleCard("Study", "Homework",
//                "DESCRIPTION", "10/07/2018","3:30 AM - 4:30 PM",
//                "3:30 PM", "4:30 PM","LABEL" ,
//                "NOTE", 2, 120));

        ArrayList<ScheduleCard> scheduleCardList = myScheduleCardList;
        ArrayList<ScheduleCard> scheduleCardListYd = new ArrayList<>();
        ArrayList<ScheduleCard> scheduleCardListMonth = new ArrayList<>();


        //getting current date information
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Calendar cal = Calendar.getInstance();
        Date parsedDate = null;


        String concatResults = "";

        //create a hashmap of each category with total hours for today
//        HashMap<String, Integer> todayHash = new HashMap<String, Integer>();
//        for (ScheduleCard card : scheduleCardList) {
//            if (card.getCardDate().equals(df.format(cal.getTime()).trim())) //if date = today
//            {
//                Integer oldValue = todayHash.get(card.getCardCategory());
//                if (oldValue == null) {oldValue = 0;}
//                //update the total hours of specific category
//                todayHash.put(card.getCardCategory(), oldValue + card.getCardTotalHr());
//            }
//        }
//        for (Map.Entry<String, Integer> entry : todayHash.entrySet()){
//            concatResults += entry.getKey() + " \ttotal hours: " + entry.getValue() + "\n";
//        }
//        todayText.setText(concatResults);

        int DirectHr = 0, DirectMin = 0, IndirectHr = 0, IndirectMin = 0, PersonalHr = 0, PersonalMin = 0, Self_devHr = 0, Self_devMin = 0, EtcHr = 0, EtcMin = 0;
        for (int i = 0; i < scheduleCardList.size(); i++) {
            if (scheduleCardList.get(i).getCardCategory().equals("Direct")) {
                DirectHr += scheduleCardList.get(i).getCardTotalHr();
                DirectMin += scheduleCardList.get(i).getCardTotalMin();
            } else if (scheduleCardList.get(i).getCardCategory().equals("Indirect")) {
                IndirectHr += scheduleCardList.get(i).getCardTotalHr();
                IndirectMin += scheduleCardList.get(i).getCardTotalMin();
            } else if (scheduleCardList.get(i).getCardCategory().equals("Personal")) {
                PersonalHr += scheduleCardList.get(i).getCardTotalHr();
                PersonalMin += scheduleCardList.get(i).getCardTotalMin();
            } else if (scheduleCardList.get(i).getCardCategory().equals("Self-development")) {
                Self_devHr += scheduleCardList.get(i).getCardTotalHr();
                Self_devMin += scheduleCardList.get(i).getCardTotalMin();
            } else if (scheduleCardList.get(i).getCardCategory().equals("Etc")) {
                EtcHr += scheduleCardList.get(i).getCardTotalHr();
                EtcMin += scheduleCardList.get(i).getCardTotalMin();
            }

        }
        DirectHr += (DirectMin - (DirectMin%60))/60;
        DirectMin = DirectMin%60;
        IndirectHr += (IndirectMin - (IndirectMin%60))/60;
        IndirectMin = IndirectMin%60;
        PersonalHr += (PersonalMin - (PersonalMin%60))/60;
        PersonalMin = PersonalMin%60;
        Self_devHr += (Self_devMin - (Self_devMin%60))/60;
        Self_devMin = Self_devMin%60;
        EtcHr += (EtcMin - (EtcMin%60))/60;
        EtcMin = EtcMin%60;
        concatResults += "Direct performance  " + " \t" + Integer.toString(DirectHr)+"H " + Integer.toString(DirectMin)+"M" + "\n";
        concatResults += "Indirect performance" + " \t" + Integer.toString(IndirectHr)+"H " + Integer.toString(IndirectMin)+"M" + "\n";
        concatResults += "Personal                    " + " \t" + Integer.toString(PersonalHr)+"H " + Integer.toString(PersonalMin)+"M" + "\n";
        concatResults += "Self-development      " + " \t" + Integer.toString(Self_devHr)+"H " + Integer.toString(Self_devMin)+"M" + "\n";
        concatResults += "Etc                                " + " \t" + Integer.toString(EtcHr)+"H " + Integer.toString(EtcMin)+"M" + "\n";
        int FreeHr = DirectHr + IndirectHr + PersonalHr + Self_devHr + EtcHr;
        int FreeMin = DirectMin + IndirectMin + PersonalMin + Self_devMin + EtcMin;
        FreeHr += (FreeMin - (FreeMin%60))/60;
        FreeHr = 24 - FreeHr;
        FreeMin = FreeMin%60;
        if (FreeMin > 0) {
            FreeHr = FreeHr - 1;
            FreeMin = 60 - FreeMin;
        }
        concatResults += "\nFree time                    " + "\t" + Integer.toString(FreeHr) + "H " + Integer.toString(FreeMin)+"M";
        todayText.setText(concatResults);



        concatResults = "";
        //create a hashmap of each category with total hours for yesterday
        Map<String, Integer> yesterdayHash = new HashMap<String, Integer>();
        cal.add(Calendar.DATE, -1);
        for (ScheduleCard card : scheduleCardListYd) {
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
        for (ScheduleCard card : scheduleCardListMonth){
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
=======
        weekText = rootView.findViewById(R.id.pastWeek);
        monthText = rootView.findViewById(R.id.thisMonth);

        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
        format.setTimeZone(cal.getTimeZone());

        String today = format.format(cal.getTime());


        //Get today data
        todayList = readSchedule(today, getContext());
        todayTot = getCategoryTotals(todayList);
        todayText.setText(concatResults(todayTot));

        //Get yesterday data
        String yesterday = getYesterday(today);
        yesterdayList = readSchedule(yesterday, getContext());
        yesterdayTot = getCategoryTotals(yesterdayList);
        yesterdayText.setText(concatResults(yesterdayTot));

        //Get past week data
        String tempday = today;
        ArrayList<ScheduleCard> tempList;
        for (int i = 0; i < 7; i++) {
            tempList = readSchedule(tempday, getContext());
            weekList.addAll(tempList);
            tempday = getYesterday(tempday);
        }
        weekTot = getCategoryTotals(weekList);
        weekText.setText(concatResults(weekTot));


        //Get past month data
        tempday = today;
        for (int i = 0; i < 30; i++) {
            tempList = readSchedule(tempday, getContext());
            monthList.addAll(tempList);
            tempday = getYesterday(tempday);
        }
        monthTot = getCategoryTotals(monthList);
        monthText.setText(concatResults(monthTot));
        return rootView;
    }

    private String concatResults(ArrayList<Integer> todayTot) {
        String concatResults = "";
        int DirectMin = todayTot.get(0);
        int IndirectMin = todayTot.get(1);
        int PersonalMin = todayTot.get(2);
        int Self_devMin = todayTot.get(3);
        int EtcMin = todayTot.get(4);
        int TotalMin = DirectMin + IndirectMin + PersonalMin + Self_devMin + EtcMin;
        int FreeMin = 1440 - TotalMin;

        int DirectHr = DirectMin/60;
        DirectMin = DirectMin%60;
        int IndirectHr = IndirectMin/60;
        IndirectMin = IndirectMin%60;
        int PersonalHr = PersonalMin/60;
        PersonalMin = PersonalMin%60;
        int Self_devHr = Self_devMin/60;
        Self_devMin = Self_devMin%60;
        int EtcHr = EtcMin/60;
        EtcMin = EtcMin%60;
        int FreeHr = FreeMin/60;
        FreeMin = FreeMin%60;

        concatResults += "Direct performance  " + " \t\t" + Integer.toString(DirectHr)+"H " + Integer.toString(DirectMin)+"M" + "\n";
        concatResults += "Indirect performance" + " \t\t" + Integer.toString(IndirectHr)+"H " + Integer.toString(IndirectMin)+"M" + "\n";
        concatResults += "Personal                    " + " \t\t" + Integer.toString(PersonalHr)+"H " + Integer.toString(PersonalMin)+"M" + "\n";
        concatResults += "Self-development      " + " \t\t" + Integer.toString(Self_devHr)+"H " + Integer.toString(Self_devMin)+"M" + "\n";
        concatResults += "Etc                              " + " \t\t" + Integer.toString(EtcHr)+"H " + Integer.toString(EtcMin)+"M" + "\n";
        concatResults += "\nFree time                    " + "\t\t" + Integer.toString(FreeHr) + "H " + Integer.toString(FreeMin)+"M";

        return concatResults;
    }

    private ArrayList<Integer> getCategoryTotals(ArrayList<ScheduleCard> myList) {
        //Totals are  Direct -- Indirect -- Personal -- Selfdev -- Etc.
        int DirectMin = 0, IndirectMin = 0, PersonalMin = 0, Self_devMin = 0, EtcMin = 0;
        String category = "";

        for (int i = 0; i < myList.size(); i++ ) {
            category = myList.get(i).getCardCategory();
            switch(category) {
                case "Direct":
                    DirectMin += myList.get(i).getCardTotalMin();
                    break;
                case "Indirect":
                    IndirectMin += myList.get(i).getCardTotalMin();
                    break;
                case "Personal":
                    PersonalMin += myList.get(i).getCardTotalMin();
                    break;
                case "Self-development":
                    Self_devMin += myList.get(i).getCardTotalMin();
                    break;
                case "Etc":
                    EtcMin += myList.get(i).getCardTotalMin();
                    break;
            }
        }
        ArrayList<Integer> totals = new ArrayList<Integer>(
                Arrays.asList(DirectMin, IndirectMin, PersonalMin, Self_devMin, EtcMin)
        );
        return totals;
    }

    private String getYesterday(String today) {
        String day = today.substring(today.length()-6, today.length()-4);
        String month = today.substring(0, today.length()-6);
        String year = today.substring(today.length()-4);
        if (day.equals("01")) {
            switch (month) {
                case "1":
                    month = "12";
                    year = String.valueOf(Integer.parseInt(year)-1);
                    day = "31";
                    break;
                case "2":
                    month = "1";
                    day = "31";
                    break;
                case "3":
                    month = "2";
                    day = "28";
                    break;
                case "4":
                    month = "3";
                    day = "31";
                    break;
                case "5":
                    month = "4";
                    day = "30";
                    break;
                case "6":
                    month = "5";
                    day = "31";
                    break;
                case "7":
                    month = "6";
                    day = "30";
                    break;
                case "8":
                    month = "7";
                    day = "31";
                    break;
                case "9":
                    month = "8";
                    day = "31";
                    break;
                case "10":
                    month = "9";
                    day = "30";
                    break;
                case "11":
                    month = "10";
                    day = "31";
                    break;
                case "12":
                    month = "11";
                    day = "30";
                    break;
            }
        } else {

            day = String.valueOf(Integer.parseInt(day)-1);

        }

        return month + day + year;
        
    }

>>>>>>> f72853f464cbb26ffbcef6b14503b86450ceb1ae
}
