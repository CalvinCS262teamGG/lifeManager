package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static lifemanager.edu.calvin.cs262.teamgg.lifemanager.MainActivity.TAG;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    public static String time;
    TextView timeText;

    public TimePickerFragment() {
    }

    @SuppressLint("ValidFragment")
    public TimePickerFragment(TextView timeTextView) {
        timeText = timeTextView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @SuppressLint("SetTextI18n")
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String ampm = null;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        if (c.get(Calendar.AM_PM) == Calendar.AM) {
            ampm = "AM";
        } else if (c.get(Calendar.AM_PM) == Calendar.PM) {
            ampm = "PM";
        }

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min =  c.get(Calendar.MINUTE);
        Log.d(TAG, "pickStartTime - hour" + hour );
        Log.d(TAG, "pickStartTime - min" + min );

        String setHour = (c.get(Calendar.HOUR) == 0) ?"12":c.get(Calendar.HOUR)+"";
        timeText.setText( setHour +":" + String.format("%02d", c.get(Calendar.MINUTE)) + " " + ampm );

    }
}
