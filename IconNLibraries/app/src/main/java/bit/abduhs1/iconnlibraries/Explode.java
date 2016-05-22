package bit.abduhs1.iconnlibraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class Explode extends AppCompatActivity {

    protected ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode);
        Button button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new AnimateButtonListener());
    }

    public class AnimateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final ExplodeAnimation explodeAnimation = new ExplodeAnimation(imageView);
            explodeAnimation.setExplodeMatrix(ExplodeAnimation.MATRIX_3X3);
            explodeAnimation.setInterpolator(new DecelerateInterpolator());
            explodeAnimation.setDuration(2000);
            explodeAnimation.animate();
        }
    }
}
