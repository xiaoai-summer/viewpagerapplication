package com.example.viewpagerapplication.simpleviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by wangxiaoyan on 2020/8/12.
 */
public class MyViewPager extends ViewPager {
    private final float MIN_SCALE = 0.55f;  //两边图相对焦点图缩放比例

    private final float PAGE_MARGIN = 155 * MIN_SCALE + 20;

    private final float PAGE_SUB_TRANSLATION_Y = 10;

    public MyViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final float translationY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                PAGE_SUB_TRANSLATION_Y, getResources().getDisplayMetrics());
        setChildrenDrawingOrderEnabled(true);
        setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                if(page == null) {
                    return;
                }
                //缩放
                if (position < -1) { // [-Infinity,-1)
                    page.setScaleX(MIN_SCALE);
                    page.setScaleY(MIN_SCALE);
                    page.setTranslationY(translationY);
                } else if (position <= 1) { // [-1,1]
                    float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);
                    page.setTranslationY(translationY * Math.abs(position));
                } else { // (1,+Infinity]
                    page.setScaleX(MIN_SCALE);
                    page.setScaleY(MIN_SCALE);
                    page.setTranslationY(translationY);
                }
                //只显示三个page
                if(Math.abs(position) >= 2) {
                    page.setAlpha(0f);
                } else {
                    page.setAlpha(1f);
                }
            }
        });
        if(getResources() != null) {
            int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    PAGE_MARGIN, getResources().getDisplayMetrics());
            setPageMargin(-pageMargin);
        }
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View focusedChild = getCurrentView() != null ? getCurrentView() : getFocusedChild();
        int focusViewIndex = indexOfChild(focusedChild);
        if (focusViewIndex == -1) {
            return i;
        }
        if (i == focusViewIndex) {
            return childCount - 1;
        } else if (i == childCount - 1) {
            return focusViewIndex;
        }
        return i;
    }

    private View getCurrentView() {
        if(getAdapter() != null && getAdapter() instanceof MyPagerAdapter) {
            return ((MyPagerAdapter) getAdapter()).getCurrentView();
        }
        return null;
    }
}
