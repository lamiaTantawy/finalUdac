package app.com.example.android.project_popularmoves;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lamia on 25/11/2016.
 */

public class Trailer implements Parcelable {
    String ID;
    String Key;
    String Name;
    String Site;
    String Size;
    String Type;

    protected Trailer(Parcel in) {
        ID = in.readString();
        Key = in.readString();
        Name = in.readString();
        Site = in.readString();
        Size = in.readString();
        Type = in.readString();
    }
    protected Trailer(){}
    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Key);
        dest.writeString(Name);
        dest.writeString(Site);
        dest.writeString(Size);
        dest.writeString(Type);
    }
}
