package com.muybold.semana05.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Estudiantes on 24/03/2018.
 */

public class PermissionsManager {

    public static final int WRITE_EXTERNAL_CODE = 100;


    /**
     * Revisar si tiene permiso de escritura
     * @param ctx
     * @return
     */
    public static boolean hasWriteExternalWritePermission(Context ctx){
        return ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Solicita permisos por Activity y codigo
     * @param activity
     * @param permissionCode
     * @param requestedPermissions
     */
    public static void askForPermissions(Activity activity, int permissionCode, String [] requestedPermissions){
        ActivityCompat.requestPermissions(activity,requestedPermissions, permissionCode);
    } // askForPermissions


}
