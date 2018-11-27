package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DialogAdapter extends DialogFragment {

    public View v;

    private ArrayList<ScheduleCard> myScheduleCardList = MainActivity.myScheduleCardList;
    int position;

    public static DialogAdapter newInstance(int position) {
        DialogAdapter fragment = new DialogAdapter();

        // Supply position input as argument
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_new_event, container, false);
        return v;
    }

    public View getMyView() {
        return v;
    }
}
