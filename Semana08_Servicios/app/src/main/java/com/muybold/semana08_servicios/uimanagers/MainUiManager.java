package com.muybold.semana08_servicios.uimanagers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Estudiantes on 21/04/2018.
 */

public class MainUiManager {

    private static MainUiManager _instance;
    private Context _context;

    private MainUiManager(Context ctx){
        _context = ctx;
    }

    /**
     * Devuelve instancia de la clase.
     * @param context
     * @return
     */
    public static MainUiManager getInstance(Context context){
        if(_instance == null) _instance = new MainUiManager(context);
        return _instance;
    } // getInstance

    /**
     * Administra eventos en la interfaz
     */
    public void onHelloClick(){
        printSimpleToast("Button clicked!", Toast.LENGTH_LONG);
    }

    /**
     * Imprime un Toast en la pantalla
     * @param message
     */
    private void printSimpleToast(String message, int duration){
        if(_context == null) return;
        Toast.makeText(_context,message,duration).show();
    } // printSimpleToast

} // MainUiManager
