package be.helha.playgroundlists.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Movie implements Parcelable
{
    private String title;
    private String producer;
    private int rating;

    public Movie( String title, String producer, int rating )
    {
        this.title = title;
        this.producer = producer;
        this.rating = rating;
    }

    protected Movie( Parcel in )
    {
        title = in.readString();
        producer = in.readString();
        rating = in.readInt();
    }

    public static final Creator< Movie > CREATOR = new Creator< Movie >()
    {
        @Override
        public Movie createFromParcel( Parcel in )
        {
            return new Movie( in );
        }

        @Override
        public Movie[] newArray( int size )
        {
            return new Movie[ size ];
        }
    };

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getProducer()
    {
        return producer;
    }

    public void setProducer( String producer )
    {
        this.producer = producer;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating( int rating )
    {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        return "Movie{" +
                "title='" + title + '\'' +
                ", producer='" + producer + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString( title );
        dest.writeString( producer );
        dest.writeInt( rating );
    }
}
