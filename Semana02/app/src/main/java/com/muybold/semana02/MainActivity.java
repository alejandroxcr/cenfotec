package com.muybold.semana02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Para la tarea agregar checkbox de genero
 * */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nombreUsuarioInput) EditText nombreUsuario;
    @BindView(R.id.emailUserInput) EditText emailUsuario;
    @BindView(R.id.edadUserInput) SeekBar edadUsuario;
    @BindView(R.id.userEdadVisor) TextView usuarioEdadVisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Inti Ui events
        initUiEvents();
    }

    @OnClick(R.id.button_enviar)
    public void onSendForm(){

        if(nombreUsuario.getText().toString().matches("")
                || emailUsuario.getText().toString().matches("")
                || usuarioEdadVisor.getText().toString().matches("0")){
            Toast.makeText(MainActivity.this,R.string.app_error_missing_data, Toast.LENGTH_SHORT).show();
        } else {

            openNextStage(); //Llama activity dashboard
        }

    }

    private void openNextStage(){

        // 1. Crear intent
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

        // 2. Agregar datos para el nuevo activity
        intent.putExtra("nombre", nombreUsuario.getText().toString());
        intent.putExtra("correo", emailUsuario.getText().toString());
        intent.putExtra("edad", usuarioEdadVisor.getText().toString());

        // 3. Iniciar nuevo activity
        startActivity(intent);

    }


    /**
     *  Inicializa eventos de Ui
      */
    private void initUiEvents(){

        // 1. Seekbar edad usuario
        edadUsuario.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                usuarioEdadVisor.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // FIN: Seekbar event ////////////////////////////////////////////////////////////////////

    }

}
