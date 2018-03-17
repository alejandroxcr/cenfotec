package com.muybold.semana04.services;

import com.muybold.semana04.classes.People;
import com.muybold.semana04.classes.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Estudiantes on 17/03/2018.
 */

public class StarWarsService {

    private static IStarWarsApi starWarsApi;
    private static IJsonService jsonService;

    public interface IJsonService {

        @GET("posts")
        Call<List<Post>> getPosts();

    }

    public interface IStarWarsApi {

        @GET("people")
        Call<List<People>> getPeople();

        @GET("people/{id}")
        Call<People> getPeopleById(@Path("id") int peopleId);

    }

    public static IJsonService getJsonApi(){
        if(jsonService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                jsonService = retrofit.create(IJsonService.class);
        }

        return jsonService;
    }

    public static IStarWarsApi getStarWarsApi(){

        if(starWarsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://swapi.co/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            starWarsApi = retrofit.create(IStarWarsApi.class);
        }

        return starWarsApi;
    }

}
