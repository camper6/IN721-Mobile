package bit.abduhs1.datapassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private int requestCode;
    private int resultCode;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetting = (Button)findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new SettingButtonClickHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK)) {
            String result = data.getStringExtra("requestedUsername");

            TextView txtReceive = (TextView)findViewById(R.id.txtReceive);
            txtReceive.setText(result);
        }
    }

    public class SettingButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(Main.this, Setting.class);

            startActivityForResult(changeActivityIntent, 0);
        }
    }
}
