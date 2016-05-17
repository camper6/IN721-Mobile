package bit.abduhs1.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private SensorManager mTiltManager;
    private Sensor mTilt;
    private RollingBall ball;

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ball = new RollingBall(this);
        setContentView(ball);

        mTiltManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTilt = mTiltManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTiltManager.registerListener(new TiltSensorChanged(), mTilt, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTiltManager.unregisterListener(new TiltSensorChanged());
    }

    private class RollingBall extends View {
        Bitmap ball;
        int screenW;
        int screenH;
        int X;
        int Y;
        int Z;
        int ballW;
        int ballH;
        int initialY;

        public RollingBall(Context context) {
            super(context);
            ball = BitmapFactory.decodeResource(getResources(), R.drawable.redcircle);
            ballW = ball.getWidth();
            ballH = ball.getHeight();
            initialY = 100;
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            screenW = w;
            screenH = h;
            X = (int)(screenW/2) - (ballW /2);
            Y = initialY;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if ( Y > (screenH - ballH)) {
                Y = screenH - ballH;
            } else if ( Y < 0) {
                Y = 0;
            }

            if (X > (screenW - ballW)) {
                X = screenW - ballW;
            } else if (X < 0) {
                X = 0;
            }

            canvas.save();
            canvas.drawBitmap(ball, X, Y, null);
            canvas.restore();

            invalidate();
        }

        public void changeXY(int x, int y) {
            X = X - x;
            Y = Y + y;
        }
    }

    private class TiltSensorChanged implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            // float z = event.values[2];
            ball.changeXY((int)x, (int)y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
