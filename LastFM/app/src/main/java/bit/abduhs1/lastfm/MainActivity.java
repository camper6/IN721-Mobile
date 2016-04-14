package bit.abduhs1.lastfm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFill = (Button)findViewById(R.id.buttonFill);
        Button switchActivity = (Button)findViewById(R.id.buttonNewActivity);
        listView = (ListView)findViewById(R.id.listView);

        buttonFill.setOnClickListener(new ButtonFillHandler());
        switchActivity.setOnClickListener(new ButtonSwitchActivity());
    }

    public ArrayList<Artist> getJSONValues(String JSONInput) {
        ArrayList<Artist> values = new ArrayList<Artist>();
        values.add(new Artist("Artist", "Number of Listeners"));
        try {
            JSONObject artists = new JSONObject(JSONInput);
            JSONObject artist = artists.getJSONObject("artists");
            JSONArray artistArray = artist.getJSONArray("artist");

            int nArtists = artistArray.length();

            for (int i = 0; i < nArtists; i++) {
                JSONObject currentObject = artistArray.getJSONObject(i);
                Artist topArtist = new Artist(currentObject.getString("name"), currentObject.getString("listeners"));
                values.add(topArtist);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return values;
    }

    public class AsyncAPIGetJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String urlString = "http://ws.audioscrobbler.com/2.0/?api_key=58384a2141a4b9737eacb9d0989b8a8c&method=chart.gettopartists&format=json";

            URL URLObject = null;
            try {
                URLObject = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            int responseCode = 0;
            String JSONString = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((responseString = bufferedReader.readLine()) != null) {
                        stringBuilder = stringBuilder.append(responseString);
                    }

                    JSONString = stringBuilder.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Artist> artists = getJSONValues(s);
            CustomArrayAdapter listAdapter = new CustomArrayAdapter(MainActivity.this, R.layout.custom_list_item, artists);
            listView.setAdapter(listAdapter);
        }
    }

    public class ButtonFillHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AsyncAPIGetJSON APIThread = new AsyncAPIGetJSON();
            APIThread.execute();
        }
    }

    public class ButtonSwitchActivity implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent goToIntent = new Intent(MainActivity.this, SearchArtist.class);
            startActivity(goToIntent);
        }
    }
}
