package com.example.tudelftsid.lustrumapp.Tinder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tudelftsid.lustrumapp.Profile;
import com.example.tudelftsid.lustrumapp.R;

import java.util.List;

/**
 * Created by TUDelft SID on 16-11-2017.
 */

public class TinderMatchAdapter extends BaseAdapter {
    private Context context; //context
    private List<Profile> items; //data source of the list adapter
    private Typeface font; //The Font the data should be displayed in

    public TinderMatchAdapter(Context context, List<Profile> items, Typeface font) {
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
        Profile currentItem = (Profile) getItem(position);
        TextView textViewItemName = (TextView) convertView.findViewById(R.id.matchNameTxt);
        TextView textViewCreatedAt = (TextView) convertView.findViewById(R.id.matchCreatedAtTxt);

        textViewItemName.setTypeface(font);
        textViewCreatedAt.setTypeface(font);

        textViewItemName.setText(currentItem.getName() + " (" + currentItem.getAge() + ")");
        textViewCreatedAt.setText(currentItem.getMatchCreatedAt());
        return convertView;
    }
}
