package app.com.example.android.project_popularmoves;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MovieListener{
    boolean isTwoPane=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("Movies") && findViewById(R.id.fldetail)!=null)
        {
           // Log.v("EEEEEEEE>","has extra");
            Movie m =(Movie) getIntent().getParcelableExtra("Movies");
            DetailedFragment detailedFragment = new DetailedFragment();
            Bundle extras = new Bundle();
            extras.putParcelable("Movies",(Parcelable) m);
            detailedFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.fldetail,detailedFragment,"").commit();
        }
        PicturesFragment picturesFragment = new PicturesFragment();
        picturesFragment.setMovieListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.flMain,picturesFragment,"").commit();
        if(findViewById(R.id.fldetail)!=null)
        {
            isTwoPane = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setSelectedMovie(String ID, String overview, String userRating, String releaseData, String imageURL, String title) {
        Log.e("Listener==>",ID+" "+title);
        Movie movie = new Movie();
        movie.setID(ID);
        movie.setOverview(overview);
        movie.setUserRating(userRating);
        movie.setReleaseData(releaseData);
        movie.setImageURL(imageURL);
        movie.setTitle(title);
        if(!isTwoPane)
        {
            Intent intent = new Intent(this,DetailedImageActivity.class);

                intent.putExtra("Movies", (Parcelable) movie);
                startActivity(intent);
        }
        else
        {
            DetailedFragment detailedFragment = new DetailedFragment();
            Bundle extras = new Bundle();
            extras.putParcelable("Movies",(Parcelable) movie);
            detailedFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.fldetail,detailedFragment,"").commit();
        }
    }
}
