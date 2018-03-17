package com.muybold.semana03.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.muybold.semana03.interfaces.IConstants;

/**
 * Created by Usuario on 3/16/2018.
 */

public class PreferenceManager {

    private static PreferenceManager _instance;
    private static SharedPreferences _preferences;


    private PreferenceManager(String preferencesFile, Context ctx){

        _preferences = ctx.getSharedPreferences(preferencesFile, ctx.MODE_PRIVATE);

    } // constructor-privado


    /**
     * Obtiene una instancia de esta clase
     * @param file
     * @param ctx
     * @return
     */
    public static PreferenceManager instance(String file, Context ctx){

        if(_instance == null) _instance = new PreferenceManager(file, ctx);

        return _instance;
    }// instance


    /**
     * Almacena preferencias de usuario y check 'remember'
     * @param userName
     * @param remember
     */
    public void saveUserName(String userName, boolean remember){

        SharedPreferences.Editor editor = _preferences.edit();

        editor.putString(IConstants.REMEMBER_USERNAME, userName);
        editor.putBoolean(IConstants.REMEMBER_CHECK, remember);

        editor.commit();

    } //saveUserName

    /**
     * Devuelve usuario almacenado en SharedPreferences
     * @return
     */
    public String getUserName(){
        return _preferences.getString(IConstants.REMEMBER_USERNAME, "");
    } // rememberUserName

    /**
     * Devuelve valor del check 'remember'
     * @return
     */
    public boolean getRemember(){
        return _preferences.getBoolean(IConstants.REMEMBER_CHECK, false);
    }// getRemember


} // PreferenceManager
