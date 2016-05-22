package bit.abduhs1.iconnlibraries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        String itemTitle = (String)item.getTitle();
        Intent goToIntent;

        switch (itemTitle) {
            case "Ripple":
                goToIntent = new Intent(MainActivity.this, Ripple.class);
                break;
            case "Explode":
                goToIntent = new Intent(MainActivity.this, Explode.class);
                break;
            case "Stand Up":
                goToIntent = new Intent(MainActivity.this, StandUp.class);
                break;
            default:
                goToIntent = null;
        }
        if (goToIntent != null) {
            startActivity(goToIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
