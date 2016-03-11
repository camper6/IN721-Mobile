package bit.abduhs1.dunedin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        setUpGroupList();

        ListView groupListView = (ListView) findViewById(R.id.listView);
        groupListView.setOnItemClickListener(new GroupNavListClickHandler());
    }

    public void setUpGroupList() {
        String[] group = {"Services,", "Fun Things To Do", "Dining", "Shopping"};

        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, group);

        ListView groupListView = (ListView) findViewById(R.id.listView);

        groupListView.setAdapter(groupAdapter);
    }

    public class GroupNavListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id) {
            String clickedItem = (String) list.getItemAtPosition(position).toString();
            Intent goToIntent;

            switch (clickedItem) {
                case "Services":
                    goToIntent = new Intent (MainNavigation.this, Services.class);
                    break;
                case "Fun Things To Do":
                    goToIntent = new Intent (MainNavigation.this, Activities.class);
                    break;
                case "Dining":
                    goToIntent = new Intent (MainNavigation.this, Dining.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent (MainNavigation.this, Shopping.class);
                    break;
                default:
                    goToIntent = null;
            }

            if (goToIntent != null)
                startActivity(goToIntent);
        }
    }

    public class ListViewWithToastHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickedItemsString = (String) parent.getItemAtPosition(position).toString();

            Toast.makeText(MainNavigation.this, clickedItemsString, Toast.LENGTH_LONG).show();
        }
    }

}
