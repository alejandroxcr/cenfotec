package com.muybold.semana06.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muybold.semana06.R;

/**
 * Created by Estudiantes on 07/04/2018.
 */

public class ChaoFragment extends Fragment {

    private IChaoFragment _interface;

    public interface IChaoFragment {

        void onChaoBtnClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chao_fragment, container, false);
    }

}
