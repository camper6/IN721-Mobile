package bit.abduhs1.musicschool;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spnMonths;
    AlertBuilderFragment confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnrol = (Button) findViewById(R.id.btEnrol);
        btnEnrol.setOnClickListener(new CreateFragmentButtonHandler());

        spnMonths = (Spinner) findViewById(R.id.spnMonths);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.array.months);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonths.setAdapter(adapter);

    }

    public void giveMeMyData(boolean confirm) {
        TextView textViewConfirmed = (TextView) findViewById(R.id.textViewConfirmed);

        if(confirm) {
            textViewConfirmed.setText(confirmLesson());
        } else {
            textViewConfirmed.setText("You have cancelled your enrolment");
        }
    }

    private String confirmLesson() {
        RadioButton rdoAccordion = (RadioButton) findViewById(R.id.radioButtonAccordian);
        RadioButton rdoBassoon = (RadioButton) findViewById(R.id.radioButtonBass);
        RadioButton rdoCello = (RadioButton) findViewById(R.id.radioButtonCello);

        spnMonths = (Spinner) findViewById(R.id.spnMonths);
        String selectedMonth = "";
        String confirmation = "";

        if (rdoAccordion.isChecked()){
            selectedMonth = (String) spnMonths.getSelectedItem();
            confirmation = "You are now enrolled for Accordion lessons in " + selectedMonth;
        }else if(rdoBassoon.isChecked()){
            selectedMonth = (String) spnMonths.getSelectedItem();
            confirmation = "You are now enrolled for Bassoon lessons in " + selectedMonth;
        }else if(rdoCello.isChecked()){
            selectedMonth = (String) spnMonths.getSelectedItem();
            confirmation = "You are now enrolled for Cello lessons in " + selectedMonth;
        }else{
            Toast.makeText(MainActivity.this, "You have not selected an instrument.", Toast.LENGTH_SHORT).show();
        }

        return confirmation;
    }

    public class CreateFragmentButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            confirm = new AlertBuilderFragment();
            FragmentManager fm = getFragmentManager();
            confirm.show(fm, "confirm");
        }
    }

    public class OnClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            RadioButton rdoAccordion = (RadioButton) findViewById(R.id.radioButtonAccordian);
            RadioButton rdoBassoon = (RadioButton) findViewById(R.id.radioButtonBass);
            RadioButton rdoCello = (RadioButton) findViewById(R.id.radioButtonCello);

            TextView textViewConfirmed = (TextView) findViewById(R.id.textViewConfirmed);

            spnMonths = (Spinner) findViewById(R.id.spnMonths);
            String selectedMonth = "";

            if (rdoAccordion.isChecked()){
                selectedMonth = (String) spnMonths.getSelectedItem();
                textViewConfirmed.setText("You are now enrolled for Accordion lessons in " + selectedMonth + ".");
            }else if(rdoBassoon.isChecked()){
                selectedMonth = (String) spnMonths.getSelectedItem();
                textViewConfirmed.setText("You are now enrolled for Bassoon lessons in " + selectedMonth + ".");
            }else if(rdoCello.isChecked()){
                selectedMonth = (String) spnMonths.getSelectedItem();
                textViewConfirmed.setText("You are now enrolled for Cello lessons in " + selectedMonth + ".");
            }else{
                Toast.makeText(MainActivity.this, "You have not selected an instrument.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
