package com.hilda.viewpageshallow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "ViewPager";
    private final int COUNT = 4;
    private int mIndex = 0;
    private ViewPager mViewPager;
    private ShallowTransformer mShallowTransformer;
    private List<ImageView> mImageViewList = new ArrayList<>(COUNT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager_shallow);
        initData(this);
        mShallowTransformer = new ShallowTransformer(mImageViewList);
        mViewPager.setAdapter(new ViewPagerShallowAdapter(mImageViewList));
        mViewPager.setPageTransformer(false, mShallowTransformer);
        mViewPager.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
                        if (mIndex <= 0) {
                            mIndex = 0;
                        } else {
                            mIndex--;
                        }
                        mViewPager.setCurrentItem(mIndex);
                        mShallowTransformer.setCurrentItem(mIndex);
                        Log.d(TAG, "index = "+mIndex+","+keyEvent);
                        return true;
                    } else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        if (mIndex >= 3) {
                            mIndex = 3;
                        } else {
                            mIndex++;
                        }
                        mViewPager.setCurrentItem(mIndex);
                        mShallowTransformer.setCurrentItem(mIndex);
                        Log.d(TAG, "index = "+mIndex+","+keyEvent);
                        return true;
                    }
                    return false;
                }else{
                    return false;
                }
            }
        });
        initViewPagerScroll();
    }

    private void initData(Context context){
        for(int i = 0; i<COUNT;++i){
            ImageView imageView = new ImageView(context);
            if(i==0){
                imageView.setImageResource(R.drawable.slide0);
                imageView.setTag("00");
            }else if(i==1){
                imageView.setImageResource(R.drawable.slide1);
                imageView.setTag("01");
            }else if(i==2){
                imageView.setImageResource(R.drawable.slide2);
                imageView.setTag("02");
            }else if(i==3){
                imageView.setImageResource(R.drawable.slide3);
                imageView.setTag("03");
            }
            mImageViewList.add(imageView);
        }
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            ShallowScroller mScroller = new ShallowScroller(mViewPager.getContext());
            mScroller.setDuration(1000);
            mField.set(mViewPager, mScroller);
        } catch (Exception e) {

        }
    }

}