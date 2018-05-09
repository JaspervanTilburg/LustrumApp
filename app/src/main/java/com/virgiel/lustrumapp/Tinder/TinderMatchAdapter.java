package com.virgiel.lustrumapp.Tinder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.virgiel.lustrumapp.LustrumRestClient;
import com.virgiel.lustrumapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        String dateDifference = getDateDifference(currentItem.getMatchCreatedAt());
        TextView textViewItemName = (TextView) convertView.findViewById(R.id.matchNameTxt);
        TextView textViewCreatedAt = (TextView) convertView.findViewById(R.id.matchCreatedAtTxt);
        TextView textViewNumber = (TextView) convertView.findViewById(R.id.phoneTxt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.matchImageView);

        textViewItemName.setTypeface(font);
        textViewCreatedAt.setTypeface(font);
        textViewNumber.setTypeface(font);

        if (!currentItem.getAvatar().contains("missing")) {
            Glide.with(context).load(LustrumRestClient.BASE_URL + currentItem.getAvatar()).into(imageView);
        }
        textViewItemName.setText(currentItem.getName() + " (" + currentItem.getAge() + ")");
        textViewCreatedAt.setText(dateDifference);
        textViewNumber.setText("+" + currentItem.getPhone());
        return convertView;
    }

    private String getDateDifference(String date) {
        String[] numbers = date.split("-");
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH,Integer.parseInt(numbers[2].split("T")[0]));
        thatDay.set(Calendar.MONTH,Integer.parseInt(numbers[1])-1);
        thatDay.set(Calendar.YEAR, Integer.parseInt(numbers[0]));
        Calendar today = Calendar.getInstance();
        long diff = today.getTimeInMillis() - thatDay.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        if (days < 30) {
            return (days + " dagen geleden");
        }
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        return (format1.format(thatDay.getTime()));
    }
}
