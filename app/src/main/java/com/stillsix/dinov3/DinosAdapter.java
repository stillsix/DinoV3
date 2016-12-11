package com.stillsix.dinov3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DinosAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    Dinos data[] = null;

    public DinosAdapter(Context context, int layoutResourceId, Dinos[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DinoHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new DinoHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.dietIcon = (ImageView)row.findViewById(R.id.dietIcon);
            holder.elementIcon = (ImageView)row.findViewById(R.id.elementIcon);
            holder.eraIcon = (ImageView)row.findViewById(R.id.eraIcon);

            row.setTag(holder);
        }
        else
        {
            holder = (DinoHolder)row.getTag();
        }

        Dinos dino = data[position];
        holder.txtTitle.setText(dino.title);
        holder.imgIcon.setImageResource(dino.icon);
        holder.dietIcon.setImageResource(dino.diet_icon);
        holder.elementIcon.setImageResource(dino.element_icon);
        holder.eraIcon.setImageResource(dino.era_icon);

        return row;
    }

    static class DinoHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        ImageView dietIcon;
        ImageView eraIcon;
        ImageView elementIcon;
    }
}


