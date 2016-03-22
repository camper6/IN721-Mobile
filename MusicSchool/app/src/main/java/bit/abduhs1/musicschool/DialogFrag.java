package bit.abduhs1.musicschool;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bit.abduhs1.musicschool.R;

/**
 * Created by Sakinah on 22/03/2016.
 */
public class DialogFrag extends DialogFragment {

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get a reference to the actual dialog window and sets its title
        Dialog dialogWindow = getDialog();
        dialogWindow.setTitle("Do you wish to confirm your enrolment?");

        // Inflate the layout to produce a View
        View dialogView = inflater.inflate(R.layout.dialogue_fragment_display, container);

        return dialogView;
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get a reference to the actual dialog window and sets its title
        Dialog dialogWindow = getDialog();
        dialogWindow.setTitle("Do you wish to confirm your enrolment?");

        return dialogWindow;
    }
}
