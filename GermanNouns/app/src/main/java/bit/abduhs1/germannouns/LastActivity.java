package bit.abduhs1.germannouns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {
    private int score = 0;
    private TextView textViewScored;
    private TextView textViewScoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        textViewScored = (TextView)findViewById(R.id.textViewScored);
        textViewScoring = (TextView)findViewById(R.id.textViewScoring);
        textViewScoring.setText("11");

        Intent launchIntent = getIntent();
        score = launchIntent.getIntExtra("score", 0);
        textViewScored.setText(score + "");

        Button btRestart = (Button)findViewById(R.id.btRestart);
        btRestart.setOnClickListener(new RestartButtonHandler());

    }

    public class RestartButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent restartActivityIntent = new Intent(LastActivity.this, MainActivity.class);
            startActivity(restartActivityIntent);
        }
    }
}
