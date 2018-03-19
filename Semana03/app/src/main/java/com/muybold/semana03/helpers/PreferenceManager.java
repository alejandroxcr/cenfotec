package com.muybold.semana03.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.muybold.semana03.interfaces.IConstants;

/**
 * Created by Usuario on 3/16/2018.
 */

public class PreferenceManager {


    /**
     * Almacena preferncia username en preferencias
     * @param context
     * @param username
     * @return
     */
    public static boolean savePreferenceUsername(Context context, String username, boolean remember){
        boolean result = false;

        try{

            SharedPreferences sharedPreferences = context.getSharedPreferences(IConstants.APP_MAIN_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(IConstants.APP_PREFERENCE_USERNAME, username);
            editor.putBoolean(IConstants.APP_PREFERENCE_REMEMBER, remember);

            editor.commit();

            result = true;

        }catch (Exception ex){
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Obtiene el valor de la preferencia APP_PREFERENCE_REMEMBER
     * @param context
     * @return
     */
    public static boolean getRememberPreference(Context context){

        boolean remember = false;
        boolean defaultValue = false;

        try{

            SharedPreferences sharedPreferences = context.getSharedPreferences(IConstants.APP_MAIN_PREFERENCES, Context.MODE_PRIVATE);

            remember = sharedPreferences.getBoolean(IConstants.APP_PREFERENCE_REMEMBER, defaultValue);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return remember;
    }

    /**
     * Devuelve el valor de la preferencia userName
     * @param context
     * @return
     */
    public static String getUsernamePreference(Context context){
        String userName = "";
        String defaultValue = "";
        try{

            SharedPreferences sharedPreferences = context.getSharedPreferences(IConstants.APP_MAIN_PREFERENCES, Context.MODE_PRIVATE);

            userName = sharedPreferences.getString(IConstants.APP_PREFERENCE_USERNAME, defaultValue);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userName;
    }

    /**
     * Elimina preferencia por nombre, del archivo de preferencias de la aplicacion.
     * @param context
     * @return
     */
    public static boolean removePreferences(Context context, String [] preferences){
        boolean result = false;

        try{

            SharedPreferences sharedPreferences = context.getSharedPreferences(IConstants.APP_MAIN_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            for (String preference : preferences ) {

                editor.remove(preference);
            }

            editor.commit();

            result = true;

        }catch (Exception ex){
            result = false;
            ex.printStackTrace();
        }

        return result;
    }


} // PreferenceManager
