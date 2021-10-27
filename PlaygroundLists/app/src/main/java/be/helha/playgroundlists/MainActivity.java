package be.helha.playgroundlists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.helha.playgroundlists.domain.Movie;
import be.helha.playgroundlists.ui.ListMovieAdapter;

public class MainActivity extends AppCompatActivity
{
    protected static final String KEY_MOVIE = "key_movie";
    private ListView listView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        listView = findViewById( R.id.lv_mainActivity );

        //initSimpleListView();
        initMoviesListView();
    }

    private void initSimpleListView()
    {
        String[] values = new String[]
                {
                        "Bonjour",
                        "Hello",
                        "Hi"
                };

        ArrayAdapter< String > adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.list_item_simple,
                R.id.tv_listItemSimple_value,
                values
        );

        listView.setAdapter( adapter );
    }

    private void initMoviesListView()
    {
        List< Movie > movies = new ArrayList<>();
        movies.add( new Movie( "Inception", "Producer", 4 ) );
        movies.add( new Movie( "Angular, la douce promesse", "M. P.", 5 ) );
        movies.add( new Movie( "TI Examen, la douce peur", "M. P.", 1 ) );

        ListMovieAdapter adapter = new ListMovieAdapter( getApplicationContext(), movies );

        listView.setAdapter( adapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Movie movie = ( Movie ) parent.getItemAtPosition( position );

                Intent intent = new Intent( MainActivity.this, DetailMovieActivity.class );
                intent.putExtra( KEY_MOVIE, movie );

                startActivity( intent );
            }
        } );
    }
}
