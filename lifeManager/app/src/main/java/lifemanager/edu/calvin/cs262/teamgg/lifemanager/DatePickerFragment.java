package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private TextView dateText;

    public DatePickerFragment() {}
    @SuppressLint("ValidFragment")
    public DatePickerFragment(TextView dateTextView) {
        dateText = dateTextView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user.

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(year, month, day, 0, 0, 0);
        Date chosenDate = c.getTime();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        String setDate = format.format(chosenDate);
        dateText.setText(setDate);

    }

}
