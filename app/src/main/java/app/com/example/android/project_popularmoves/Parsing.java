package app.com.example.android.project_popularmoves;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lamia on 19/10/2016.
 */

public class Parsing {
    Movie[] Images ;
    ArrayList<Movie> listOfImages = new ArrayList<Movie>();
    ArrayList<Trailer> listOfTrailers = new ArrayList<Trailer>();
    ArrayList<String> listOfReviews = new ArrayList<String>();
    List<String> parseJsonToGetReview(String JsonSTR)
    {
        final String list = "results";
        try {
            JSONObject jsonObject = new JSONObject(JsonSTR);
            JSONArray jsonArray = jsonObject.getJSONArray(list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject imageName = jsonArray.getJSONObject(i);
                String content = (String) imageName.get("author")+"\n";
                content += (String)imageName.get("content");
                listOfReviews.add(content);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfReviews;
    }
    List<Trailer> parseJsonToGetTrailer(String JsonSTR)
    {
        final String list = "results";
        try {
            JSONObject jsonObject = new JSONObject(JsonSTR);
            JSONArray jsonArray = jsonObject.getJSONArray(list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject imageName = jsonArray.getJSONObject(i);
                String id = (String) imageName.get("id");
                String key = (String) imageName.get("key");
                String name = (String) imageName.get("name");
                String site = (String) imageName.get("site");
                String type = (String) imageName.get("type");
                Trailer trailer = new Trailer();
                trailer.setID(id);
                trailer.setKey(key);
                trailer.setName(name);
                trailer.setSite(site);
                trailer.setType(type);
                listOfTrailers.add(trailer);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfTrailers;
    }
    List<Movie> ParseJsonToGetImages(String JsonSTR)
    {

        final String list = "results";
        try {
            JSONObject jsonObject = new JSONObject(JsonSTR);
            JSONArray jsonArray = jsonObject.getJSONArray(list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject imageName = jsonArray.getJSONObject(i);
                String imageURL = (String) imageName.get("poster_path");
                String OverView = (String) imageName.get("overview");
                String ReleaseData = (String) imageName.get("release_date");
                String ID = (String) String.valueOf(imageName.getInt("id"));
                String OriginalTitle = (String) imageName.get("original_title");
                String UserRating = (String) String.valueOf(imageName.getDouble("vote_average"));
                Movie movie = new Movie();
                 movie.setImageURL("http://image.tmdb.org/t/p/w185//"+imageURL.toString());
                movie.setOverview(OverView);
                movie.setReleaseData(ReleaseData);
                movie.setID(ID);
                movie.setTitle(OriginalTitle);
                movie.setUserRating(UserRating);
                listOfImages.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfImages;
    }
}
