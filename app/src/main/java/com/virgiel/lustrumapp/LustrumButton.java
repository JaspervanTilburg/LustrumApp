package com.virgiel.lustrumapp;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.virgiel.lustrumapp.Animation.ResizeAnimation;
import com.virgiel.lustrumapp.Animation.TranslateAnimation;
import com.virgiel.lustrumapp.Animation.WeightAnimation;

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

        private int xLoc;
        private int yLoc;

        private ImageView image;
        private int color;
        private String name;
        private ArrayList<LustrumButton> childButtons;

        public LustrumButton(ImageView image, int color, String name) {
            this.image = image;
            this.color = color;
            this.name = name;
            childButtons = new ArrayList<>();
        }

        public LustrumButton(ImageView image, int color, int x, int y) {
            this.image = image;
            this.color = color;
            this.xLoc = x;
            this.yLoc = y;
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
                    image.getX() + 150, image.getY() + 150, childButtons.get(i).getxLoc(), childButtons.get(i).getyLoc());
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
                        childButtons.get(i).getxLoc(), childButtons.get(i).getyLoc(), image.getX() + 150, image.getY() + 150);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(collapseAnimation);
                animationSet.setDuration(EXPAND_DURATION);

                button.setVisibility(View.INVISIBLE);
                button.setClickable(false);
                button.startAnimation(animationSet);
            }
        }

    public void setBackgroundColor(Activity activity) {
        activity.findViewById(R.id.activiteitenBackground).setBackgroundColor(ContextCompat.getColor(activity, color));
    }

    public void setName(Activity activity){
        ((TextView) activity.findViewById(R.id.timeline_text)).setText(name);
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

        public int getxLoc() {
            return xLoc;
        }

        public void setxLoc(int xLoc) {
            this.xLoc = xLoc;
        }

        public int getyLoc() {
            return yLoc;
        }

        public void setyLoc(int yLoc) {
            this.yLoc = yLoc;
        }
    }


