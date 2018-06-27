package com.virgiel.lustrumapp.PintenVangen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.R;
import com.virgiel.lustrumapp.Selfie;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 16-11-2017.
 */
public class HighscoreAdapter extends BaseAdapter {

    private Context context; //context
    private List<Score> items; //data source of the list adapter
    private Typeface font; //The Font the data should be displayed in

    public HighscoreAdapter(Context context, List<Score> items, Typeface font) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.highscore_list_item, parent, false);
        }

        Score currentItem = (Score) getItem(position);
        TextView name = convertView.findViewById(R.id.highscore_name);
        TextView score = convertView.findViewById(R.id.highscore_score);
        name.setTypeface(font);
        score.setTypeface(font);
        name.setText((position + 1) + ".  " + currentItem.getUser());
        score.setText(Integer.toString(currentItem.getScore()));

        return convertView;

    }
}
