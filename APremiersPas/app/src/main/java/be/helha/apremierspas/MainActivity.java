package be.helha.apremierspas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText etPseudo, etPassword, etEmail;
    private Button btnSubmit;

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
        etPseudo = findViewById( R.id.et_mainActivity_nickname );
        etPassword = findViewById( R.id.et_mainActivity_password );
        etEmail = findViewById( R.id.et_mainActivity_email );
        btnSubmit = findViewById( R.id.btn_mainActivity_submit );
    }

    private void initListeners()
    {
        btnSubmit.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Toast.makeText(
                        getApplicationContext(),
                        getStringOfInputs(),
                        Toast.LENGTH_LONG
                ).show();
            }
        } );
    }

    private String getStringOfInputs()
    {
        return String.format( "Nickname: %s\nEmail: %s\nPassword: %s",
                etPseudo.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString() );
    }
}
