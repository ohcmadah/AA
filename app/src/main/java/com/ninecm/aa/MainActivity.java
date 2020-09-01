package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ninecm.aa.adapter.MainAdapterPager;
import com.roacult.backdrop.BackdropLayout;

public class MainActivity extends AppCompatActivity {

    private MainAdapterPager adapterPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private ImageButton btnSearch;
    private BackdropLayout backdropLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setUp();
    }

    private  void init() {
        viewPager = (ViewPager) findViewById(R.id.time_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        fab = (FloatingActionButton) findViewById(R.id.btn_plus);
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
    }


    private void setUp() {
        // tabLayout을 viewPager와 함께 동작(연동)
        tabLayout.setupWithViewPager(viewPager);

        // viewPager에 Adapter 연결
        adapterPager = new MainAdapterPager(getSupportFragmentManager(), tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapterPager);

        // 메뉴 클릭 ActionListener
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(changePage);

        // 검색 버튼 클릭 ActionListener
        btnSearch.setOnClickListener(goSearchPage);
        fab.setOnClickListener(goAddPage);
    }


    // 상단 Navigation ActionListener
    TabLayout.OnTabSelectedListener changePage = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());

            if (tab.getPosition() == 1) { // RANKING이면
                fab.setVisibility(View.INVISIBLE); // FAB INVISIBLE
            } else { // TIME이면
                fab.setVisibility(View.VISIBLE); // FAB VISIBLE
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    // Search Button Click
    View.OnClickListener goSearchPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        }
    };

    // backdrop 안돼서 일단 클릭하면 옆으로 넘어가는걸로 대채함..
    View.OnClickListener goAddPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };
}