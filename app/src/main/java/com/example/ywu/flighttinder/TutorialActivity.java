package com.example.ywu.flighttinder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TutorialActivity extends FragmentActivity{

    private static final int NUM_PAGES = 4;
    private SliderViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_page);

        viewPager = (SliderViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SliderPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnSwipeOutListener(new SliderViewPager.OnSwipeOutListener() {
            @Override
            public void onSwipeOutAtEnd() {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class SliderPagerAdapter extends FragmentStatePagerAdapter {
        public SliderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SliderFragment.createFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
