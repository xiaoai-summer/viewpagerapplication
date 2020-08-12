package com.example.viewpagerapplication.simpleviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.viewpagerapplication.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyViewPagerActivity extends AppCompatActivity {
    private LinkedList<View> mViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);

        MyViewPager vpager = findViewById(R.id.vpager);
        mViewList = new LinkedList<>();

//        mViewList.add(getLayoutInflater().inflate(R.layout.simple_second_page,null,false));
//        mViewList.add(getLayoutInflater().inflate(R.layout.simple_third_page,null,false));
//
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mViewList,vpager);
        int mCurrentPos = 10;
        vpager.setAdapter(myPagerAdapter);
        vpager.setCurrentItem(mCurrentPos);
    }
}