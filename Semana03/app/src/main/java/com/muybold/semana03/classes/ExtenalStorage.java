package com.muybold.semana03.classes;

import android.content.Context;
import android.os.Environment;


import com.muybold.semana03.interfaces.IStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Usuario on 3/23/2018.
 */

public class ExtenalStorage implements IStorage {

    @Override
    public boolean write(Context context, String fileName, String content, boolean appendToContent) {
        boolean result = false;
        boolean createFile = true;
        String dirName = "app_ejemplo";

        try{
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);

            if(!directory.exists()) createFile = directory.mkdirs();

            if(createFile){

                File file = new File(directory, fileName);

                FileOutputStream fo = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(fo);

                pw.write(content);
                pw.close();
                fo.close();

                result = true;
            }

        }catch (Exception ex){
            result = false;
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public String read(Context context, String fileName) {
        String result = null;
        String filePath = "app_ejemplo/" + fileName;
        try {

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), filePath);

            if(file.exists()){
                FileInputStream is = new FileInputStream(file.getAbsolutePath());
                InputStreamReader reader = new InputStreamReader(is);

                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                result = sb.toString();
            }


        }catch (Exception ex){
            result = null;
            ex.printStackTrace();
        }

        return result;
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
