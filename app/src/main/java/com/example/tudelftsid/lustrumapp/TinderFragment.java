package com.example.tudelftsid.lustrumapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tudelftsid.lustrumapp.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinderFragment extends Fragment {

    public ArrayList<String> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tinder, container, false);

        items = new ArrayList<>();
        items.add("Jasper");
        items.add("Rolf");
        items.add("Klaas");
        items.add("Jan");
        final SwipeFlingAdapterView swipeView = (SwipeFlingAdapterView) view.findViewById(R.id.swipeFrame);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.tinder_item, items);
        swipeView.setAdapter(adapter);
        swipeView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                items.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(getActivity().getApplicationContext(), "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(getActivity().getApplicationContext(), "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                items.add("XML ".concat(String.valueOf(itemsInAdapter)));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float v) {

            }
        });

        return view;
    }
}
