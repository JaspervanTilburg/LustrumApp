package com.virgiel.lustrumapp.Soundboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.virgiel.lustrumapp.R;

/**
 * Created by Jip on 24-11-2017.
 */

public abstract class SoundStore {

    public static Sound[] getSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray labels = res.obtainTypedArray(R.array.labels);
        TypedArray ids = res.obtainTypedArray(R.array.ids);

        Sound[] sounds = new Sound[labels.length()];

        for (int i = 0; i < sounds.length; i++) {
            sounds[i] = new Sound(labels.getString(i), ids.getResourceId(i, -1));
        }

        labels.recycle();
        ids.recycle();

        return sounds;
    }

}

