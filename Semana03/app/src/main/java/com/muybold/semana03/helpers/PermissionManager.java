package com.muybold.semana03.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Usuario on 3/23/2018.
 */

public class PermissionManager {


    public static final int PERMISSION_WRITE_EXTERNAL_CODE = 100;

    /**
     * Check for write external permission
     * @param ctx
     * @return
     */
    public static boolean hasWriteExternalWritePermission(Context ctx){
        return (ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED);
    }


    /**
     * Request app permissions
     * @param activity
     * @param permissionCode
     * @param requestedPermissions
     */
    public static void askPermission(Activity activity, int permissionCode, String[] requestedPermissions){
        ActivityCompat.requestPermissions(activity, requestedPermissions, permissionCode);
    }

} // PermissionManager
