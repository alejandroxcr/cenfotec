package com.muybold.semana04;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.muybold.semana04.adapters.PeopleAdapter;
import com.muybold.semana04.classes.People;
import com.muybold.semana04.classes.TheForceResponse;
import com.muybold.semana04.services.StarWarsServiceHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.people_list)
    ListView _peopleList;

    @BindView(R.id.progress_indicator)
    ProgressBar _progressIndicator;


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

        _progressIndicator.setIndeterminate(true);
        _progressIndicator.setProgress(1);

        Call<TheForceResponse<People>> call = service.getPeople();
        call.enqueue(new Callback<TheForceResponse<People>>() {
            @Override
            public void onResponse(Call<TheForceResponse<People>> call, Response<TheForceResponse<People>> response) {

                try {
                    if (response != null) {

                        _people = response.body();
                        populateList();

                    } else {

                        Toast.makeText(MainActivity.this, getText(R.string.app_emtpy_response), Toast.LENGTH_LONG);

                    }
                }catch (Exception ex){

                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG);

                }
            }

            @Override
            public void onFailure(Call<TheForceResponse<People>> call, Throwable t) {

                Toast.makeText(MainActivity.this,getString(R.string.app_something_wrong), Toast.LENGTH_LONG);

            }
        });

    }

    /**
     * Llena list view con información
     * del Api de Star Wars
     */
    private void populateList(){

        try {

            _progressIndicator.setProgress(100);
            _progressIndicator.setVisibility(View.GONE);
            _peopleList.setVisibility(View.VISIBLE);

            PeopleAdapter adapter = new PeopleAdapter(this, _people.results);
            _peopleList.setAdapter(adapter);

        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG);
            finish();
        }
    }


}
