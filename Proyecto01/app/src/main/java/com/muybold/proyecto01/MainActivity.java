package com.muybold.proyecto01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.enterButton) Button _enterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar butter knife
        ButterKnife.bind(this);

        // Inicializar eventos UI
        initializeUiEvents();
    }

    /***
     * Inicializa enventos de la Ui
     */
    private void initializeUiEvents(){
        _enterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startDashboard();
            }
        });
    }

    /**
     *  Muestra mensaje en la pantalla
      */
    private void sayHello(){
        Toast.makeText(MainActivity.this,
                R.string.str_hola,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Carga la pantalla de inicio
     */
    private void startDashboard(){

        // Crear intencion, explicita hacia Dashboard
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

        // Iniciar activity
        startActivity(intent);
    } // startDashboard
}
