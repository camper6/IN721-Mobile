package bit.abduhs1.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonChange = (Button)findViewById(R.id.buttonChange);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textViewColour = (TextView)findViewById(R.id.textViewColour);

        if((requestCode == 0) && (resultCode == Activity.RESULT_OK)) {
            int result = data.getIntExtra("requestedResult", 0);

            textViewColour.setTextColor(result);
        }
    }

    public class buttonChangeHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, Setting.class);

            startActivityForResult(changeActivityIntent, 0);
        }
    }
}
