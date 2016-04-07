package bit.abduhs1.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new fillListButtonHandler());
    }

    public class fillListButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ArrayList<String> cityNameArray = new ArrayList<String>();
            String assetFileName = "city_names.txt";

            AssetManager am = getAssets();

            try {
                InputStream is = am.open(assetFileName);
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);

                String newCity;
                while ((newCity = br.readLine()) != null) {
                    cityNameArray.add(newCity);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> cities = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, cityNameArray);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(cities);

        }
    }
}
