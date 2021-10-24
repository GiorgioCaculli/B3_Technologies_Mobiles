package be.helha.epreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.helha.epreference.domain.Bookmark;

public class CreateBookmarkActivity extends AppCompatActivity
{
    protected static final String KEY_BOOKMARK = "key_bookmark";
    private Button buttonCreateBookmark;
    private EditText etBookmarkTitle;
    private EditText etBookmarkURL;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_bookmark );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        buttonCreateBookmark = findViewById( R.id.btn_createBookmarkActivity_create );
        etBookmarkTitle = findViewById( R.id.et_createBookmarkActivity_title );
        etBookmarkURL = findViewById( R.id.et_createBookmarkActivity_url );
    }

    private void initListeners()
    {
        buttonCreateBookmark.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                String title = etBookmarkTitle.getText().toString();
                String titleFirstLetter = String.valueOf( title.charAt( 0 ) );
                String url = etBookmarkURL.getText().toString();
                Bookmark bookmark = new Bookmark( title, titleFirstLetter, url );
                Intent goToMainActivityIntent = new Intent();
                goToMainActivityIntent.putExtra( KEY_BOOKMARK, bookmark );
                setResult( RESULT_OK, goToMainActivityIntent );
                finish();
            }
        } );
    }
}
