package teamgg.cs262.calvin.edu.lifemanager;


import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchAnalyticsActivity(View view) {
        Intent intent = new Intent(this, Analytics.class);
        startActivity(intent);
    }

    public void launchScheduleActivity(View view) {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }

    public void openNewEvent(View view) {
        Intent intent = new Intent(this, NewEvent.class);
        startActivity(intent);
    }


    public void toggleSelectedActivity(View view) {
        Button toggleStart = (Button) findViewById(R.id.toggleStart);
        Button toggleStop = (Button) findViewById(R.id.toggleStop);
        if (toggleStart.getVisibility() == View.VISIBLE) {
            toggleStart.setVisibility(View.INVISIBLE);
            toggleStop.setVisibility(View.VISIBLE);
        } else {
            toggleStart.setVisibility(View.VISIBLE);
            toggleStop.setVisibility(View.INVISIBLE);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
