package com.muybold.semana03.classes;

import android.content.Context;
import android.os.Environment;


import com.muybold.semana03.interfaces.IStorage;

import java.io.File;

/**
 * Created by Usuario on 3/23/2018.
 */

public class ExtenalStorage implements IStorage {

    @Override
    public boolean write(Context context, String fileName, String content, boolean appendToContent) {
        boolean result = false;

        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),fileName);
            
        }catch (Exception ex){
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public String read(Context context, String fileName) {
        return null;
    }

    /**
     * Verifica si el almacenamiento externo esta listo para
     * escribir
     * @return
     */
    private boolean isReadyToWrite(){
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
