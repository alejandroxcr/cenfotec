package com.muybold.semana04.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.muybold.semana04.R;
import com.muybold.semana04.classes.People;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Usuario on 4/6/2018.
 */

public class PeopleAdapter extends BaseAdapter {

    private Context _context;
    private LayoutInflater _inflater;
    private List<People> _list;

    private ProgressBar _progresIndicator;

    public PeopleAdapter(Context context, List<People> list){

        _context = context;
        _list = list;
        _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = _inflater.inflate(R.layout.row_people_layout, viewGroup, false);

        try {
            TextView name = rowView.findViewById(R.id.name);
            People current = _list.get(position);

            if (current != null)
                name.setText(current.name);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return rowView;
    }
} // PeopleAdapter
