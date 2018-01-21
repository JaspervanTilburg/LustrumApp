package com.example.tudelftsid.lustrumapp.Soundboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tudelftsid.lustrumapp.R;

import java.util.ArrayList;

/**
 * Created by Rolf on 21-1-2018.
 */

public class SoundAdapter extends ArrayAdapter<Sound> {

    private Context mContext;
    private int mResourceId;
    private Sound[] mSounds;

    public SoundAdapter(@NonNull Context context, int resourceId, Sound[] sounds) {
        super(context, resourceId, sounds);
        this.mContext = context;
        this.mResourceId = resourceId;
        this.mSounds = sounds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(mResourceId, parent,false);

        Sound currentSound = mSounds[position];

        ImageView image = (ImageView)listItem.findViewById(R.id.soundboard_button);
        image.setImageResource(currentSound.getmImageId());

        return listItem;
    }
}
