package com.example.ywu.flighttinder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SliderFragment extends Fragment {
    private int position;

    public static SliderFragment createFragment (int position) {
        SliderFragment sliderFragment = new SliderFragment();
        sliderFragment.position = position;
        return sliderFragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.slider_page, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        if (position == 0) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.tut1));
        } else if (position == 1) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.tut2));
        } else if (position == 2) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.tut3));
        } else if (position == 3) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.tut4));
        }
        return rootView;
    }
}
