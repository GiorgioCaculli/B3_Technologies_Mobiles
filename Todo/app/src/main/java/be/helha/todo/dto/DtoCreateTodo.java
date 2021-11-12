package be.helha.todo.dto;

public class DtoCreateTodo
{
    private String title;

    public DtoCreateTodo( String title )
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }
}
