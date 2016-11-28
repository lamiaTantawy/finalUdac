package app.com.example.android.project_popularmoves;

import android.net.Uri;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by lamia on 25/11/2016.
 */

public class AsyncReviewsBackground extends AsyncTask<String,Void,List<String> > {
    private static final String LOG_TAG = AsyncReviewsBackground.class.getSimpleName();
    @Override
    protected List<String> doInBackground(String... params) {
        //http://api.themoviedb.org/3/movie/328111/reviews?api_key=ebdebc32ff035226e98000b48fd0bac9
        //
        String baseUrl = "http://api.themoviedb.org/3/movie/"+params[0]+"/reviews?api_key=ebdebc32ff035226e98000b48fd0bac9";

        Uri builtUri = Uri.parse(baseUrl).buildUpon().build();
        InternetServices internet = new InternetServices();
        HttpURLConnection urlConnection = internet.openConnection(builtUri,"GET");
        String str = internet.ReadInputStream(urlConnection);
       // Log.v(LOG_TAG,str);
        Parsing parsing = new Parsing();
        return parsing.parseJsonToGetReview(str);
    }
}
