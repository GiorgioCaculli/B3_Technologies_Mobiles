package be.helha.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.helha.todo.databinding.FragmentTodoListItemBinding;
import be.helha.todo.dto.DtoTodo;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DtoTodo}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TodoRecyclerViewAdapter extends RecyclerView.Adapter< TodoRecyclerViewAdapter.ViewHolder >
{
    private final List< DtoTodo > mValues;
    private TodoListFragment.OnTodoClickListener todoClickListener;

    public TodoRecyclerViewAdapter( List< DtoTodo > items, TodoListFragment.OnTodoClickListener todoClickListener )
    {
        mValues = items;
        this.todoClickListener = todoClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {

        return new ViewHolder( FragmentTodoListItemBinding.inflate( LayoutInflater.from( parent.getContext() ), parent, false ) );

    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, int position )
    {
        final DtoTodo dtoTodo = mValues.get( position );
        holder.mItem = dtoTodo;
        holder.tvId.setText( String.valueOf( dtoTodo.getId() ) );
        holder.tvTitle.setText( dtoTodo.getTitle() );
        holder.bind( todoClickListener );
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView tvId;
        public final TextView tvTitle;
        public DtoTodo mItem;

        public ViewHolder( FragmentTodoListItemBinding binding )
        {
            super( binding.getRoot() );
            tvId = binding.tvFragmentTodoListItemId;
            tvTitle = binding.tvFragmentTodoListItemTitle;
        }

        public void bind( TodoListFragment.OnTodoClickListener onTodoClickListener )
        {
            itemView.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View v )
                {
                    onTodoClickListener.onTodoClick( mItem );
                }
            } );
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + tvTitle.getText() + "'";
        }
    }
}
