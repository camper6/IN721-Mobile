package bit.abduhs1.usinglocation;

import android.os.AsyncTask;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Sakinah on 8/05/2016.
 */
public class Coordinate {

    private double latitude;
    private double longtitude;
    private double nxtLatitude;
    private double nxtLongtitude;
    private Random rGen;
    private DecimalFormat decFormat;

    public Coordinate() {
        rGen = new Random();
        decFormat = new DecimalFormat("##.###");
        setCoordinate();
    }

    private void setCoordinate() {
        // Random * Range + Minimum AKA Max * 2 (Random) - Minimum
        // (Random * (Max + Max)) - Min
        nxtLatitude = rGen.nextDouble() * (90*2) - 90;
        nxtLongtitude = rGen.nextDouble() * (180*2) - 180;
    }

    public void setTextViewCoordinate(TextView tvLatitude, TextView tvLongtitude) {
        latitude = nxtLatitude;
        longtitude = nxtLongtitude;
        tvLatitude.setText(String.valueOf(decFormat.format(latitude)));
        tvLongtitude.setText(String.valueOf(decFormat.format(longtitude)));
        setCoordinate();
    }
}
