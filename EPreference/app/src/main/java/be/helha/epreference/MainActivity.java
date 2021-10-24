package be.helha.epreference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.helha.epreference.domain.Bookmark;
import be.helha.epreference.ui.ListBookmarkAdapter;

public class MainActivity extends AppCompatActivity
{
    private static final int REQ_CODE_BOOKMARK_ACTIVITY = 1;
    private Button buttonCreateBookmark;
    private ListView listViewBookmarks;
    private final List< Bookmark > bookmarks = new ArrayList<>();
    private ListBookmarkAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initViewInstances();
        initListeners();
        initBookmarkListView();
    }

    private void initViewInstances()
    {
        buttonCreateBookmark = findViewById( R.id.btn_mainActivity_createBookmark );
        listViewBookmarks = findViewById( R.id.lv_MainActivity_bookmarks );
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
    }

    private void initBookmarkListView()
    {
        adapter = new ListBookmarkAdapter( getApplicationContext(), bookmarks );
        listViewBookmarks.setAdapter( adapter );
        listViewBookmarks.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id )
            {
                Bookmark bookmark = ( Bookmark ) parent.getItemAtPosition( position );

                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( bookmark.getBookmarkURL() ) );

                startActivity( intent );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );

        if( requestCode == REQ_CODE_BOOKMARK_ACTIVITY && resultCode == RESULT_OK )
        {
            Bookmark bookmark = data.getParcelableExtra( CreateBookmarkActivity.KEY_BOOKMARK );
            bookmarks.add( bookmark );
            adapter.notifyDataSetChanged();
        }
    }
}
