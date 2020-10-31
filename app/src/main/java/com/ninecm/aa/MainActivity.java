package com.ninecm.aa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ninecm.aa.adapter.MainAdapterPager;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_COSMETIC_REQUEST = 1;

    private MainAdapterPager adapterPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private ImageButton btnSearch;

    private MainViewModel viewModel;

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

    View.OnClickListener goAddPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, ADD_COSMETIC_REQUEST);
            overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_stay);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_COSMETIC_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            String endDay = data.getStringExtra(AddActivity.EXTRA_END_DAY);
            int starNum = data.getIntExtra(AddActivity.EXTRA_STAR, 1);
            String memo = data.getStringExtra(AddActivity.EXTRA_MEMO);

            Cosmetic cosmetic = new Cosmetic(title, endDay, starNum, memo);
            setViewModel();
            viewModel.insert(cosmetic);

            Toast.makeText(this, "제품이 등록되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "제품이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setViewModel() {
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(MainViewModel.class);
    }
}