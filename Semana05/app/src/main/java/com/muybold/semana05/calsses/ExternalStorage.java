package com.muybold.semana05.calsses;

import android.content.Context;
import android.os.Environment;

import com.muybold.semana05.interfaces.IConstants;
import com.muybold.semana05.interfaces.IStorage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Estudiantes on 24/03/2018.
 */

public class ExternalStorage implements IStorage {

    @Override
    public File write(Context context, String fileName, String content, boolean appendToContent) {

        File file = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String finalFileName = fileName + timeStamp;

        try{

            File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            file = File.createTempFile(
                    finalFileName,
                    IConstants.PHOTO_JPG_EXT,
                    storageDir );

        }catch (Exception ex){
            file = null;
            ex.printStackTrace();
        }

        return file;
    }

    @Override
    public String read(Context context, String fileName) {
        return null;
    }
}
