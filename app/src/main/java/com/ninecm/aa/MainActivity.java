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

        init();
        setUp();
    }

    private  void init() {
        // activity_main.xml에 <ViewPager>
        viewPager = (ViewPager) findViewById(R.id.time_viewpager);
        // activity_main.xml에 <TabLayout>
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    private void setUp() {
        // tabLayout을 viewPager와 함께 동작(연동)
        tabLayout.setupWithViewPager(viewPager);

        // viewPager에 Adapter 연결
        adapterPager = new MainAdapterPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterPager);

        // 메뉴 클릭 시 actionListener 추가
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(changePages);
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