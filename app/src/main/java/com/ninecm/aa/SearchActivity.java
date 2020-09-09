package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ninecm.aa.adapter.SearchItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private List<Cosmetic> cosmetics;
    private SearchItemAdapter searchItemAdapter;
    private TextView searchCancel;
    private EditText searchEditText;
    private String value="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200823", 2, "없음");
        Cosmetic cosmetic2 = new Cosmetic("아큐브 투명 렌즈", "20200823", 3, "없음");
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic2);

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        searchItemAdapter = new SearchItemAdapter(cosmetics, this);
        // RecyclerView Manager를 LinearLayout으로 설정
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // RecyclerView Adapter 설정
        searchRecyclerView.setAdapter(searchItemAdapter);

        // 검색 취소 액션 리스너 등록
        searchCancel.setOnClickListener(goBackPage);

        //edittext 입력변화 이벤트 처리
        searchEditText.addTextChangedListener(textWatcher);
    }

    private void init() {
        searchRecyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
        searchCancel = (TextView) findViewById(R.id.search_cancel);
        searchEditText = (EditText) findViewById(R.id.search_edittext);
    }

    View.OnClickListener goBackPage = new View.OnClickListener() {
        public void onClick(View v) {
            SearchActivity.this.finish();
            overridePendingTransition(R.anim.anim_slide_out_left, R.anim.anim_slide_in_right);
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // 입력하기 전에
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //입력되고 있을 때
            value = searchEditText.getText().toString();
            Log.d("태그", value);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 입력이 끝났을 때
        }
    };

}