package be.helha.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import be.helha.todo.dto.DtoTodo;
import be.helha.todo.infrastructure.ITodoRepository;
import be.helha.todo.infrastructure.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoDetailFragment extends Fragment
{

    private static final String ARG_TODO_ID = "todoId";

    private TextView tvTodoTitle;

    private int todoId;
    private DtoTodo dtoTodo;

    public TodoDetailFragment()
    {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoDetailFragment.
     */
    public static TodoDetailFragment newInstance( int todoId )
    {
        TodoDetailFragment fragment = new TodoDetailFragment();
        Bundle args = new Bundle();
        args.putInt( ARG_TODO_ID, todoId );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        if ( getArguments() != null )
        {
            todoId = getArguments().getInt( ARG_TODO_ID );
            fetchTodo();
        }
    }

    private void fetchTodo()
    {
        Retrofit.getInstance().create( ITodoRepository.class ).getById( todoId )
                .enqueue( new Callback< DtoTodo >()
                {
                    @Override
                    public void onResponse( Call< DtoTodo > call, Response< DtoTodo > response )
                    {
                        if( response.code() == 200 )
                        {
                            dtoTodo = response.body();
                            updateView();
                        }
                    }

                    @Override
                    public void onFailure( Call< DtoTodo > call, Throwable t )
                    {
                        Log.e( "Error: ", t.toString() );
                        Toast.makeText( getContext(), t.toString(), Toast.LENGTH_LONG ).show();
                    }
                });
    }

    private void updateView()
    {
        tvTodoTitle.setText( dtoTodo.getTitle() );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_todo_detail, container, false );
        tvTodoTitle = view.findViewById( R.id.tv_FragmentTodoDetail_title );
        return view;
    }
}
