package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ninecm.aa.adapter.SearchItemAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private ArrayList<Cosmetic> cosmetics;
    private SearchItemAdapter searchItemAdapter;
    private TextView searchCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        setUp();
    }

    private void init() {
        // RecyclerView를 가져옴
        searchRecyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
        searchCancel = (TextView) findViewById(R.id.search_cancel);
    }

    private void setUp() {
        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200826", "20200823", "없음", 2);
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        searchItemAdapter = new SearchItemAdapter(cosmetics);
        // RecyclerView Manager를 LinearLayout으로 설정
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // RecyclerView Adapter 설정
        searchRecyclerView.setAdapter(searchItemAdapter);

        // 검색 취소 액션 리스너 등록
        searchCancel.setOnClickListener(goBackPage);
    }

    View.OnClickListener goBackPage = new View.OnClickListener() {
        public void onClick(View v) {
            SearchActivity.this.finish();
        }
    };

}