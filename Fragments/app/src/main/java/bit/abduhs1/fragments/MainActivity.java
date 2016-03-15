package bit.abduhs1.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        Button btnImageFragment = (Button) findViewById(R.id.btnShowImageFragment);
        btnImageFragment.setOnClickListener(new ShowImageHandler());
        Button btnListViewFragment = (Button) findViewById(R.id.btnShowListViewFragment);
        btnListViewFragment.setOnClickListener(new ShowListViewHandler());
    }

    public class ShowImageHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //The Activity instantiates a Fragment
            Fragment dynamicFragment = new ShowImage();
            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, dynamicFragment);
            ft.commit();
        }
    }

    public class ShowListViewHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Fragment dynamicFragment = new ShowListView();
            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            //ft.replace(R.id.fragment_container, dynamicFragment);
            ft.replace(R.id.fragment_container2, dynamicFragment);
            ft.commit();
        }
    }
}
