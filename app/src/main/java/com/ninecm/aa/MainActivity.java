package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ninecm.aa.adapter.MainAdapterPager;

public class MainActivity extends AppCompatActivity {

    private MainAdapterPager adapterPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init();
        //setUp();
    }

    private void setUp() {
        tabLayout.setupWithViewPager(viewPager);

        adapterPager = new MainAdapterPager(getSupportFragmentManager());
        viewPager.setAdapter(adapterPager);
    }

    private  void init() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);
    }
}