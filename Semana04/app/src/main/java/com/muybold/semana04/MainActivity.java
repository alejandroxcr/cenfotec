package com.muybold.semana04;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.muybold.semana04.classes.People;
import com.muybold.semana04.classes.TheForceResponse;
import com.muybold.semana04.services.StarWarsServiceHandler;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    private TheForceResponse<People> _people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Service loads on first load
        callService();
    }

    /**
     * Realiza llamada al servicio para traer
     * informacion del API de Star Wars
     */
    private void callService(){

        StarWarsServiceHandler.StarWarsService service = StarWarsServiceHandler.getService();

        Call<TheForceResponse<People>> call = service.getPeople();
        call.enqueue(new Callback<TheForceResponse<People>>() {
            @Override
            public void onResponse(Call<TheForceResponse<People>> call, Response<TheForceResponse<People>> response) {

                if(response != null)
                    _people = response.body();
                else
                    Toast.makeText(MainActivity.this, getText(R.string.app_emtpy_response), Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<TheForceResponse<People>> call, Throwable t) {

                Toast.makeText(MainActivity.this,getString(R.string.app_something_wrong), Toast.LENGTH_LONG);

            }
        });

    }


}
