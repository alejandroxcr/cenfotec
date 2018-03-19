package com.muybold.semana03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.muybold.semana03.classes.InternalStorage;
import com.muybold.semana03.helpers.StorageManager;
import com.muybold.semana03.interfaces.IConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StorageActivity extends AppCompatActivity {

    @BindView(R.id.name_text)
    EditText name;


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

        StorageManager manager = new StorageManager(new InternalStorage());

        String content = manager.read(this, IConstants.APP_INTERNAL_NAMES_FILE);
    }


    @OnClick(R.id.add_btn)
    public void onAddClicked(){

        String nameStr = name.getText().toString().trim();
        if(!nameStr.isEmpty()){

            // Guardar en almacenamiento interno
            StorageManager manager = new StorageManager(new InternalStorage());
            boolean result = manager.write(this, IConstants.APP_INTERNAL_NAMES_FILE, nameStr);

            if(result){

                Toast.makeText(this, R.string.app_successfully_added, Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, R.string.app_fail_added, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
