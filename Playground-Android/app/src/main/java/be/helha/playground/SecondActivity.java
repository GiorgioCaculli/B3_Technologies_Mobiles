package be.helha.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity
{
    public static final String KEY_NAME = "name";
    private EditText etName;
    private Button btnSubmit;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        initViewInstances();
        initListeners();
    }

    private void initViewInstances()
    {
        etName = findViewById( R.id.et_secondActivity_name );
        btnSubmit = findViewById( R.id.btn_secondActivity_submit );
    }

    private void initListeners()
    {
        btnSubmit.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                String value = etName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra( KEY_NAME, value );
                setResult( RESULT_OK, intent );
                finish();
            }
        } );
    }
}
