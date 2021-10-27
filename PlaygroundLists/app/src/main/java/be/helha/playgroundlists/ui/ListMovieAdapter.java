package be.helha.playgroundlists.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import be.helha.playgroundlists.R;
import be.helha.playgroundlists.domain.Movie;

public class ListMovieAdapter extends ArrayAdapter< Movie >
{
    public ListMovieAdapter( @NonNull Context context, @NonNull List< Movie > objects )
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
            convertView = inflater.inflate( R.layout.list_item_movie, null );
        }

        Movie movie = getItem( position );
        populateView( movie, convertView );

        return convertView;
    }

    private void populateView( Movie movie, View convertView )
    {
        TextView tvTitle = convertView.findViewById( R.id.tv_listItemMovie_title );
        TextView tvRating = convertView.findViewById( R.id.tv_listItemMovie_rating );
        TextView tvProducer = convertView.findViewById( R.id.tv_listItemMovie_producer );

        tvTitle.setText( movie.getTitle() );
        tvRating.setText( String.valueOf( movie.getRating() ) );
        tvProducer.setText( movie.getProducer() );
    }
}
