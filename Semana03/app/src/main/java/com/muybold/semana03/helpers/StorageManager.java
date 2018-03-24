package com.muybold.semana03.helpers;

import android.content.Context;

import com.muybold.semana03.interfaces.IStorage;

/**
 * Created by Usuario on 3/18/2018.
 */

public class StorageManager {

    private IStorage _storage;


    public StorageManager(IStorage storage){
        _storage = storage;
    }

    /**
     * Escribe contenido en archivo de memoria interna o externa
     * @param context
     * @param fileName
     * @param content
     * @return
     */
    public boolean write(Context context, String fileName, String content, boolean appendToContent ){
        return _storage.write(context, fileName, content, appendToContent);
    }

    /**
     * Lee de almacenamiento interno o externo
     * @param context
     * @param fileName
     * @return
     */
    public String read(Context context, String fileName){
        return _storage.read(context, fileName);
    }

} // StorageManager
