package be.helha.cactivitesetintentions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button buttonCall, buttonSearchTheWeb;
    private static final int REQ_CODE_CALL_ACTIVITY = 1;
    private static final int REQ_CODE_SEARCH_THE_WEB_ACTIVITY = 2;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        buttonCall = findViewById( R.id.btn_MainActivity_call );
        buttonSearchTheWeb = findViewById( R.id.btn_MainActivity_search_the_web );
    }

    private void initListeners()
    {
        buttonCall.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent goToCallActivity = new Intent( MainActivity.this, CallActivity.class );
                startActivityForResult( goToCallActivity, REQ_CODE_CALL_ACTIVITY );
            }
        } );
        buttonSearchTheWeb.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent goToSearchTheWebActivity = new Intent( MainActivity.this, SearchTheWebActivity.class );
                startActivityForResult( goToSearchTheWebActivity, REQ_CODE_SEARCH_THE_WEB_ACTIVITY );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data )
    {
        if( resultCode != RESULT_OK )
        {
            return;
        }

        Intent intent = new Intent();

        switch ( requestCode )
        {
            case REQ_CODE_CALL_ACTIVITY:
                String phoneNumber = data.getStringExtra( CallActivity.PHONE_NUMBER );
                intent.setAction( Intent.ACTION_DIAL );
                intent.setData( Uri.parse( "tel:" + phoneNumber ) );
                break;
            case REQ_CODE_SEARCH_THE_WEB_ACTIVITY:
                String url = data.getStringExtra( SearchTheWebActivity.URL_SITE );
                intent.setAction( Intent.ACTION_VIEW );
                intent.setData( Uri.parse( url ) );
                break;
            default:
                break;
        }
        startActivity( intent );
        super.onActivityResult( requestCode, resultCode, data );
    }
}
