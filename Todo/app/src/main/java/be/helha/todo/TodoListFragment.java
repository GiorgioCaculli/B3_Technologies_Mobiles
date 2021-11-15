package be.helha.todo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.helha.todo.dto.DtoTodo;
import be.helha.todo.infrastructure.ITodoRepository;
import be.helha.todo.infrastructure.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class TodoListFragment extends Fragment
{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final List< DtoTodo > todoList = new ArrayList<>();
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private TodoRecyclerViewAdapter todoRecyclerViewAdapter;

    public interface OnTodoClickListener
    {
        void onTodoClick( DtoTodo dtoTodo );
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodoListFragment()
    {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings( "unused" )
    public static TodoListFragment newInstance( int columnCount )
    {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        args.putInt( ARG_COLUMN_COUNT, columnCount );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        if ( getArguments() != null )
        {
            mColumnCount = getArguments().getInt( ARG_COLUMN_COUNT );
        }
        try
        {
            OnTodoClickListener clickListener = ( OnTodoClickListener ) getActivity();
            todoRecyclerViewAdapter = new TodoRecyclerViewAdapter( todoList, clickListener );
            fetchTodoList();
        }
        catch ( ClassCastException cce )
        {
            Log.e( "FragmentCastException", "The activity must implement the interface OnTodoClickListener" );
        }
    }

    private void fetchTodoList()
    {
        Retrofit.getInstance().create( ITodoRepository.class ).getAll().enqueue( new Callback< List< DtoTodo > >()
        {
            @Override
            public void onResponse( Call< List< DtoTodo > > call, Response< List< DtoTodo > > response )
            {
                if ( response.code() == 200 )
                {
                    todoList.addAll( response.body() );
                    todoRecyclerViewAdapter.notifyItemChanged( 0 );
                }
            }

            @Override
            public void onFailure( Call< List< DtoTodo > > call, Throwable t )
            {
                Toast.makeText( getContext(), t.toString(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_todo_list, container, false );

        // Set the adapter
        if ( view instanceof RecyclerView )
        {
            Context context = view.getContext();
            RecyclerView recyclerView = ( RecyclerView ) view;
            if ( mColumnCount <= 1 )
            {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else
            {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            recyclerView.setAdapter( todoRecyclerViewAdapter );
        }
        return view;
    }
}
