package be.helha.todo.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class DtoTodo implements Parcelable
{
    private int id;
    private String title;
    private boolean isDone;

    public DtoTodo( int id, String title, boolean isDone )
    {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    protected DtoTodo( Parcel in )
    {
        id = in.readInt();
        title = in.readString();
        isDone = in.readByte() != 0;
    }

    public static final Creator< DtoTodo > CREATOR = new Creator< DtoTodo >()
    {
        @Override
        public DtoTodo createFromParcel( Parcel in )
        {
            return new DtoTodo( in );
        }

        @Override
        public DtoTodo[] newArray( int size )
        {
            return new DtoTodo[ size ];
        }
    };

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public boolean isDone()
    {
        return isDone;
    }

    public void setDone( boolean done )
    {
        isDone = done;
    }

    @Override
    public String toString()
    {
        return "DtoTodo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isDone=" + isDone +
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
        dest.writeInt( id );
        dest.writeString( title );
        dest.writeByte( ( byte ) ( isDone ? 1 : 0 ) );
    }
}
