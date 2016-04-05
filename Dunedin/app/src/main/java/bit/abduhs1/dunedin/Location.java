package bit.abduhs1.dunedin;

import android.graphics.drawable.Drawable;

/**
 * Created by Sakinah on 5/04/2016.
 */
public class Location {

    Drawable locationImage;
    String locationName;

    public Location(String locationName, Drawable locationImage) {
        this.locationName = locationName;
        this.locationImage = locationImage;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
