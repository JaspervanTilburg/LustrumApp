package com.example.tudelftsid.lustrumapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

    /**
     * Created by TUDelft SID on 24-10-2017.
     */
    public class LustrumButton {

        public static final int EXPANDED_BUTTON_SIZE = 236;
        public static final int COLLAPSED_BUTTON_SIZE = 1;
        public static final int EXPAND_DURATION = 300;

        public static final int SELECT_BUTTON_SIZE = 314;
        public static final int DESELECT_BUTTON_SIZE = 157;
        public static final int SELECT_DURATION = 100;

        private int[] XLocations = {200, 300, 600};
        private int[] YLocations = {200, 1000, 300};

        private ImageView image;
        private int color;
        private ArrayList<LustrumButton> childButtons;

        public LustrumButton(ImageView image, int color) {
            this.image = image;
            this.color = color;
            childButtons = new ArrayList<>();
        }

    public void addChildButton(LustrumButton button) {
        childButtons.add(button);
    }

    public WeightAnimation animateScaleDown() {
        int width = image.getWidth();
        int height = image.getHeight();
        WeightAnimation scaleDown = new WeightAnimation(image,
                2, 1, SELECT_DURATION);
        image.startAnimation(scaleDown);
        return scaleDown;
    }

    public WeightAnimation animateScaleUp() {
        int width = image.getWidth();
        int height = image.getHeight();
        WeightAnimation scaleUp = new WeightAnimation(image,
               1, 2, SELECT_DURATION);
        image.startAnimation(scaleUp);
        return scaleUp;
    }

    public void animateExpand() {
        for (int i = 0; i < childButtons.size(); i++) {
            ImageView button = childButtons.get(i).getImage();
            ResizeAnimation expandAnimation = new ResizeAnimation(button,
                    COLLAPSED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, EXPAND_DURATION);
            TranslateAnimation translateAnimation = new TranslateAnimation(button,
                    image.getX() + 100, image.getY() + 100, XLocations[i], YLocations[i]);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(expandAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.setDuration(EXPAND_DURATION);

            button.setVisibility(View.VISIBLE);
            button.setClickable(true);
            button.startAnimation(animationSet);
        }
    }

        public void animateCollapse() {
            for (int i = 0; i < childButtons.size(); i++) {
                ImageView button = childButtons.get(i).getImage();
                ResizeAnimation collapseAnimation = new ResizeAnimation(button,
                        EXPANDED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, EXPAND_DURATION);
                TranslateAnimation translateAnimation = new TranslateAnimation(button,
                        XLocations[i], YLocations[i], image.getX() + 100, image.getY() + 100);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(collapseAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration(EXPAND_DURATION);

                button.setVisibility(View.INVISIBLE);
                button.setClickable(false);
                button.startAnimation(animationSet);
            }
        }

    public void setBackgroundColor(Activity activity) {
        activity.findViewById(R.id.activiteitenBackground).setBackgroundColor(ContextCompat.getColor(activity, color));
    }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }


    public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
