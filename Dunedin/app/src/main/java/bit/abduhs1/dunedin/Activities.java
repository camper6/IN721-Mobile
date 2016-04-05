package bit.abduhs1.dunedin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Activities extends Activity {

    Location[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        initialiseDataArray();

        ActivitiesArrayAdapter activitiesAdapter = new ActivitiesArrayAdapter(this, R.layout.custom_list_item, locations);

        ListView groupListView = (ListView) findViewById(R.id.listViewActivities);
        groupListView.setAdapter(activitiesAdapter);
    }

    private void initialiseDataArray() {
        Resources resourceMachine = getResources();
        Drawable larnachImage = resourceMachine.getDrawable(R.drawable.larnach_castle, null);
        Drawable moanaImage = resourceMachine.getDrawable(R.drawable.moana_pool, null);
        Drawable monarchImage = resourceMachine.getDrawable(R.drawable.monarch, null);
        Drawable octagonImage = resourceMachine.getDrawable(R.drawable.octagon, null);
        Drawable olvestonImage = resourceMachine.getDrawable(R.drawable.olveston, null);
        Drawable peninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula, null);
        Drawable stkildaImage = resourceMachine.getDrawable(R.drawable.st_kilda_beach, null);
        Drawable saltpoolmage = resourceMachine.getDrawable(R.drawable.salt_water_pool, null);
        Drawable breweryImage = resourceMachine.getDrawable(R.drawable.speights_brewery, null);
        Drawable taeriImage = resourceMachine.getDrawable(R.drawable.taeri_gorge_railway, null);

        locations = new Location[10];
        locations[0] = new Location("Larnach Castle", larnachImage);
        locations[1] = new Location("Moana Pool", moanaImage);
        locations[2] = new Location("Monarch Cruise", monarchImage);
        locations[3] = new Location("Octagon", octagonImage);
        locations[4] = new Location("Olveston", olvestonImage);
        locations[5] = new Location("Peninsula", peninsulaImage);
        locations[6] = new Location("St Kilda Beach", stkildaImage);
        locations[7] = new Location("Salt Water Pool", saltpoolmage);
        locations[8] = new Location("Speights Brewery", breweryImage);
        locations[9] = new Location("Taeri Gorge Railway", taeriImage);
    }

    public class ActivitiesArrayAdapter extends ArrayAdapter<Location> {

        public ActivitiesArrayAdapter(Context context, int resource, Location[] objects) {
            super(context, resource, objects);
        }

        public View getView(int position, View convertView, ViewGroup container) {
            LayoutInflater inflater = LayoutInflater.from(Activities.this);

            View customView = inflater.inflate(R.layout.custom_list_item, container, false);

            ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) customView.findViewById(R.id.tvItemsWords);

            Location currentItem = getItem(position);

            itemImageView.setImageDrawable(currentItem.locationImage);
            itemTextView.setText(currentItem.toString());

            return customView;
        }
    }

}
