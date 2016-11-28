package app.com.example.android.project_popularmoves;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lamia on 21/10/2016.
 */

public class Movie implements Parcelable{
    String ID;
    String ImageURL;
    String Title;
    String Overview;
    String UserRating;
    String ReleaseData;
    public Movie()
    {

    }
    protected Movie(String ImageURL,String Title,String Overview,String UserRating,String ReleaseData,String id)
    {
        this.ImageURL = ImageURL;
        this.Title = Title;
        this.Overview = Overview;
        this.UserRating = UserRating;
        this.ReleaseData = ReleaseData;
        this.ID = id;
    }
    protected Movie(Parcel in) {
        ID = in.readString();
        ImageURL = in.readString();
        Title = in.readString();
        Overview = in.readString();
        UserRating = in.readString();
        ReleaseData = in.readString();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getUserRating() {
        return UserRating;
    }

    public void setUserRating(String userRating) {
        UserRating = userRating;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getReleaseData() {
        return ReleaseData;
    }

    public void setReleaseData(String releaseData) {
        ReleaseData = releaseData;
    }


    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(ImageURL);
        dest.writeString(Title);
        dest.writeString(Overview);
        dest.writeString(UserRating);
        dest.writeString(ReleaseData);
    }
}
