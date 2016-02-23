package bit.abduhs1.myfirstapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.DimenRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Resources object
        Resources resourceResolver = getResources();

        // Constants
        /*
        resourceResolver.getString(R.string.string_resource);
        resourceResolver.getDimension(R.dimen.dimen_resource);
        resourceResolver.getColor(R.color.color_resource);
        resourceResolver.getBoolean(R.bool.bool_resource);
        resourceResolver.getInteger(R.integer.integer_resource);
        resourceResolver.getIntArray(R.array.integer_array_resource);
        resourceResolver.getTextArray(R.array.array_resource);
        */

        int datesArray[] = resourceResolver.getIntArray(R.array.arrays);
        TextView dateView = (TextView) findViewById(R.id.FebFridays);

        for(int i = 0; i < datesArray.length; i++)
        {
            dateView.append(Integer.toString(datesArray[i]));
        }

        String dogBreed = "";
        TextView animalView = (TextView) findViewById(R.id.animalView);
        Random gen = new Random();
        int animalValue = gen.nextInt(4);

        switch(animalValue)
        {
            case 0:
                dogBreed = "Poodle";
                animalView.setText(dogBreed);
            case 1:
                dogBreed = "Labdrador";
                animalView.setText(dogBreed);
            case 2:
                dogBreed = "Shar Pei";
                animalView.setText(dogBreed);
            case 3:
                dogBreed = "Newfoundland";
                animalView.setText(dogBreed);
        }

    }


}
