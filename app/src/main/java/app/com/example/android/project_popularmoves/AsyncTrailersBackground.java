package app.com.example.android.project_popularmoves;

import android.net.Uri;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by lamia on 24/11/2016.
 */

public class AsyncTrailersBackground extends AsyncTask<String,Void,List<Trailer> > {

    private static final String LOG_TAG = AsyncTrailersBackground.class.getSimpleName();

    @Override
    protected List<Trailer> doInBackground(String... params) {
        //http://api.themoviedb.org/3/movie/328111/videos?api_key=ebdebc32ff035226e98000b48fd0bac9
        String baseUrl = "http://api.themoviedb.org/3/movie/"+params[0]+"/videos?api_key=ebdebc32ff035226e98000b48fd0bac9";

        Uri builtUri = Uri.parse(baseUrl).buildUpon().build();
        InternetServices internet = new InternetServices();
        HttpURLConnection urlConnection = internet.openConnection(builtUri,"GET");
        String str = internet.ReadInputStream(urlConnection);
        Parsing parsing = new Parsing();
        return parsing.parseJsonToGetTrailer(str);
    }

}
