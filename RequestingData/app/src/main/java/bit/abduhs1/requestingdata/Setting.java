package bit.abduhs1.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent returnIntent = new Intent();
        TextView textViewSetting = (TextView)findViewById(R.id.textViewSetting);
        int colour = textViewSetting.getCurrentTextColor();

        returnIntent.putExtra("requestedResult", colour);

        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
