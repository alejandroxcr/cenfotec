package com.muybold.proyecto01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.callMariaButton)
    Button _callMaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        initializeUiEvents();
    }

    private void initializeUiEvents(){
        _callMaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallMaria();
            }
        });
    }


    private void CallMaria(){
        // Crear intent para llamar
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // El intent espera datos
        intent.setData(Uri.parse("tel:88531923"));

        startActivity(intent);
    }
}
