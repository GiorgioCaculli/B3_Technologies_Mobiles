package be.helha.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        SharedPreferences preferences = getSharedPreferences( "playground", MODE_PRIVATE );

        SharedPreferences.Editor editor = preferences.edit();

        String pseudo = preferences.getString( "pseudo", null );

        if( pseudo == null )
        {
            editor.putString( "pseudo", "Warrior3000" );
            editor.apply();
        }
    }
}
