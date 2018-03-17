package com.muybold.semana04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muybold.semana04.classes.People;
import com.muybold.semana04.classes.Post;
import com.muybold.semana04.services.StarWarsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callTheForce();
       // callJsonService();
    }

    private void callJsonService(){
        StarWarsService.IJsonService service = StarWarsService.getJsonApi();

        service.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void callTheForce(){

        // Get the api
        StarWarsService.IStarWarsApi api = StarWarsService.getStarWarsApi();

        api.getPeople().enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {

                List<People> resultList = response.body();
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
               t.printStackTrace();
            }
        });

    }
}
