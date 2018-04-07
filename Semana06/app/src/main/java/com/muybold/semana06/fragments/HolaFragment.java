package com.muybold.semana06.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muybold.semana06.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Estudiantes on 07/04/2018.
 */

public class HolaFragment extends Fragment {

    @BindView(R.id.text_msg)
    TextView _text;

    private IHolaFragment _interface;

    public interface IHolaFragment {

        void onHolaBtnClick();

    } // IHolaFragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hola_fragment, container, false);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            _interface = (IHolaFragment) context;
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
