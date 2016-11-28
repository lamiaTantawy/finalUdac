package app.com.example.android.project_popularmoves;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import app.com.example.android.project_popularmoves.RealmActivities.FavouriteActivity;

/**
 * Created by lamia on 19/10/2016.
 */

public class PicturesFragment extends Fragment {
    List<Movie> nameImages= new ArrayList<Movie>();
    private ImagesAdapter imagesAdapter;
    GridView gridView;
    private MovieListener movieListener;
    void setMovieListener(MovieListener listener)
    {
        this.movieListener = listener;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Context context = getActivity();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.picturesfragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings)
        {
            Intent intent = new Intent(getActivity(),SettingsPrefrence.class);
            startActivity(intent);
        }
        if(id == R.id.action_Favourite)
        {
            Intent intent = new Intent(getActivity(),FavouriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void updateTopRatedImages()
    {
        AsyncTaskBackground async = new AsyncTaskBackground(getActivity(),imagesAdapter);
        async.execute("top_rated");
        try {
            nameImages = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void updatePopularImages()
    {
        AsyncTaskBackground async = new AsyncTaskBackground(getActivity(),imagesAdapter);
        async.execute("popular");
        try {
            nameImages = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.pictures_fragment, container, false);
        imagesAdapter = new ImagesAdapter(getActivity(), new ArrayList<Movie>());
        gridView = (GridView) rootView.findViewById(R.id.imgLV);
        gridView.setAdapter(imagesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieListener.setSelectedMovie(imagesAdapter.getItem(position).getID(),imagesAdapter.getItem(position).getOverview()
                ,imagesAdapter.getItem(position).getUserRating(),imagesAdapter.getItem(position).getReleaseData(),imagesAdapter.getItem(position).getImageURL()
                ,imagesAdapter.getItem(position).getTitle());
//                Intent intent = new Intent(getActivity(),DetailedImageActivity.class);
//                intent.putExtra("Movies", (Parcelable) imagesAdapter.getItem(position));
//                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        String prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getString(R.string.Settings_key), "Popular");

        if(prefs.equals(getString(R.string.Settings_label_TopRated)))
        {
            updateTopRatedImages();
        }
        else
        updatePopularImages();
    }
}
