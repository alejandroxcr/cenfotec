package com.fireblend.aplicacionbasica.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fireblend.aplicacionbasica.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtroActivity extends AppCompatActivity{

    private final String TAG = "ACTIVITY2";

    @BindView(R.id.boton_volver)
    Button boton;

    @BindView(R.id.texto)
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otro);
        ButterKnife.bind(this);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cierra este activity para volver a la ultima
                //activity en el stack
                finish();
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

        //Recuperamos el mensaje enviado en el intent
        //por medio de la llave LLAVE_TEXTO
        String mensaje = getIntent().getStringExtra(Constantes.LLAVE_TEXTO);

        //Y lo mostramos
        texto.setText(mensaje);
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
