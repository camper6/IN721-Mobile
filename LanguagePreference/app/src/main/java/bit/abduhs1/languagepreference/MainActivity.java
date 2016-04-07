package bit.abduhs1.languagepreference;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;
    TextView textViewGreeting;
    String greetingInChosenLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGreeting = (TextView) findViewById(R.id.textViewGreeting);

        Button btnConfirm = (Button)findViewById(R.id.buttonConfirm);
        btnConfirm.setOnClickListener(new SetLanguageClickHandler());

        prefs = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        String languagePreference = prefs.getString("language", null);
        String colorPreference = prefs.getString("color", null);

        if (languagePreference != null) {
            greetingInChosenLanguage = getGreeting(Language.valueOf(languagePreference));

            textViewGreeting.setText(greetingInChosenLanguage);
        }

        if (colorPreference != null) {
            ColorDrawable color = getColor(colorPreference);

            textViewGreeting.setTextColor(color.getColor());
        }
    }

    private ColorDrawable getColor(String color) {
        ColorDrawable textColor = new ColorDrawable(textViewGreeting.getCurrentTextColor());
        switch (color) {
            case "Red":
                textColor = new ColorDrawable(Color.RED);
                break;
            case "Green":
                textColor = new ColorDrawable(Color.GREEN);
                break;
            case "Blue":
                textColor = new ColorDrawable(Color.BLUE);
                break;
            default:
                break;
        }
        return textColor;
    }

    private String getGreeting(Language language) {
        String greeting = "";

        switch (language) {
            case French:
                greeting = "Bonjour le Monde";
                break;
            case German:
                greeting = "Hallo Welt";
                break;
            case Spanish:
                greeting = "Hola Mundo";
                break;
            default:
                break;
        }

        return greeting;
    }

    public class SetLanguageClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupLanguage);
            RadioGroup colors = (RadioGroup) findViewById(R.id.radioGroupColor);

            if (rg.getCheckedRadioButtonId() != -1) {
                int checkedId = rg.getCheckedRadioButtonId();
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);
                String checkedLanguage = checkedButton.getText().toString();

                prefsEditor.putString("language", checkedLanguage);
                prefsEditor.commit();

                greetingInChosenLanguage = getGreeting(Language.valueOf(checkedLanguage));
                textViewGreeting.setText(greetingInChosenLanguage);
            }

            if (colors.getCheckedRadioButtonId() != -1) {

                int colorId = colors.getCheckedRadioButtonId();
                RadioButton checkedColorButton = (RadioButton) findViewById(colorId);
                String checkedColor = checkedColorButton.getText().toString();

                prefsEditor.putString("color", checkedColor);
                prefsEditor.commit();

                ColorDrawable languageColor = getColor(checkedColor);
                textViewGreeting.setTextColor(languageColor.getColor());
            }
        }
    }
}
