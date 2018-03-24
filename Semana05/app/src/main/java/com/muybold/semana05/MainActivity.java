package com.muybold.semana05;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.muybold.semana05.calsses.ExternalStorage;
import com.muybold.semana05.helpers.PermissionsManager;
import com.muybold.semana05.helpers.StorageManager;
import com.muybold.semana05.interfaces.IConstants;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.user_photo)
    ImageView userPhoto;

    private Uri _photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }


    /**
     * Inicializa la aplicacion
     */
    private void init(){

        // 1. Verificar permisos de escritura
        if(PermissionsManager.hasWriteExternalWritePermission(this)){

            takePicture();

        } else {
            PermissionsManager.askForPermissions(MainActivity.this,
                    PermissionsManager.WRITE_EXTERNAL_CODE,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        }

    }

    /**
     * Toma imagen usando la camara
     */
    private void takePicture(){

        StorageManager manager = new StorageManager(new ExternalStorage());

        try{

            File result = manager.write(this, IConstants.PHOTO_FILE_NAME, null, false);
            if(result != null){

                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                _photoUri = FileProvider.getUriForFile(this, IConstants.FILE_PROVIDER, result);


                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT,_photoUri);
                startActivityForResult(photoIntent, IConstants.PHOTO_RESULT_CODE);

            } else {

                Toast.makeText(this, getString(R.string.app_no_file_created), Toast.LENGTH_LONG);
                finish();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Resultado de la accion del usuario
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == IConstants.PHOTO_RESULT_CODE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userPhoto.setImageBitmap(imageBitmap);
        }
    }

    /***
     * Resultado de consulta al usuario por permisos
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String [] permissions, int [] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if(requestCode == PermissionsManager.WRITE_EXTERNAL_CODE){

                takePicture();

            } else {

                Toast.makeText(this, getString(R.string.app_no_write_permission), Toast.LENGTH_SHORT);
                finish();
            }
        }

    }

}
