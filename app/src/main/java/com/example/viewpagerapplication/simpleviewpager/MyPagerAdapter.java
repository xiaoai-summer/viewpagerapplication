package com.example.viewpagerapplication.simpleviewpager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagerapplication.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangxiaoyan on 2020/8/12.
 */
public class MyPagerAdapter extends PagerAdapter {
    private static final String TAG = "MyPagerAdapter";
    private LinkedList<View> mViewList;
    private ViewPager mViewPager;
    private static final int SCROLL_PAGER_MSG = 1;
    private long mLastSelectedTime;
    private View mCurrentView;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == SCROLL_PAGER_MSG && System.currentTimeMillis() - mLastSelectedTime >= 1000 * 5) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        }
    };

    public MyPagerAdapter(LinkedList <View> mViewList, ViewPager vpager) {
        this.mViewList = mViewList;
        this.mViewPager = vpager;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = mViewList.get(position);
//        container.addView(view);
        Log.i(TAG, "MyPagerAdapter::instantiateItem:position= " + position);
        View view;
        if(mViewList != null && !mViewList.isEmpty()){
            view = mViewList.getFirst();
            mViewList.removeFirst();
            Log.i(TAG, "mViewList.size() = " + mViewList.size());
        }else {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.simple_first_page, null);
        }
        container.addView(view);
        TextView textView = view.findViewById(R.id.text_pager);
        textView.setText(position+"");
        return view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViewList.addLast((View) object);
        Log.i(TAG, "MyPagerAdapter::destroyItem:position= " + position);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        mLastSelectedTime = System.currentTimeMillis();
        mCurrentView = (View) object;
        if(mHandler.hasMessages(SCROLL_PAGER_MSG)) {
            mHandler.removeMessages(SCROLL_PAGER_MSG);
        }
        final Message message = Message.obtain(mHandler);
        message.what = SCROLL_PAGER_MSG;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(message);
            }
        }, 1000 * 5);
    }

    public View getCurrentView(){
        return mCurrentView;
    }
}
