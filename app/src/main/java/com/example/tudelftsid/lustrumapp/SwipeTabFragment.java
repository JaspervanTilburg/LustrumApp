package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.Collections;
import java.util.List;

/**
 * Created by TUDelft SID on 5-10-2017.
 */

public class SwipeTabFragment extends Fragment {

    private View rootView;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.swipe_tab_layout, container, false);

        mSwipeView = (SwipePlaceHolderView) rootView.findViewById(R.id.swipeView);
        mContext = getActivity().getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_accept_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_reject_view));


        List<Profile> profiles = Utils.loadProfiles(getActivity().getApplicationContext());
        Collections.shuffle(profiles);
        for(Profile profile : profiles){
            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
        }

        rootView.findViewById(R.id.dislikeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        rootView.findViewById(R.id.likeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        return rootView;
    }

}
