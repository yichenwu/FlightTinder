package com.example.ywu.flighttinder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SliderViewPager extends ViewPager {
    float mStartDragX;
    OnSwipeOutListener mListener;

    public SliderViewPager(Context context) {
        super(context);
    }

    public SliderViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX > x && getCurrentItem() == getAdapter().getCount() - 1) {
                    mListener.onSwipeOutAtEnd();
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnSwipeOutListener {
        void onSwipeOutAtEnd();
    }
}
