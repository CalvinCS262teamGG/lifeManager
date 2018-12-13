package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.readSchedule;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Analytics.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Analytics#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Analytics extends Fragment {

    public Analytics() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Analytics");

        View rootView = inflater.inflate(R.layout.fragment_analytics, null);

        TextView todayText = rootView.findViewById(R.id.today);
        TextView yesterdayText = rootView.findViewById(R.id.yesterday);
        TextView weekText = rootView.findViewById(R.id.pastWeek);
        TextView monthText = rootView.findViewById(R.id.thisMonth);

        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
        format.setTimeZone(cal.getTimeZone());

        String today = format.format(cal.getTime());


        //Get today data
        ArrayList<ScheduleCard> todayList = readSchedule(today, getContext());
        ArrayList<Integer> todayTot = getCategoryTotals(todayList);
        todayText.setText(concatResults(todayTot, 1));

        //Get yesterday data
        String yesterday = getYesterday(today);
        ArrayList<ScheduleCard> yesterdayList = readSchedule(yesterday, getContext());
        ArrayList<Integer> yesterdayTot = getCategoryTotals(yesterdayList);
        yesterdayText.setText(concatResults(yesterdayTot, 1));

        //Get past week data
        String tempday = today;
        ArrayList<ScheduleCard> tempList;
        ArrayList<ScheduleCard> weekList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            tempList = readSchedule(tempday, getContext());
            weekList.addAll(tempList);
            tempday = getYesterday(tempday);
        }
        ArrayList<Integer> weekTot = getCategoryTotals(weekList);
        weekText.setText(concatResults(weekTot, 7));


        //Get past month data
        tempday = today;
        ArrayList<ScheduleCard> monthList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            tempList = readSchedule(tempday, getContext());
            monthList.addAll(tempList);
            tempday = getYesterday(tempday);
        }
        ArrayList<Integer> monthTot = getCategoryTotals(monthList);
        monthText.setText(concatResults(monthTot, 30));
        return rootView;
    }

    private String concatResults(ArrayList<Integer> todayTot, int numberofdays) {
        String concatResults = "";
        int DirectMin = todayTot.get(0);
        int IndirectMin = todayTot.get(1);
        int PersonalMin = todayTot.get(2);
        int Self_devMin = todayTot.get(3);
        int EtcMin = todayTot.get(4);
        int TotalMin = DirectMin + IndirectMin + PersonalMin + Self_devMin + EtcMin;
        int FreeMin = numberofdays*24*60 - TotalMin;

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

        concatResults += "Direct performance     " + "\t\t" + Integer.toString(DirectHr)+"H " + Integer.toString(DirectMin)+"M" + "\n";
        concatResults += "Indirect performance  " + "\t\t" + Integer.toString(IndirectHr)+"H " + Integer.toString(IndirectMin)+"M" + "\n";
        concatResults += "Personal                        " + "\t\t" + Integer.toString(PersonalHr)+"H " + Integer.toString(PersonalMin)+"M" + "\n";
        concatResults += "Self-development         " + "\t\t" + Integer.toString(Self_devHr)+"H " + Integer.toString(Self_devMin)+"M" + "\n";
        concatResults += "Etc                                   " + "\t\t" + Integer.toString(EtcHr)+"H " + Integer.toString(EtcMin)+"M" + "\n\n";
        concatResults += "Free time                      " + "\t\t" + Integer.toString(FreeHr) + "H " + Integer.toString(FreeMin)+"M";

        return concatResults;
    }

    private ArrayList<Integer> getCategoryTotals(ArrayList<ScheduleCard> myList) {
        //Totals are  Direct -- Indirect -- Personal -- Selfdev -- Etc.
        int DirectMin = 0, IndirectMin = 0, PersonalMin = 0, Self_devMin = 0, EtcMin = 0;
        String category;

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
        return new ArrayList<>(
                Arrays.asList(DirectMin, IndirectMin, PersonalMin, Self_devMin, EtcMin)
        );
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

}
