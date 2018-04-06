package com.muybold.semana04.services;

import com.muybold.semana04.classes.People;
import com.muybold.semana04.classes.TheForceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Estudiantes on 17/03/2018.
 */

public class StarWarsServiceHandler {

    private static StarWarsService _service;


    /**
     * StarWars service
     */
    public interface StarWarsService {

        @GET("people")
        Call<TheForceResponse<People>> getPeople();


    } // StarWarsApi


    /**
     * Retorna instancia del servicio Retrofit
     * @return
     */
    public static StarWarsService getService(){

        if(_service == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://swapi.co/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            _service = retrofit.create(StarWarsService.class);
        }

        return _service;
    }


}
