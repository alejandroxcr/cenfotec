package com.muybold.semana03;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.muybold.semana03.com.muybold.semana03.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_username)
    EditText userName;

    @BindView(R.id.input_check_remember)
    CheckBox rememberCheck;

    private SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getPrefrences();
    }

    private void getPrefrences(){

        appPreferences = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        rememberCheck.setChecked(appPreferences.getBoolean(Constants.REMEMBER_CHECK,false));
        userName.setText(appPreferences.getString(Constants.REMEMBER_USERNAME,""));
    }

    @OnCheckedChanged(R.id.input_check_remember)
    public void rememberCheck(boolean checked){
        rememberMe(checked);
    } // rememberCheck

    private void rememberMe(boolean remember){
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putBoolean(Constants.REMEMBER_CHECK,remember);

        if(remember){
            editor.putString(Constants.REMEMBER_USERNAME, userName.getText().toString());
        } else{
            editor.remove(Constants.REMEMBER_USERNAME);
        }

        editor.commit();
    }

}
