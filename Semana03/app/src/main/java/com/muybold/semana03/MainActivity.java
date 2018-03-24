package com.muybold.semana03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


import com.muybold.semana03.helpers.PreferenceManager;
import com.muybold.semana03.interfaces.IConstants;

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

    } // onCreate

    /**
     * ButterKnife event. Registra cuando el check
     * de remember cambia
     * @param isChecked
     */
    @OnCheckedChanged(R.id.input_check_remember)
    public void onRememberCheckChange(boolean isChecked){

        if(isChecked){

            String userNameStr = userName.getText().toString().trim();
            PreferenceManager.savePreferenceUsername(MainActivity.this, userNameStr, isChecked);

        } else {

            String [] preferences = { IConstants.APP_PREFERENCE_REMEMBER, IConstants.APP_PREFERENCE_USERNAME };
            PreferenceManager.removePreferences(MainActivity.this, preferences );
        }

    } // onRememberCheckChange

    @OnClick(R.id.enter_btn)
    public void onEnterClicked(){

        Intent intent = new Intent(MainActivity.this, StorageActivity.class);

        intent.putExtra(IConstants.APP_USERNAME, userName.getText().toString());

        startActivity(intent);
    }


    @Override
    protected void onStop(){
        super.onStop();

        if(rememberCheck.isChecked()){
            String userNameStr = userName.getText().toString().trim();
            PreferenceManager.savePreferenceUsername(this, userNameStr, true);

        } else {

            String [] preferences = { IConstants.APP_PREFERENCE_USERNAME, IConstants.APP_PREFERENCE_REMEMBER };
            PreferenceManager.removePreferences(this, preferences);
        }
    }

    /**
     * Inicializa la aplicacion
     */
    private void initApp(){
        try{

            // Inicializar campo userName
            userName.setText(PreferenceManager.getUsernamePreference(MainActivity.this));

            // Inicializar checkbox remember
            rememberCheck.setChecked(PreferenceManager.getRememberPreference(MainActivity.this));

        } catch (Exception ex){
            ex.printStackTrace();
        }

    } // initApp



}
