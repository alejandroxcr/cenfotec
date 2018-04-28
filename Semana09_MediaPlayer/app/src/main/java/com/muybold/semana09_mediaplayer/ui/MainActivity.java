package com.muybold.semana09_mediaplayer.ui;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.muybold.semana09_mediaplayer.R;
import com.muybold.semana09_mediaplayer.services.StreamingService;

import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.play_button)
    public void play(View view){

        Toast.makeText(this, "Starting service", Toast.LENGTH_SHORT).show();
        startService(new Intent(this, StreamingService.class));

    }

}
