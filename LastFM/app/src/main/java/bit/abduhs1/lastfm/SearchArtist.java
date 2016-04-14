package bit.abduhs1.lastfm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class SearchArtist extends AppCompatActivity {

    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_artist);

        listView2 = (ListView) findViewById(R.id.listView2);
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new ButtonSearchHandler());
    }

    public ArrayList<String> getJSONValues(String JSONInput) {
        ArrayList<String> values = new ArrayList<String>();
        try {
            JSONObject artists = new JSONObject(JSONInput);
            JSONObject artist = artists.getJSONObject("similarartists");
            JSONArray artistArray = artist.getJSONArray("artist");

            int nArtists = artistArray.length();

            for (int i = 0; i < nArtists; i++) {
                JSONObject currentObject = artistArray.getJSONObject(i);
                String artistName = currentObject.getString("name");
                values.add(artistName);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return values;
    }

    public class AsyncAPIGetJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String artist = params[0];
                String urlString = "http://ws.audioscrobbler.com/2.0/?api_key=58384a2141a4b9737eacb9d0989b8a8c&method=artist.getsimilar&artist="+artist+"&limit=10&format=json";

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
            ArrayList<String> artists = getJSONValues(s);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(SearchArtist.this, android.R.layout.simple_list_item_1, artists);
            listView2.setAdapter(listAdapter);
        }
    }

    public class ButtonSearchHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText artist = (EditText) findViewById(R.id.editTextArtist);
            AsyncAPIGetJSON APIThread = new AsyncAPIGetJSON();
            if (!(artist.getText().toString().matches(""))) {
                APIThread.execute(artist.getText().toString());
            }
        }
    }
}
