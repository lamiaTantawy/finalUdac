package app.com.example.android.project_popularmoves;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lamia on 26/11/2016.
 */

public class FavoritMovie extends RealmObject {
    @PrimaryKey
    private String Id;

    private String ImageURL;
    private String Title;
    private String Overview;
    private String UserRating;
    private String ReleaseData;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getUserRating() {
        return UserRating;
    }

    public void setUserRating(String userRating) {
        UserRating = userRating;
    }

    public String getReleaseData() {
        return ReleaseData;
    }

    public void setReleaseData(String releaseData) {
        ReleaseData = releaseData;
    }

    public String getId() {
        return Id;
    }

    public void setId(String ID) {
        this.Id = ID;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

}
