package com.muybold.semana03.classes;


import android.content.Context;

import com.muybold.semana03.interfaces.IStorage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Usuario on 3/18/2018.
 */

public class InternalStorage implements IStorage {


    /**
     * Escribe en almacenamiento interno
      * @param context
     * @param fileName
     * @param content
     * @return
     */
    @Override
    public boolean write(Context context, String fileName, String content, boolean appendToContent)  {
        boolean result = false;

        FileOutputStream outputStream = null;

        try {

            // Append to content
            if(appendToContent) {
                String currentContent = read(context, fileName);

                if (!currentContent.isEmpty()) {

                    StringBuilder sb = new StringBuilder();
                    sb.append(currentContent + " " + content);
                    content = sb.toString();
                }
            }

            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE); // Append to file
            outputStream.write(content.getBytes());
            outputStream.close();

            result = true;

        }catch (Exception ex){
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Lee de almacenamiento interno
     * @param context
     * @param fileName
     * @return
     */
    @Override
    public String read(Context context, String fileName) {
        String result = null;

        try {

            FileInputStream inputStream = context.openFileInput(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

            result = sb.toString();

        }catch (Exception ex){
            result = null;
            ex.printStackTrace();
        }

        return result;
    }
} // InternalStorage
