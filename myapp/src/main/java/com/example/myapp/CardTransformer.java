package com.example.myapp;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CardTransformer implements ViewPager.PageTransformer {
private static final float MAX_SCALE = 1.2f;
private static final float MIN_SCALE = 1.0f;
    /**
     * transformPage是CardTransformer类实现了ViewPager.PageTransformer之后实现的方法
     * @param view
     * @param v
     */
    @Override
    public void transformPage(@NonNull View view, float v) {
        if(v <= 1){
            //1.2f+(1-1)*(1.2f-1.0f)-----不知道是些啥
            float scaleFactor = MIN_SCALE + (1 - Math.abs(v)) * (MAX_SCALE - MIN_SCALE);
            view.setScaleX(scaleFactor);

            if(v > 0){
                view.setTranslationX(-scaleFactor * 2);
            }else if(v < 0){
                view.setTranslationY(scaleFactor * 2);
            }
            view.setScaleY(scaleFactor);
        }else{
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
        }
    }
}
