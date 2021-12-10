package com.hilda.viewpageshallow;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ShallowScroller extends Scroller {

    private int mDuration;

    public ShallowScroller(Context context) {
        super(context);
    }

    public ShallowScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ShallowScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setDuration(int time) {
        mDuration = time;
    }
}
