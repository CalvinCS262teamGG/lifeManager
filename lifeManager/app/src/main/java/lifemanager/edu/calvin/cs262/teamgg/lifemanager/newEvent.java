package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class newEvent extends Fragment implements View.OnClickListener {

    public newEvent() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("New Event");

        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        TextView pickDate = (TextView) rootView.findViewById(R.id.enterDate);
        TextView pickStartTime = (TextView) rootView.findViewById(R.id.enterStart);
        TextView pickEndTime = (TextView) rootView.findViewById(R.id.enterEnd);

        pickDate.setOnClickListener(this);
        pickStartTime.setOnClickListener(this);
        pickEndTime.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        DialogFragment newFragment;
        switch (view.getId()) {
            case R.id.enterDate:
                    newFragment = new DatePickerFragment();
                    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;

            case R.id.enterStart:
                    newFragment = new TimePickerFragment();
                    newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                break;

            case R.id.enterEnd:
                newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                break;
        }
    }
}
