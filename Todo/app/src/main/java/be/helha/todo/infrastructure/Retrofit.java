package be.helha.todo.infrastructure;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit
{
    /*private static final String BASE_URL = "http://10.0.2.2:5000/api/";*/ /* AVD */
    /*private static final String BASE_URL = "http://www.caculli.it:5000/api/";*/ /* CACULLI.IT */
    private static final String BASE_URL = "http://10.2.32.134:5000/api/"; /* HELHa */
    private static retrofit2.Retrofit instance;

    public static retrofit2.Retrofit getInstance()
    {
        if ( instance == null )
        {
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }
        return instance;
    }
}
