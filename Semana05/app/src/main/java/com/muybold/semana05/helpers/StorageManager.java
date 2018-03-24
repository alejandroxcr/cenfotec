package com.muybold.semana05.helpers;

import android.content.Context;

import com.muybold.semana05.interfaces.IStorage;

import java.io.File;

/**
 * Created by Estudiantes on 24/03/2018.
 */

public class StorageManager {

    private IStorage _storage;

    public StorageManager(IStorage storage){
        _storage = storage;
    }


    /**
     * Escribe en almacenamiento externo
     * @param ctx
     * @param fileName
     * @param content
     * @param appendToEnd
     * @return
     */
    public File write(Context ctx, String fileName, String content, boolean appendToEnd){
        return _storage.write(ctx, fileName, content, appendToEnd);
    }

}
