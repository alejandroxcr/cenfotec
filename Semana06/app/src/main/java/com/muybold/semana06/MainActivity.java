package com.muybold.semana06;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.muybold.semana06.fragments.ChaoFragment;
import com.muybold.semana06.fragments.HolaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HolaFragment.IHolaFragment, ChaoFragment.IChaoFragment {

    @BindView(R.id.hola_btn)
    TextView _holaBtn;

    @BindView(R.id.chao_btn)
    TextView _chaoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
    }

    /**
     * Inicializar Ui
     */
    private void initUi(){

        // 1. Cargar hola fragment al inicio
        loadHolaFragment();

    } //initUi

    @OnClick(R.id.hola_btn)
    public void OnHolaClick(TextView view){
        loadHolaFragment();
    } // OnHolaClick

    @OnClick(R.id.chao_btn)
    public void OnChaoClick(TextView view){
        loadChaoFragment();
    } // OnChaoClick

    /**
     * loadHolaFragment
     * Carga el fragment hola
     */
    private void loadHolaFragment(){
        try{

            HolaFragment fragment = new HolaFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);

            transaction.commit();

        }catch (Exception ex){
            Toast.makeText(this,R.string.app_error_loading_fragment, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * loadHolaFragment
     * Carga el fragment chao
     */
    private void loadChaoFragment(){
        try{

            ChaoFragment fragment = new ChaoFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);

            transaction.commit();
        }catch (Exception ex){
            Toast.makeText(this,R.string.app_error_loading_fragment, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onHolaBtnClick() {
        /// TODO: funcionalidad click boton en hola
    }

    @Override
    public void onChaoBtnClick() {
        /// TODO: funcionalidad click button en chao
    }
}
