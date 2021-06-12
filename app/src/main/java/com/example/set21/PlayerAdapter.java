
package com.example.set21;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.set21.Player;
import com.example.set21.R;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    Context context;
    List<Player> objects;
    public PlayerAdapter(Context context, int resource, List<Player> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        TextView tvName =(TextView) view.findViewById(R.id.tvName);
        TextView tvSets =(TextView) view.findViewById(R.id.tvSets);
        TextView tvDate =(TextView) view.findViewById(R.id.tvDate);

        Player temp = objects.get(position);
        tvName.setText(temp.getName());
        tvSets.setText(String.valueOf(temp.getSets()));
        tvDate.setText(temp.getDate());

        return view;
    }
}