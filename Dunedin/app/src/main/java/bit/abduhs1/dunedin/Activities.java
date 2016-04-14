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

    Location[] location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        location = initialiseDataArray();

        ActivitiesArrayAdapter activitiesAdapter = new ActivitiesArrayAdapter(this, R.layout.custom_list_item, location);

        ListView groupListView = (ListView) findViewById(R.id.listViewActivities);
        groupListView.setAdapter(activitiesAdapter);
    }

    private Location[] initialiseDataArray() {
        /*Resources resourceMachine = getResources();
        Drawable larnachImage = resourceMachine.getDrawable(R.drawable.larnach_castle);
        Drawable moanaImage = resourceMachine.getDrawable(R.drawable.moana_pool);
        Drawable monarchImage = resourceMachine.getDrawable(R.drawable.monarch);
        Drawable octagonImage = resourceMachine.getDrawable(R.drawable.octagon);
        Drawable olvestonImage = resourceMachine.getDrawable(R.drawable.olveston);
        Drawable peninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula);
        Drawable stkildaImage = resourceMachine.getDrawable(R.drawable.st_kilda_beach);
        Drawable saltpoolmage = resourceMachine.getDrawable(R.drawable.salt_water_pool);
        Drawable breweryImage = resourceMachine.getDrawable(R.drawable.speights_brewery);
        Drawable taeriImage = resourceMachine.getDrawable(R.drawable.taeri_gorge_railway);
        */


        int larnachImage = (R.drawable.larnach_castle);
        int moanaImage = (R.drawable.moana_pool);
        int monarchImage = (R.drawable.monarch);
        int octagonImage = (R.drawable.octagon);
        int olvestonImage = (R.drawable.olveston);
        int peninsulaImage = (R.drawable.peninsula);
        int stkildaImage = (R.drawable.st_kilda_beach);
        int saltpoolmage = (R.drawable.salt_water_pool);
        int breweryImage = (R.drawable.speights_brewery);
        int taeriImage = (R.drawable.taeri_gorge_railway);

        Location[] locations = new Location[10];
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
        return locations;
    }

    public class ActivitiesArrayAdapter extends ArrayAdapter<Location> {
        //Location[] array;

        public ActivitiesArrayAdapter(Context context, int resource, Location[] objects) {
            super(context, resource, objects);
            //array = objects;
        }

        /*
        @Override
        public int getCount() {
            return array.length;
        }
        */

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            LayoutInflater inflater = LayoutInflater.from(Activities.this);

            View customView = inflater.inflate(R.layout.custom_list_item, container, false);

            ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) customView.findViewById(R.id.tvItemsWords);

            //Location currentItem = array[position];
            Location currentItem = getItem(position);

            itemImageView.setImageResource(currentItem.locationImage);
            itemTextView.setText(currentItem.toString());

            return customView;
        }
    }

}
