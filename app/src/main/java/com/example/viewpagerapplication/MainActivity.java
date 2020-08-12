package com.example.viewpagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viewpagerapplication.simpleviewpager.MyViewPagerActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mSimpleViewPagerBtn;
    private Button mFragmentViewPagerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleViewPagerBtn = findViewById(R.id.simple_viewpager_btn);
        mFragmentViewPagerBtn = findViewById(R.id.fragment_viewpager_btn);
        mSimpleViewPagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyViewPagerActivity.class));
            }
        });
    }
}