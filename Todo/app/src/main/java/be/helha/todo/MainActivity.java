package be.helha.todo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import be.helha.todo.dto.DtoTodo;

public class MainActivity extends AppCompatActivity implements TodoListFragment.OnTodoClickListener
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

    }

    @Override
    public void onTodoClick( DtoTodo dtoTodo )
    {
        Log.i( "TodoLog", dtoTodo.toString() );
        Toast.makeText( getApplicationContext(), dtoTodo.toString(), Toast.LENGTH_LONG ).show();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack( "list" )
                .replace(
                        R.id.fragment_mainActivity_container,
                        TodoDetailFragment.newInstance( dtoTodo.getId() ),
                        "todoDetail"
                )
                .commit();
    }
}
