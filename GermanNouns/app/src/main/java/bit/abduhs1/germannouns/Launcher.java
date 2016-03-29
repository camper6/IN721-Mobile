package bit.abduhs1.germannouns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        RelativeLayout launcher = (RelativeLayout)findViewById(R.id.launcher);
        launcher.setOnClickListener(new ChangeActivityClickHandler());

    }

    class ChangeActivityClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(Launcher.this, MainActivity.class);
            startActivity(changeActivityIntent);
        }
    }
}
