package com.muybold.semana05.interfaces;

import android.content.Context;

import java.io.File;

/**
 * Created by Estudiantes on 24/03/2018.
 */

public interface IStorage {

    /**
     * Escribe un archivo en almancenamiento interno o externo
     * @param fileName
     * @param content
     * @return
     */
    File write(Context context, String fileName, String content, boolean appendToContent);

    /**
     * Lee un archivo de almacenamiento interno o externo
     * @param fileName
     * @return
     */
    String read(Context context, String fileName);

}
