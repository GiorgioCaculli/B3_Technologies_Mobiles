package be.helha.playgroundlists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.helha.playgroundlists.domain.Movie;

public class DetailMovieActivity extends AppCompatActivity
{
    private TextView tvTitle;
    private Button buttonReturn;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail_movie );

        tvTitle = findViewById( R.id.tv_detailMovieActivity_title );
        buttonReturn = findViewById( R.id.btn_detailMovieActivity_back );

        Movie movie = getIntent().getParcelableExtra( MainActivity.KEY_MOVIE );

        tvTitle.setText( movie.getTitle() );

        buttonReturn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent( DetailMovieActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        } );
    }
}
