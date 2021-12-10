package com.hilda.viewpageshallow;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ShallowTransformer implements ViewPager.PageTransformer {

    private final static int COUNT = 4;
    private final static String TAG = "ShallowTransformer";

    private int mCurrentItemIndex;
    private Duration mDuration;
    private List<ImageView> mImageViewList;

    public ShallowTransformer(List<ImageView> imageViews){
        mImageViewList = imageViews;
    }

    public void setCurrentItem(int index){
        mCurrentItemIndex = index;
        if(mCurrentItemIndex == 0){
            mDuration = Duration.RIGHT;
        }else if(mCurrentItemIndex == 3){
            mDuration = Duration.LEFT;
        }
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if(position<-1){
            page.setAlpha(0f);
            mDuration = Duration.RIGHT;
            Log.d(TAG, page.getTag()+" : "+position);
        }else if(position>1){
            page.setAlpha(0f);
            mDuration = Duration.LEFT;
            Log.d(TAG, page.getTag()+" : "+position);
        } else{
            if (-1 <= position && position < 0) {
                if(mDuration==Duration.RIGHT){
                    page.setTranslationX(0f);
                    page.setAlpha(1+position);
                    Log.d(TAG, page.getTag()+" right : "+position);
                }else if(mDuration==Duration.LEFT){
                    page.setTranslationX(-1 * page.getWidth() * position);
                    float alpha = 1+position;
                    page.setAlpha(alpha);
                    Log.d(TAG, page.getTag()+" left : "+position);
                }
            } else if (0 < position && position <= 1) {
                if(mDuration==Duration.RIGHT){
                    page.setTranslationX(-1 * page.getWidth() * position);
                    float alpha = 1-position;
                    page.setAlpha(alpha);
                    Log.d(TAG, page.getTag()+" right : "+position);
                }else if(mDuration==Duration.LEFT){
                    page.setTranslationX(0f);
                    page.setAlpha(1-position);
                    Log.d(TAG, page.getTag()+" left : "+position);
                }
            } else {
                page.setAlpha(1f);
                mDuration = Duration.NONE;
                Log.d(TAG, page.getTag()+" : "+position);
            }
        }
    }

    private enum Duration{
        LEFT, //key right,scroll to left
        RIGHT, //key left, scroll to right
        NONE //no scroll,
    }
}
