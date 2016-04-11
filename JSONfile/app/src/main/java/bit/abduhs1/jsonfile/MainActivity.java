package bit.abduhs1.jsonfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonFill;
    ListView listViewEvents;
    ArrayList<String> eventTitle;
    ArrayList<String> eventDescription;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFill = (Button) findViewById(R.id.buttonFill);
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);

        String JSONInput = getJSONInput();
        eventTitle = getJSONValues(JSONInput, "title");
        eventDescription = getJSONValues(JSONInput, "description");

        listAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, eventTitle);

        buttonFill.setOnClickListener(new ButtonFillHandler());
        if (listAdapter != null) {
            listViewEvents.setOnItemClickListener(new EventClickHandler());
        }
    }

    public String getJSONInput() {
        String assetFileName = "dunedin_events.json";
        String JSONInput = "";
        AssetManager am = getAssets();
        try {
            InputStream inputStream = am.open(assetFileName);

            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            inputStream.read(JSONBuffer);
            inputStream.close();

            JSONInput = new String(JSONBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONInput;
    }

    public ArrayList<String> getJSONValues(String JSONInput, String key) {

        ArrayList<String> values = new ArrayList<String>();

        try {
            JSONObject dunedinEvents = new JSONObject(JSONInput);
            JSONObject events = dunedinEvents.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            int nEvents = eventArray.length();

            for (int i = 0; i < nEvents; i++) {
                JSONObject currentEventObject = eventArray.getJSONObject(i);
                values.add(currentEventObject.getString(key));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return values;
    }

    public class ButtonFillHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            listViewEvents.setAdapter(listAdapter);
        }
    }

    public class EventClickHandler implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, eventDescription.get(position), Toast.LENGTH_LONG).show();
        }
    }
}
