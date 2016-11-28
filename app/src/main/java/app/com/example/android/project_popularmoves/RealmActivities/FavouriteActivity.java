package app.com.example.android.project_popularmoves.RealmActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.com.example.android.project_popularmoves.R;

public class FavouriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, new FavListFragment())
                    .commit();
        }
    }
}
