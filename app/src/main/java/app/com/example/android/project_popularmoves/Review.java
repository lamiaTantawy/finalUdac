package app.com.example.android.project_popularmoves;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lamia on 25/11/2016.
 */

public class Review implements Parcelable {
    String ID;
    String Author;
    String Content;
    String Url;

    protected Review(Parcel in) {
        ID = in.readString();
        Author = in.readString();
        Content = in.readString();
        Url = in.readString();
    }
    protected Review(){}
    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Author);
        dest.writeString(Content);
        dest.writeString(Url);
    }
}
