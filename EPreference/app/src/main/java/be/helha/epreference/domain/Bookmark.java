package be.helha.epreference.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Bookmark implements Parcelable
{
    private String bookmarkName;
    private String bookmarkFirstLetter;
    private String bookmarkURL;

    public Bookmark( String bookmarkName, String bookmarkFirstLetter, String bookmarkURL )
    {
        this.bookmarkName = bookmarkName;
        this.bookmarkFirstLetter = bookmarkFirstLetter;
        this.bookmarkURL = bookmarkURL;
    }

    protected Bookmark( Parcel in )
    {
        bookmarkName = in.readString();
        bookmarkFirstLetter = in.readString();
        bookmarkURL = in.readString();
    }

    public static final Creator< Bookmark > CREATOR = new Creator< Bookmark >()
    {
        @Override
        public Bookmark createFromParcel( Parcel in )
        {
            return new Bookmark( in );
        }

        @Override
        public Bookmark[] newArray( int size )
        {
            return new Bookmark[ size ];
        }
    };

    public String getBookmarkName()
    {
        return bookmarkName;
    }

    public void setBookmarkName( String bookmarkName )
    {
        this.bookmarkName = bookmarkName;
    }

    public String getBookmarkFirstLetter()
    {
        return bookmarkFirstLetter;
    }

    public void setBookmarkFirstLetter( String bookmarkFirstLetter )
    {
        this.bookmarkFirstLetter = bookmarkFirstLetter;
    }

    public String getBookmarkURL()
    {
        return bookmarkURL;
    }

    public void setBookmarkURL( String bookmarkURL )
    {
        this.bookmarkURL = bookmarkURL;
    }

    @Override
    public String toString()
    {
        return "Bookmark{" +
                "bookmarkName='" + bookmarkName + '\'' +
                ", bookmarkFirstLetter='" + bookmarkFirstLetter + '\'' +
                ", bookmarkURL='" + bookmarkURL + '\'' +
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
        dest.writeString( bookmarkName );
        dest.writeString( bookmarkFirstLetter );
        dest.writeString( bookmarkURL );
    }
}
