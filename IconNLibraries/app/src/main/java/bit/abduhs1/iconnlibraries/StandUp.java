package bit.abduhs1.iconnlibraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class StandUp extends AppCompatActivity {
    protected ImageView ivStand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_up);
        ivStand = (ImageView)findViewById(R.id.ivStand);
        Button btStand = (Button)findViewById(R.id.btStand);
        btStand.setOnClickListener(new StandButtonListener());
    }

    public class StandButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            YoYo.with(Techniques.StandUp).duration(3000).playOn(ivStand);
        }
    }
}
