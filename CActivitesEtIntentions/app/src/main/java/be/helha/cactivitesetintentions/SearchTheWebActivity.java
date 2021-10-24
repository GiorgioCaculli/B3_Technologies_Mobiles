package be.helha.cactivitesetintentions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchTheWebActivity extends AppCompatActivity
{
    private Button buttonSubmit;
    private EditText editTextURL;
    public static final String URL_SITE = "https://www.google.com";

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_the_web );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        buttonSubmit = findViewById( R.id.btn_searchTheWebActivity_submit );
        editTextURL = findViewById( R.id.et_searchTheWebActivity_url );
    }

    private void initListeners()
    {
        buttonSubmit.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                String value = editTextURL.getText().toString();
                Intent goToMainActivity = new Intent();
                goToMainActivity.putExtra( URL_SITE, value );
                setResult( RESULT_OK, goToMainActivity );
                finish();
            }
        } );
        editTextURL.setText( "https://" );
        Selection.setSelection( editTextURL.getText(), editTextURL.getText().length() );
        editTextURL.addTextChangedListener( new TextWatcher()
        {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after )
            {
            }

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count )
            {
            }

            @Override
            public void afterTextChanged( Editable s )
            {
                if( !s.toString().startsWith( "https://" ) )
                {
                    editTextURL.setText( "https://" );
                    Selection.setSelection( editTextURL.getText(), editTextURL.getText().length() );
                }
            }
        } );
    }
}
