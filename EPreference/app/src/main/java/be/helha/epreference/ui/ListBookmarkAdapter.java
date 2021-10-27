package be.helha.epreference.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import be.helha.epreference.R;
import be.helha.epreference.domain.Bookmark;

public class ListBookmarkAdapter extends ArrayAdapter< Bookmark  >
{
    public ListBookmarkAdapter( @NonNull Context context, @NonNull List< Bookmark > objects )
    {
        super( context, 0, objects );
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent )
    {
        if( convertView == null )
        {
            LayoutInflater inflater = LayoutInflater.from( getContext() );
            convertView = inflater.inflate( R.layout.list_item_bookmark, null );
        }

        Bookmark bookmark = getItem( position );
        populateView( bookmark, convertView );

        return convertView;
    }

    private void populateView( Bookmark bookmark, View convertView )
    {
        TextView tvFirstLetter = convertView.findViewById( R.id.tv_listItem_firstLetter );
        TextView tvURL = convertView.findViewById( R.id.tv_listItem_url );
        TextView tvSiteName = convertView.findViewById( R.id.tv_listItem_siteName );

        tvFirstLetter.setText( String.valueOf( bookmark.getBookmarkName().charAt( 0 ) ).toUpperCase() );
        tvURL.setText( bookmark.getBookmarkURL() );
        tvSiteName.setText( bookmark.getBookmarkName() );
    }
}
