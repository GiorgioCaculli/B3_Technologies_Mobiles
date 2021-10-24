package be.helha.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );
        Intent goToMainActivity = new Intent( this, MainActivity.class );
        startActivity( goToMainActivity );
        finish();
    }
}
