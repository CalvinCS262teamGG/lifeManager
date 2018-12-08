package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * this class us used for our online help documentation page
 */
public class MyDialogFragment extends DialogFragment {
    static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help_dialog, container, false);
        TextView tv = v.findViewById(R.id.textView);
        tv.setText("Online Help Docs");
        return v;
    }
}