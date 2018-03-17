package com.muybold.semana03;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.muybold.semana03.interfaces.IConstants;
import com.muybold.semana03.helpers.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_username)
    EditText userName;

    @BindView(R.id.input_check_remember)
    CheckBox rememberCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initApp();

    }

    @Override
    protected void onStop(){
        super.onStop();

        PreferenceManager preferences = PreferenceManager.instance(IConstants.APP_PREFS, this);

        if(rememberCheck.isChecked()){
            preferences.saveUserName(userName.getText().toString(), rememberCheck.isChecked());

        }

    }

    /**
     * Inicializa la aplicacion
     */
    private void initApp(){
        PreferenceManager prefManager = PreferenceManager.instance(IConstants.APP_PREFS,this);

        // remember check
        rememberCheck.setChecked(prefManager.getRemember());
        // remember username
        userName.setText(prefManager.getUserName());

    } // initApp



}
