package com.virgiel.lustrumapp.DateSpel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rolf on 15-11-2017.
 */

public class DateSpelAdapter extends ArrayAdapter<DateSpelCard> {

    private Context context;
    private int resource;
    private ArrayList<DateSpelCard> objects;

    public DateSpelAdapter(@NonNull Context context, int layoutId, @NonNull ArrayList<DateSpelCard> objects) {
        super(context, layoutId, objects);
        this.context = context;
        this.resource = layoutId;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DateSpelViewHolder viewHolder = null;

        if (row==null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            viewHolder = new DateSpelViewHolder();
            //viewHolder.upperText = (TextView)row.findViewById(R.id.datespel_card_upper_text);
            //viewHolder.bottomText = (TextView)row.findViewById(R.id.datespel_card_bottom_text);

            row.setTag(viewHolder);
        } else {
            viewHolder = (DateSpelViewHolder)row.getTag();
        }

        DateSpelCard card = objects.get(position);

        //viewHolder.upperText.setText(card.getUpperText());
        //viewHolder.bottomText.setText(card.getBottomText());

        return row;
    }
    
    static class DateSpelViewHolder {
        TextView upperText;
        TextView bottomText;
    }





}
