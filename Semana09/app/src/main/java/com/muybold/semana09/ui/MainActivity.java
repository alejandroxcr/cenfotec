package com.muybold.semana09.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muybold.semana09.R;
import com.muybold.semana09.ui.fragments.MenuListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainVp)
    ViewPager _mainVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        uiInit();
    }

    /**
     * Inicializa elementos de Ui
     */
    private void uiInit(){
        //_mainVp = new MainPagerAdapter(getSupportFragmentManager());
    } // uiInit


    public static class MainPagerAdapter extends FragmentPagerAdapter {

        private static int ITEMS_COUNT = 2;

        public MainPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        } // MainPagerAdapter

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MenuListFragment();
                case 2:
                    return null;
            }

            return null;
        }

        @Override
        public int getCount() {
            return ITEMS_COUNT;
        }
    }

}
