package bit.abduhs1.datapassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btnPassing = (Button)findViewById(R.id.btnPassing);
        btnPassing.setOnClickListener(new PassingButtonClickHandler());
    }

    public class PassingButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();

            EditText edtUsername = (EditText)findViewById(R.id.edtUsername);
            String username = edtUsername.getText().toString();

            if (username.length() >= 5) {
                returnIntent.putExtra("requestedUsername", username);

                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            } if (username == "no username"){
                returnIntent.putExtra("requestedUsername", "");

                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            } else {
                String warning = "Your username should be more than eight characters long";
                Toast.makeText(Setting.this, warning, Toast.LENGTH_LONG).show();
            }
        }
    }
}
