package app.com.example.android.project_popularmoves;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lamia on 21/10/2016.
 */

public class ImagesAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = ImagesAdapter.class.getSimpleName();
    private final Activity context;
    private List<Movie> ListOfMovies;

    public ImagesAdapter(Activity context , List<Movie> ListOfMovies)
    {
        super(context,0,ListOfMovies);
        this.context = context;
        this.ListOfMovies = ListOfMovies;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.images,parent,false);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.images);
        Picasso.with(context).load(ListOfMovies.get(position).getImageURL()).into(imageView);
        return convertView;
    }
}
