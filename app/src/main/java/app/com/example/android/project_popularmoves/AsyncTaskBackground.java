package app.com.example.android.project_popularmoves;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by lamia on 17/10/2016.
 */

public class AsyncTaskBackground extends AsyncTask<String,Void,List<Movie> > {
    private static final String LOG_TAG = AsyncTaskBackground.class.getSimpleName();
    ImagesAdapter imagesAdapter;
    Context context;
    AsyncTaskBackground(Context context,ImagesAdapter imagesAdapter)
    {
        this.context = context;
        this.imagesAdapter = imagesAdapter;
    }
    @Override
    protected List<Movie> doInBackground(String... params) {
        if(params.length==0)return null;
        //String baseUrl = "http://api.themoviedb.org/3/movie/popular?api_key=ebdebc32ff035226e98000b48fd0bac9";
        String baseUrl = "http://api.themoviedb.org/3/movie/"+params[0]+"?api_key=ebdebc32ff035226e98000b48fd0bac9";
        Uri builtUri = Uri.parse(baseUrl).buildUpon().build();
        InternetServices internet = new InternetServices();
        HttpURLConnection urlConnection = internet.openConnection(builtUri,"GET");
        String str = internet.ReadInputStream(urlConnection);
        Parsing parsing = new Parsing();
        return parsing.ParseJsonToGetImages(str);
       // return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onPostExecute(List<Movie> movies) {
       // super.onPostExecute(movies);
        if(movies != null)
        {
            imagesAdapter.clear();
            imagesAdapter.addAll(movies);
        }
    }

}
