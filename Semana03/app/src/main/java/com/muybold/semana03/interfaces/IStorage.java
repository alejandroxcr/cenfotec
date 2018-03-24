package com.muybold.semana03.interfaces;

import android.content.Context;

/**
 * Created by Usuario on 3/18/2018.
 */

public interface IStorage {

    /**
     * Escribe un archivo en almancenamiento interno o externo
     * @param fileName
     * @param content
     * @return
     */
    boolean write(Context context, String fileName, String content, boolean appendToContent);

    /**
     * Lee un archivo de almacenamiento interno o externo
     * @param fileName
     * @return
     */
    String read(Context context, String fileName);

} // IStorage
