package com.fireblend.aplicacionbasica.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fireblend.aplicacionbasica.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //Constante para llamadas al Log.d
    private final String TAG = "ACTIVITY1";

    //Declaraciones de widgets del UI usando Butterknife
    @BindView(R.id.boton_cambiar)
    Button botonCambiar;

    @BindView(R.id.boton_llamar)
    Button botonLlamar;

    @BindView(R.id.input)
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate");

        super.onCreate(savedInstanceState);
        //Asignamos el layout que corresponde a este activity
        setContentView(R.layout.activity_main);

        //La llamada de inicializacion de ButterKnife
        ButterKnife.bind(this);

        //Se le asigna un callback al evento de onClick a botonCambiar
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar el mensaje escrito en input
                String mensaje = input.getText().toString();

                //Si el mensaje esta vacio...
                if(mensaje.trim().equals("")){
                    //Mostramos un error
                    Toast.makeText(MainActivity.this,
                            R.string.error_vacio,
                            Toast.LENGTH_SHORT).show();
                    //Y no hacemos nada
                    return;
                }
                //Crear un intent hacia el Activity OtroActivity
                Intent myIntent = new Intent(MainActivity.this,
                        OtroActivity.class);

                //Se envia el mensaje por medio de LLAVE_TEXTO
                myIntent.putExtra(Constantes.LLAVE_TEXTO, mensaje);

                //Se envia el intent
                startActivity(myIntent);
            }
        });

        botonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crear un intent para el action ACTION_DIAL
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //Se envian datos asociados a ese Intent
                intent.setData(Uri.parse("tel:88854764"));
                //Se envia el intent
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
    }

}
