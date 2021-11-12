package be.helha.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import be.helha.todo.dto.DtoCreateTodo;
import be.helha.todo.dto.DtoTodo;
import be.helha.todo.infrastructure.ITodoRepository;
import be.helha.todo.infrastructure.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ITodoRepository todoRepository = Retrofit.getInstance().create( ITodoRepository.class );

        todoRepository.getAll().enqueue( new Callback< List< DtoTodo > >()
        {
            @Override
            public void onResponse( Call< List< DtoTodo > > call, Response< List< DtoTodo > > response )
            {
                Log.i( "TodoLog", response.body().toString() );
            }

            @Override
            public void onFailure( Call< List< DtoTodo > > call, Throwable t )
            {
                Log.e( "Error: ", t.toString() );
            }
        } );

        /*todoRepository.create( new DtoCreateTodo( "Vive les smourbifs" ) ).enqueue( new Callback< DtoTodo >()
        {
            @Override
            public void onResponse( Call< DtoTodo > call, Response< DtoTodo > response )
            {
                Log.i( "TodoLog", response.body().toString() );
            }

            @Override
            public void onFailure( Call< DtoTodo > call, Throwable t )
            {
                Log.e( "Error: ", t.toString() );
            }
        } );*/

        todoRepository.delete( 6 ).enqueue( new Callback< Void >()
        {
            @Override
            public void onResponse( Call< Void > call, Response< Void > response )
            {
                Log.i( "TodoLog", "Todo has been removed" );
            }

            @Override
            public void onFailure( Call< Void > call, Throwable t )
            {
                Log.e( "Error: ", t.toString() );
            }
        } );

        todoRepository.update( new DtoTodo( 9, "Reussir les projets", true ), 9 ).enqueue( new Callback< Void >()
        {
            @Override
            public void onResponse( Call< Void > call, Response< Void > response )
            {
                Log.i( "TodoLog", "Todo has been updated" );
            }

            @Override
            public void onFailure( Call< Void > call, Throwable t )
            {
                Log.e( "Error: ", t.toString() );
            }
        } );

        todoRepository.getById( 13 ).enqueue( new Callback< DtoTodo >()
        {
            @Override
            public void onResponse( Call< DtoTodo > call, Response< DtoTodo > response )
            {
                Log.i( "TodoLog", response.body().toString() );
            }

            @Override
            public void onFailure( Call< DtoTodo > call, Throwable t )
            {
                Log.e( "Error: ", t.toString() );
            }
        } );
    }
}
