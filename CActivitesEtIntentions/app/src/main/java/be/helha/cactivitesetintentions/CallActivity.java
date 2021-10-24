package be.helha.cactivitesetintentions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallActivity extends AppCompatActivity
{
    private Button buttonCall;
    private EditText editTextPhoneNumber;
    public static final String PHONE_NUMBER = "00000000";
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_call );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        buttonCall = findViewById( R.id.btn_CallActivity_submitCall );
        editTextPhoneNumber = findViewById( R.id.et_CallActivity_phone_number );
    }

    private void initListeners()
    {
        buttonCall.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                final String value = editTextPhoneNumber.getText().toString();
                Intent goToMainActivity = new Intent();
                goToMainActivity.putExtra( PHONE_NUMBER, value );
                setResult( RESULT_OK, goToMainActivity );
                finish();
            }
        } );
    }
}
