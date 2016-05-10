package bit.abduhs1.usinglocation;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private double latitude;
    private double longtitude;
    private Random rGen;
    private DecimalFormat decFormat;

    TextView tvLatitudeValue;
    TextView tvLongtitudeValue;
    TextView tvCity;
    ImageView imageView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rGen = new Random();
        decFormat = new DecimalFormat("##.###");
        setCoordinate();

        tvLatitudeValue = (TextView) findViewById(R.id.tvLatitudeValue);
        tvLongtitudeValue = (TextView) findViewById(R.id.tvLongtitudeValue);
        tvCity = (TextView) findViewById(R.id.tvCity);
        imageView = (ImageView) findViewById(R.id.imageView);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria defaultCriteria = new Criteria();
        String providerName = locationManager.getBestProvider(defaultCriteria, false);

        // Not sure about the minTime
        locationManager.requestLocationUpdates(providerName, 500, 100, new customLocationListener());

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(providerName);

        Button btTeleport = (Button)findViewById(R.id.btTeleport);
        btTeleport.setOnClickListener(new TeleportButtonListener());
    }

    private void setCoordinate() {
        // Random * Range + Minimum AKA Max * 2 (Random) - Minimum
        // (Random * (Max + Max)) - Min
        latitude = rGen.nextDouble() * (90*2) - 90;
        longtitude = rGen.nextDouble() * (180*2) - 180;
    }

    public void setTextViewCoordinate() {
        tvLatitudeValue.setText(String.valueOf(decFormat.format(latitude)));
        tvLongtitudeValue.setText(String.valueOf(decFormat.format(longtitude)));
    }

    public String getCityValues(String JSONInput, String name) {
        String result = "";

        try {
            JSONObject json = new JSONObject(JSONInput);
            result = json.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void setTextViewCity(String city, String code) {
        tvCity.setText("Closest City: " + city + ", " + code);
    }

    public void setImageView(Bitmap image) {
        imageView.setImageBitmap(image);
    }

    /*
    public void setTextViewCity(String JSONInput) {
        String city = "";
        String countryCode = "";

        try {
            if (!JSONInput.equals("[[]]")) { // Using == operator does not catch the empty value. Why?
                JSONObject place = new JSONObject(JSONInput);
                city = place.getString("geoplugin_place");
                countryCode = place.getString("geoplugin_countryCode");
                tvCityValue.setText(city + ", " + countryCode);
            } else {
                tvCityValue.setText("You've landed in the ocean");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public class TeleportButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setCoordinate();
            //setTextViewCoordinate();
            AsyncAPIGetNearestCity getCity = new AsyncAPIGetNearestCity(MainActivity.this);
            //AsyncAPIGetJSON getCity = new AsyncAPIGetJSON();
            getCity.execute();
        }
    }

    public String getJSON(String url) {
        URL URLObject = null;
        try {
            URLObject = new URL(url);
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

    public String getGeopluginJSON() {
        String url = "http://www.geoplugin.net/extras/location.gp?lat=" + latitude + "&long=" + longtitude + "&format=json";

        String json = getJSON(url);

        if (json.equals("[[]]")) {
            json = null;
            setCoordinate();
        }

        return json;
    }

    public Bitmap getCityImage(String city, String code) {
        Bitmap image = null;
        // Needs better result
        //String flickr = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=eda41a123d459be0f85276d37290651e&accuracy=11&tag=" + city + "%2C%20" + code + "&format=json&nojsoncallback=1";
        String flickr = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=eda41a123d459be0f85276d37290651e&tag=city&text=" + city + "%2C%20" + code + "&format=json&nojsoncallback=1";

        String photoURL = null;
        String photosJSON = getJSON(flickr);
        try {
            JSONObject json = new JSONObject(photosJSON);
            JSONObject photosObject = json.getJSONObject("photos");
            JSONArray photoArray = photosObject.getJSONArray("photo");

            if (photoArray.length() > 0) {
                JSONObject photo = photoArray.getJSONObject(0); // Gets the first result
                // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
                String photoID = photo.getString("id");
                String secretID = photo.getString("secret");
                String serverID = photo.getString("server");
                String farmID = photo.getString("farm");

                photoURL = "https://farm" + farmID + ".staticflickr.com/" + serverID + "/" + photoID + "_" + secretID + "_m.jpg";
                image = getImage(photoURL);
            } else {
                image = getImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/240px-No_image_available.svg.png");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return image;
    }

    public Bitmap getImage(String url) {
        Bitmap photo = null;

        URL URLObject = null;
        try {
            URLObject = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                photo = BitmapFactory.decodeStream(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return photo;
    }

    public class customLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longtitude = location.getLongitude();

            AsyncAPIGetNearestCity getCity = new AsyncAPIGetNearestCity(MainActivity.this);
            getCity.execute();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public class AsyncAPIGetNearestCity extends AsyncTask<Void, Void, Void> {

        String city = "";
        String code = "";
        Bitmap image = null;

        public AsyncAPIGetNearestCity(MainActivity activity) {
            progressDialog = new ProgressDialog(activity); // Better to put here than in the buttons, because upon execute, the Dialog won't receive any feedbacks it needs if in the button
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            String json = null;
            while (json == (null)) {

                json = getGeopluginJSON();
            }

            progressDialog.dismiss();

            city = getCityValues(json, "geoplugin_place");
            code = getCityValues(json, "geoplugin_countryCode");
            image = getCityImage(city, code);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setTextViewCoordinate();
            setTextViewCity(city, code);
            setImageView(image);
        }
    }

    /*
    public class AsyncAPIGetJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String url = "http://www.geoplugin.net/extras/location.gp?lat=" + latitude + "&long=" + longtitude + "&format=json";

            URL URLObject = null;
            try {
                URLObject = new URL(url);
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
            setTextViewCity(s);
        }
    }*/
}
