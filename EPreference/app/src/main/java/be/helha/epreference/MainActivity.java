package be.helha.epreference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.helha.epreference.domain.Bookmark;
import be.helha.epreference.ui.ListBookmarkAdapter;

import static java.lang.System.out;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity
{
    private static final int REQ_CODE_BOOKMARK_ACTIVITY = 1;
    private Button buttonCreateBookmark;
    private ListView listViewBookmarks;
    private List< Bookmark > bookmarks = new ArrayList<>();
    private ListBookmarkAdapter bookmarkAdapter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        gson = new Gson();
        preferences = getSharedPreferences( "bookmarks", MODE_PRIVATE );
        String res = preferences.getString( "bookmarks", "[]" );
        editor = preferences.edit();
        Bookmark[] bookmarksArray = gson.fromJson( res, Bookmark[].class );
        bookmarks.addAll( Arrays.asList( bookmarksArray ) );
        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        buttonCreateBookmark = findViewById( R.id.btn_mainActivity_createBookmark );
        listViewBookmarks = findViewById( R.id.lv_MainActivity_bookmarks );
        bookmarkAdapter = new ListBookmarkAdapter( getApplicationContext(), bookmarks );
        listViewBookmarks.setAdapter( bookmarkAdapter );
    }

    private void initListeners()
    {
        buttonCreateBookmark.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent goToCreateBookmarkActivityIntent = new Intent( MainActivity.this, CreateBookmarkActivity.class );
                startActivityForResult( goToCreateBookmarkActivityIntent, REQ_CODE_BOOKMARK_ACTIVITY );
            }
        } );
        listViewBookmarks.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Bookmark bookmark = ( Bookmark ) parent.getItemAtPosition( position );

                String url = bookmark.getBookmarkURL();

                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );

                startActivity( intent );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data )
    {
        if( requestCode == REQ_CODE_BOOKMARK_ACTIVITY && resultCode == RESULT_OK )
        {
            if( data != null )
            {
                Bookmark bookmark = data.getParcelableExtra( CreateBookmarkActivity.KEY_BOOKMARK );
                addBookmarkToListView( bookmark );
            }
        }
        super.onActivityResult( requestCode, resultCode, data );
    }

    private void addBookmarkToListView( Bookmark bookmark )
    {
        bookmarks.add( bookmark );
        bookmarkAdapter.notifyDataSetChanged();
        String bookmarkJson = gson.toJson( bookmarks );
        editor.putString( "bookmarks", bookmarkJson );
        editor.apply();
        Log.i( "JSON", bookmarkJson );
    }
}
