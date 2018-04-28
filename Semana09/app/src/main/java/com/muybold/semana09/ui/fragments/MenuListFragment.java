package com.muybold.semana09.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.muybold.semana09.R;

/**
 * Created by Estudiantes on 28/04/2018.
 */

public class MenuListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    } // onCreate


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        try{
            view = inflater.inflate(R.layout.fragment_menu, container, false);

        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        return view;
    }

} // MenuListFragment
