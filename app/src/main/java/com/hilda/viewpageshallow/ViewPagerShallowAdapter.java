package com.hilda.viewpageshallow;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerShallowAdapter extends PagerAdapter {
    private final int SIZE = 4;
    private List<ImageView> mImageViewList;

    public ViewPagerShallowAdapter(List<ImageView> imageViews){
        mImageViewList = imageViews;
    }

    @Override
    public int getCount() {
        return SIZE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mImageViewList.get(position));
    }
}
