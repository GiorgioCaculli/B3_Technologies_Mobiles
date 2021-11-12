package be.helha.todo.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import be.helha.todo.dto.DtoTodo;

public class ListDtoTodoAdapter extends ArrayAdapter< DtoTodo >
{
    public ListDtoTodoAdapter( @NonNull Context context, @NonNull List< DtoTodo > objects )
    {
        super( context, 0, objects );
    }
}
