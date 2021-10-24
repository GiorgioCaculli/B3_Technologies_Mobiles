package be.helha.playground;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final int REQ_CODE_SECOND_ACTIVITY = 1;
    private Button btnClickMe, btnGoToSecondActivity, btnOpenHELHa;
    private EditText etPseudo;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.i( "info log", "MainActivity init" );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        btnClickMe = findViewById( R.id.btn_mainActivity_clickMe );
        btnOpenHELHa = findViewById( R.id.btn_mainActivity_openHELHa );
        btnGoToSecondActivity = findViewById( R.id.btn_mainActivity_goToActivity );
        etPseudo = findViewById( R.id.et_mainActivity_pseudo );
    }

    private void initListeners()
    {

        btnClickMe.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                Toast.makeText( getApplicationContext(), etPseudo.getText().toString(), Toast.LENGTH_LONG ).show();
            }
        } );
        btnGoToSecondActivity.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent goToSecondActivity = new Intent( MainActivity.this, SecondActivity.class );
                startActivityForResult( goToSecondActivity, REQ_CODE_SECOND_ACTIVITY );
            }
        } );
        btnOpenHELHa.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent openHELHaIntent = new Intent( Intent.ACTION_VIEW );
                openHELHaIntent.setData( Uri.parse( "https://www.helha.be" ) );
                startActivity( openHELHaIntent );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );

        if( requestCode == REQ_CODE_SECOND_ACTIVITY && resultCode == RESULT_OK )
        {
            String name = data.getStringExtra( SecondActivity.KEY_NAME );
            Toast.makeText( getApplicationContext(), name, Toast.LENGTH_LONG ).show();
        }
    }
}
