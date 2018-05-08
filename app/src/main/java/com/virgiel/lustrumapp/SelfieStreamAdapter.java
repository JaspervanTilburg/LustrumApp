package com.virgiel.lustrumapp;

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

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.selfie_list_item, parent, false);
        }

        final Selfie currentItem = (Selfie) getItem(position);
        ImageView imageView = convertView.findViewById(R.id.selfieImageView);
        TextView userNameView = convertView.findViewById(R.id.selfieUserName);
        ImageButton selfieLikeButton = convertView.findViewById(R.id.selfieLikeButton);
        Button selfieDeleteButton = convertView.findViewById(R.id.selfieDeleteButton);
        final TextView likesTextView = convertView.findViewById(R.id.likesTextView);
        final Drawable likePink = convertView.getResources().getDrawable(R.mipmap.like_pink);
        final Drawable likeWhite = convertView.getResources().getDrawable(R.mipmap.like);
        likePink.setBounds(0,0,50,50);
        likeWhite.setBounds(0,0,50,50);
        userNameView.setTypeface(font);
        likesTextView.setTypeface(font);

        selfieLikeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Selfie selfie = items.get(position);
                if (currentItem.isLikedByMe()) {
                    currentItem.setLikedByMe(false);
                    currentItem.setLikes(currentItem.getLikes() - 1);
                    postSelfieUnLike(selfie.getId());
                    likesTextView.setText(" " + currentItem.getLikes() + " vind-ik-vo's");
                    likesTextView.setCompoundDrawables(likeWhite, null, null, null);
                } else {
                    currentItem.setLikedByMe(true);
                    currentItem.setLikes(currentItem.getLikes() + 1);
                    postSelfieLike(selfie.getId());
                    likesTextView.setText(" " + currentItem.getLikes() + " vind-ik-vo's");
                    likesTextView.setCompoundDrawables(likePink, null, null, null);
                }
            }
        });

        selfieDeleteButton.setBackgroundColor(Color.TRANSPARENT);
        selfieDeleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Selfie selfie = items.get(position);
                if (LustrumRestClient.getToken().equals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjM1NDQ1MDEsInN1YiI6Njc0fQ._d5IkbZ7L-7tqOFoXp1NgMgKCqRbrw0CxWs48Wv4ju0")) {
                    deleteSelfie(selfie.getId());
                    Toast.makeText(context, "Selfie Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

        Glide.with(context).load(LustrumRestClient.BASE_URL + currentItem.getImageURL()).into(imageView);
        likesTextView.setText(" " + currentItem.getLikes() + " vind-ik-vo's");
        userNameView.setText(currentItem.getUser());
        if (currentItem.isLikedByMe()) {
            likesTextView.setCompoundDrawables(likePink, null, null, null);
        }
        return convertView;

    }

    public void postSelfieLike(int image_id) {
        LustrumRestClient.getWithHeader("/selfies/" + image_id + "/like", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Selfie liked: " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong with selfie like" + statusCode + ", " + msg + ", " + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with selfie like " + msg);
            }
        });
    }

    public void postSelfieUnLike(int image_id) {
        LustrumRestClient.getWithHeader("/selfies/" + image_id + "/unlike", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Selfie unliked: " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong with selfie unlike" + statusCode + ", " + msg + ", " + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with selfie unlike " + msg);
            }
        });
    }

    public void deleteSelfie(int image_id) {
        LustrumRestClient.getWithHeader("/selfies/" + image_id + "/delete", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Selfie deleted: " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String msg, Throwable throwable) {
                System.out.println("Something went wrong with selfie delete" + statusCode + ", " + msg + ", " + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject msg) {
                System.out.println("Something went wrong with selfie delete " + msg);
            }
        });
    }
}
