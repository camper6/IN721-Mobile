package bit.abduhs1.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String cityName = "";
    String countryName = "";
    ArrayList<String> allCountries = new ArrayList<String>();
    String[] displayStringArray;
    Spinner spinnerCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("cities", MODE_PRIVATE, null);
        createDatabase();
        getCountries();

        spinnerCountries = (Spinner) findViewById(R.id.spinnerCities);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCountries);
        spinnerCountries.setAdapter(spinnerAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new selectCountryHandler());
    }

    public void setUpList(String selectedItem) {
        if ( selectedItem == "Every City") {
            getCities("Every City");
            ListView listViewCities = (ListView) findViewById(R.id.listViewCities);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayStringArray);
            listViewCities.setAdapter(listAdapter);
        } else {
            getCities(selectedItem);
            ListView listViewCities = (ListView) findViewById(R.id.listViewCities);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayStringArray);
            listViewCities.setAdapter(listAdapter);
        }
    }

    public void createDatabase() {
        String dropQuery = "DROP TABLE IF EXISTS tblCity";
        db.execSQL(dropQuery);

        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cityName TEXT NOT NULL, " +
                "countryName TEXT NOT NULL);";
        db.execSQL(createQuery);

        ArrayList<String> cities = new ArrayList<String>();
        cities.add("INSERT INTO tblCity VALUES(null, 'Amsterdam', 'The Netherlands')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Berlin', 'Germany')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Cairo', 'Egypt')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Dunedin', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Christchurch', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Auckland', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Wanaka', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Queenstown', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Invercargill', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Auckland', 'New Zealand')");
        cities.add("INSERT INTO tblCity VALUES(null, 'Wellington', 'New Zealand')");

        for (int i = 0; i < cities.size(); i++) {
            db.execSQL(cities.get(i));
        }
    }

    public void getCountries() {
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity";
        Cursor recordSet = db.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();

        int countryNameIndex = recordSet.getColumnIndex("countryName");

        allCountries.add("Every City");
        recordSet.moveToFirst();
        for (int r = 0; r < recordCount; r++) {
            countryName = recordSet.getString(countryNameIndex);
            allCountries.add(countryName);

            recordSet.moveToNext();
        }
    }

    public void getCities(String country) {
        String selectQuery = "";

        if (country == "Every City") {
            selectQuery = "SELECT * FROM tblCity";
        } else {
            selectQuery = "SELECT * FROM tblCity WHERE countryName LIKE '" + country + "'";
        }

        Cursor recordSet = db.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        displayStringArray = new String[recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityName");
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();
        for (int r = 0; r < recordCount; r++) {
            cityName = recordSet.getString(cityNameIndex);
            countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = cityName + ", " + countryName;

            recordSet.moveToNext();
        }
    }

    public class selectCountryHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setUpList(spinnerCountries.getSelectedItem().toString());
        }
    }
}
