package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TUDelft SID on 16-11-2017.
 */

public class TinderMatchAdapter extends BaseAdapter {
    private Context context; //context
    private List<Profile> items; //data source of the list adapter

    public TinderMatchAdapter(Context context, List<Profile> items) {
        this.context = context;
        this.items = items;
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
        Profile currentItem = (Profile) getItem(position);
        TextView textViewItemName = (TextView) convertView.findViewById(R.id.matchNameTxt);
        TextView textViewCreatedAt = (TextView) convertView.findViewById(R.id.matchCreatedAtTxt);
        textViewItemName.setText(currentItem.getName() + " (" + currentItem.getAge() + ")");
        textViewCreatedAt.setText(currentItem.getMatchCreatedAt());
        return convertView;
    }
}
