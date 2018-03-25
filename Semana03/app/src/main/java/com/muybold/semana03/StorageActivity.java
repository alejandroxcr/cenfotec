package com.muybold.semana03;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muybold.semana03.classes.ExtenalStorage;
import com.muybold.semana03.classes.InternalStorage;
import com.muybold.semana03.helpers.PermissionManager;
import com.muybold.semana03.helpers.StorageManager;
import com.muybold.semana03.interfaces.IConstants;
import com.muybold.semana03.interfaces.IStorage;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StorageActivity extends AppCompatActivity {

    @BindView(R.id.name_text)
    EditText name;

    @BindView(R.id.external_name)
    EditText externalName;

    @BindView(R.id.welcome_msg)
    TextView welcome;

    @BindView(R.id.stored_viewer)
    TextView storedViewer;

    @BindView(R.id.external_stored)
    TextView externalViewer;

    private String _userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ButterKnife.bind(this);

        init();
    }

    /**
     * Inicializa activity
     */
    private void init(){

        String welcomeMsg = null;
        StorageManager manager = new StorageManager(new InternalStorage());
        String content = manager.read(this, IConstants.APP_INTERNAL_NAMES_FILE);

        manager = new StorageManager(new ExtenalStorage());
        String contentExternal = manager.read(this,"semana02.txt");

        if(!content.isEmpty())
            storedViewer.setText(content);

        if(!contentExternal.isEmpty())
            externalViewer.setText(contentExternal);

        _userName = getIntent().getStringExtra(IConstants.APP_USERNAME);
        welcomeMsg = getString(R.string.app_welcome) + ", " + _userName;
        welcome.setText(welcomeMsg);

    } // init


    @OnClick(R.id.add_btn)
    public void onAddClicked(){

        String nameStr = name.getText().toString().trim();
        if(!nameStr.isEmpty()){

            // Guardar en almacenamiento interno
            StorageManager manager = new StorageManager(new InternalStorage());
            boolean result = manager.write(this, IConstants.APP_INTERNAL_NAMES_FILE, nameStr, true);

            if(result){

                Toast.makeText(this, R.string.app_successfully_added, Toast.LENGTH_SHORT).show();
                name.setText("");
                updateViewer();

            } else {

                Toast.makeText(this, R.string.app_fail_added, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @OnClick(R.id.add_external_btn)
    public void addExternalClicked(){

        String usrInput = externalName.getText().toString().trim();

        if(!usrInput.isEmpty()) {

            // Check for external write permissions
            if (PermissionManager.hasWriteExternalWritePermission(StorageActivity.this)) {
                saveExternal();
            } else {

                PermissionManager.askPermission(this,
                        PermissionManager.PERMISSION_WRITE_EXTERNAL_CODE,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
            }
        } else {

            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String [] permissions, int [] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            switch (requestCode){
                case PermissionManager.PERMISSION_WRITE_EXTERNAL_CODE:
                    saveExternal();
                    break;
            }

        }
    }

    /**
     * Saves in external location
     */
    private void saveExternal(){

        StorageManager manager = new StorageManager(new ExtenalStorage());
        String usrInput = externalName.getText().toString().trim();

        boolean result = manager.write(this, "semana02.txt", usrInput, true);

        if(result) {

            String updatedExternal = manager.read(this,"semana02.txt");
            if(updatedExternal != null)
                externalViewer.setText(updatedExternal);
                externalName.setText("");

        }
        else {
            Toast.makeText(this, getString(R.string.app_fail_added), Toast.LENGTH_LONG);
        }

    } // saveExternal

    /**
     * uPDATE internal storage viewer
     */
    private void updateViewer(){

        StorageManager manager = new StorageManager(new InternalStorage());
        String content = manager.read(this, IConstants.APP_INTERNAL_NAMES_FILE);
        if(!content.isEmpty()) storedViewer.setText(content);

    }

}
