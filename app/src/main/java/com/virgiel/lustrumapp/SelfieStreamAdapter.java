package com.virgiel.lustrumapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by TUDelft SID on 16-11-2017.
 */

public class SelfieStreamAdapter extends BaseAdapter {
    private Context context; //context
    private List<Selfie> items; //data source of the list adapter
    private Typeface font; //The Font the data should be displayed in

    public SelfieStreamAdapter(Context context, List<Selfie> items, Typeface font) {
        this.context = context;
        this.items = items;
        this.font = font;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.tinder_match_list_item, parent, false);
        }
        Selfie currentItem = (Selfie) getItem(position);

        return convertView;
    }
}
