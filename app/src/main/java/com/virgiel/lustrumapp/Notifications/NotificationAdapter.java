package com.virgiel.lustrumapp.Notifications;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TUDelft SID on 16-11-2017.
 */
public class NotificationAdapter extends BaseAdapter {

    private Context context; //context
    private List<Notification> items; //data source of the list adapter
    private Typeface font;

    public NotificationAdapter(Context context, List<Notification> items, Typeface font) {
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
                    inflate(R.layout.notification_list_item, parent, false);
        }

        Notification currentItem = (Notification) getItem(position);
        TextView title = convertView.findViewById(R.id.notification_date);
        TextView body = convertView.findViewById(R.id.notification_body);
        title.setTypeface(font);
        body.setTypeface(font);
        title.setText(getDate(currentItem.getTime()));
        body.setText(currentItem.getBody());
        return convertView;

    }

    private String getDate(long timeStamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "Date unknown";
        }
    }
}
