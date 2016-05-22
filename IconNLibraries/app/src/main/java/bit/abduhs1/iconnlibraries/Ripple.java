package bit.abduhs1.iconnlibraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class Ripple extends AppCompatActivity {
    protected boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        toggle = false;

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!toggle) {
                    rippleBackground.startRippleAnimation();
                    toggle = true;
                } else {
                    rippleBackground.stopRippleAnimation();
                    toggle = false;
                }
            }
        });
    }
}
