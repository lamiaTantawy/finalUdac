package app.com.example.android.project_popularmoves.RealmActivities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import app.com.example.android.project_popularmoves.DetailedImageActivity;
import app.com.example.android.project_popularmoves.FavoritMovie;
import app.com.example.android.project_popularmoves.MainActivity;
import app.com.example.android.project_popularmoves.Movie;
import app.com.example.android.project_popularmoves.MovieListener;
import app.com.example.android.project_popularmoves.R;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by lamia on 26/11/2016.
 */

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.ViewHolder> implements RealmChangeListener {
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageV;

        public ViewHolder(final ImageButton ImgView) {
            super(ImgView);
            imageV = ImgView;

        }
    }
    private final RealmResults<FavoritMovie> fav;
    private static Activity context;
    public FavListAdapter(RealmResults<FavoritMovie> Movies, Activity context) {
        fav = Movies;
        fav.addChangeListener(this);
        this.context = context;
    }
    private MovieListener movieListener;
    void setMovieListener(MovieListener listener)
    {
        this.movieListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite, parent, false);
     //   imageView = (ImageView)view.findViewById(R.id.imgView);
        return new ViewHolder((ImageButton) view);
    }
    public static boolean isTablet() {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       // holder.mTextTitle.setText(fav.get(position).getImageURL());
        ImageButton img = holder.imageV;
        Picasso.with(context).load(fav.get(position).getImageURL()).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                movie.setID(fav.get(position).getId());
                movie.setImageURL(fav.get(position).getImageURL());
                movie.setUserRating(fav.get(position).getUserRating());
                movie.setTitle(fav.get(position).getTitle());
                movie.setReleaseData(fav.get(position).getReleaseData());
                movie.setOverview(fav.get(position).getOverview());

                if(isTablet())
                {
                    Log.v("Tabletttt>","has extra");
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.putExtra("Movies", (Parcelable) movie);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context,DetailedImageActivity.class);
                    intent.putExtra("Movies", (Parcelable) movie);
                    context.startActivity(intent);
                }

            }
        });
       // Picasso.with(context).load(fav.get(position).getImageURL()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return fav.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }


}
