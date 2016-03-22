package bit.abduhs1.musicschool;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import bit.abduhs1.musicschool.MainActivity;

/**
 * Created by Sakinah on 22/03/2016.
 */
public class AlertBuilderFragment extends DialogFragment {

    public AlertBuilderFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Do you wish to confirm your enrolment?");
        builder.setPositiveButton("Yes", new YesButtonHandler());
        builder.setNegativeButton("No", new NoButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(false);
        }
    }
}
