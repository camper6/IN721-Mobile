package bit.abduhs1.dunedin;

import android.graphics.drawable.Drawable;

/**
 * Created by Sakinah on 5/04/2016.
 */
public class Location {

    int locationImage;
    String locationName;

    public Location(String locationName, int locationImage) {
        this.locationName = locationName;
        this.locationImage = locationImage;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
