package be.helha.bdecrement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.helha.bdecrement.domain.IRandomDecrementNumber;
import static be.helha.bdecrement.domain.RandomDecrementNumber.createNew;

public class MainActivity extends AppCompatActivity
{
    private TextView tvNumber;
    private Button btnDecrement;
    private IRandomDecrementNumber randomDecrementNumber = createNew();

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        initViewInstances();
        initListeners();
        updateViewWithRandomNumber();
    }

    private void initViewInstances()
    {

        tvNumber = findViewById( R.id.tv_mainActivity_number );
        btnDecrement = findViewById( R.id.btn_mainActivity_decrement );
    }

    private void initListeners()
    {
        btnDecrement.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                updateAndRegenerate();
            }
        } );
        tvNumber.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                updateAndRegenerate();
            }
        } );
    }

    private void updateViewWithRandomNumber()
    {
        tvNumber.setText( randomDecrementNumber.getValue() + "" );
    }

    private void updateAndRegenerate()
    {
        randomDecrementNumber = randomDecrementNumber.decrement();
        updateViewWithRandomNumber();
    }
}
