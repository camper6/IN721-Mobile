package bit.abduhs1.topartistimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new ButtonSetImageHandler());
    }

    public String getImageURL(String JSONInput) {
        String topArtist = null;
        try {
            JSONObject artists = new JSONObject(JSONInput);
            JSONObject artist = artists.getJSONObject("artists");
            JSONArray artistArray = artist.getJSONArray("artist");

            JSONObject currentObject = artistArray.getJSONObject(0);
            JSONArray artistImage = currentObject.getJSONArray("image");
            JSONObject imageURL = artistImage.getJSONObject(4);
            topArtist = imageURL.getString("#text");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topArtist;
    }

    public class AsyncAPISetImage extends AsyncTask<Void, Void, Bitmap> {

        public InputStream getInputStream(String url) {
            URL URLObject = null;
            try {
                URLObject = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            InputStream inputStream = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    inputStream = connection.getInputStream();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String urlString = "http://ws.audioscrobbler.com/2.0/?api_key=58384a2141a4b9737eacb9d0989b8a8c&method=chart.gettopartists&format=json";

            InputStream inputStream = getInputStream(urlString);
            String JSONString = null;
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null) {
                    stringBuilder = stringBuilder.append(responseString);
                }
                JSONString = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String url = getImageURL(JSONString);

            inputStream = getInputStream(url);
            Bitmap topArtist = BitmapFactory.decodeStream(inputStream);

            return topArtist;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    public class ButtonSetImageHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            AsyncAPISetImage apiThread = new AsyncAPISetImage();
            apiThread.execute();
        }
    }
}
