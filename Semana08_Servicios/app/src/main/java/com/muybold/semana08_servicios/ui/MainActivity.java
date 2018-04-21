package com.muybold.semana08_servicios.ui;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.muybold.semana08_servicios.R;
import com.muybold.semana08_servicios.uimanagers.MainUiManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MainUiManager _uiManager;
    private WifiManager _wifiManager;
    private List<ScanResult> _wifiResults;

    @BindView(R.id.state_list)
    ListView wifiList;

    @BindView(R.id.switch_scan)
    Switch stateSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //_uiManager = MainUiManager.getInstance(this);
        _wifiManager = (WifiManager)getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);

        ButterKnife.bind(this);

         _wifiResults = _wifiManager.getScanResults();
         setUpView();
    }

    private void setUpView(){
        wifiList.setAdapter(new WifiAdapter());
    }

    @OnClick(R.id.hello_btn)
    public void onHelloClick(){
        _uiManager.onHelloClick();
    }

    class WifiAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return _wifiResults.size();
        }

        @Override
        public Object getItem(int position) {
            return _wifiResults.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View wifiView = getLayoutInflater().inflate(R.layout.layout_network, parent,false);

            TextView ssid = wifiView.findViewById(R.id.ssid_label);
            TextView level = wifiView.findViewById(R.id.level);

            ssid.setText(_wifiResults.get(position).SSID);

            int netLevel = WifiManager.calculateSignalLevel(_wifiResults.get(position).level,3);

            switch (netLevel){
                case 0:
                    level.setText("Baja");
                    level.setTextColor(getResources().getColor(R.color.rojo));
                    break;
                case 1:
                    level.setText("Buena");
                    level.setTextColor(getResources().getColor(R.color.amarillo));
                    break;
                case 2:
                    level.setText("Excelente");
                    level.setTextColor(getResources().getColor(R.color.verde));
                    break;
            }

            return wifiView;
        }
    }
}
