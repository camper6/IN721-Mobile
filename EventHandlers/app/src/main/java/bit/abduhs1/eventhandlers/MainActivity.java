package bit.abduhs1.eventhandlers;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonChangeDisplay = (Button) findViewById(R.id.button);
        ButtonChangeDisplay.setOnClickListener(new ButtonChangeDisplayClickHandler());
        ButtonChangeDisplay.setOnLongClickListener(new ButtonChangeDisplayLongClickHandler());

        EditText EditTextChangeDisplay = (EditText) findViewById(R.id.editText);
        EditTextChangeDisplay.setOnKeyListener(new EditTextChangeDisplayHandler());

        EditText EditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        EditTextUsername.setOnKeyListener(new EditTextUsernameHandler());
    }

    public class ButtonChangeDisplayClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {

            Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_LONG).show();
        }
    }

    public class ButtonChangeDisplayLongClickHandler implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View view) {

            Toast.makeText(MainActivity.this, "Long Click!", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    public class EditTextChangeDisplayHandler implements View.OnKeyListener
    {

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            // filtering
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                return true;

            if (keyEvent.getAction() == KeyEvent.KEYCODE_AT)
            {
                Toast.makeText(MainActivity.this, "Don't type @", Toast.LENGTH_LONG).show();
            }
            return true;
        }
    }

    public class EditTextUsernameHandler implements View.OnKeyListener
    {
        EditText text = (EditText) findViewById(R.id.editTextUsername);
        Editable username = (Editable) text.getText();

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                return true;

            if ( username.length() > 8) {
                Toast.makeText(MainActivity.this, "Usernames must be 8 characters, " + username, Toast.LENGTH_LONG).show();
            } else if (username.length() == 8)
            {
                Toast.makeText(MainActivity.this, "Thank you, " + username, Toast.LENGTH_LONG).show();
            }

            return true;
        }
    }

}
