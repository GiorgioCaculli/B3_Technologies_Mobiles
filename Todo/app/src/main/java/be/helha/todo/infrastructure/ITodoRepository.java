package be.helha.todo.infrastructure;

import java.util.List;

import be.helha.todo.dto.DtoCreateTodo;
import be.helha.todo.dto.DtoTodo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITodoRepository
{
    @GET( "todo" )
    Call< List< DtoTodo > > getAll();

    @GET( "todo/{id}" )
    Call< DtoTodo > getById( @Path( "id" ) int id );

    @POST( "todo" )
    Call< DtoTodo > create( @Body DtoCreateTodo dtoCreateTodo );

    @DELETE( "todo/{id}" )
    Call< Void > delete( @Path( "id" ) int id );

    @PUT( "todo/{id}" )
    Call< Void > update( @Body DtoTodo dto, @Path( "id" ) int id );
}
