package com.muybold.semana02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.user_name) TextView userName;
    @BindView(R.id.user_correo) TextView userCorreo;
    @BindView(R.id.user_edad) TextView userEdad;
    @BindView(R.id.user_genero) TextView userGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        getUserDate();

    }

    /**
     * Get user data from MainActivity
     */
    private void getUserDate(){

        try{

            userName.setText(getIntent().getStringExtra("nombre"));
            userCorreo.setText(getIntent().getStringExtra("correo"));
            userEdad.setText(getIntent().getStringExtra("edad"));
            userGenero.setText(getIntent().getStringExtra("genero"));

        }catch (Exception ex){
            Toast.makeText(DashboardActivity.this,R.string.app_exception + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    } // getUserDate

    /**
     * Evento clic boton volver
     */
    @OnClick(R.id.volver_btn)
    public void onVolverClick(){

        try{

            super.onBackPressed();

        }catch (Exception ex){
            Toast.makeText(DashboardActivity.this,R.string.app_exception + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
