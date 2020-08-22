package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ninecm.aa.adapter.MainAdapterPager;
import com.ninecm.aa.adapter.TimeItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainAdapterPager adapterPager;
    private TimeItemAdapter timeItemAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerView timeRecyclerView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setUp();
    }

    private  void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        timeRecyclerView = (RecyclerView) findViewById(R.id.time_recyclerview);
        list = new ArrayList<>();
    }

    private void setUp() {
        tabLayout.setupWithViewPager(viewPager);

        adapterPager = new MainAdapterPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(changePages);

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        timeItemAdapter = new TimeItemAdapter(list);
        timeRecyclerView.setAdapter(timeItemAdapter);
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    TabLayout.OnTabSelectedListener changePages = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}