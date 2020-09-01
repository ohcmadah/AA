package com.ninecm.aa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.R;
import com.ninecm.aa.adapter.RankingItemAdapter;

import java.util.ArrayList;

public class RankingFragment extends Fragment {

    private Activity mainActivity;
    private RecyclerView rankingRecyclerView;
    private RankingItemAdapter rankingItemAdapter;
    private ImageButton btnThreeStar;
    private ImageButton btnTwoStar;
    private ImageButton btnOneStar;

    private ArrayList<Cosmetic> cosmetics;

    public RankingFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);

        // init
        init(view);

        // set up
        setUp();

        return view;
    }

    private void init(View view) {
        rankingRecyclerView = (RecyclerView) view.findViewById(R.id.ranking_recyclerview);
        btnThreeStar = (ImageButton) view.findViewById(R.id.btn_three_star);
        btnTwoStar = (ImageButton) view.findViewById(R.id.btn_two_star);
        btnOneStar = (ImageButton) view.findViewById(R.id.btn_one_star);
    }

    private void setUp() {
        // 기본 메뉴로 설정(눌려짐)
        btnThreeStar.setPressed(true);

        // 버튼 액션 리스너 추가
        btnThreeStar.setOnClickListener(goThreeStarMenu);
        btnTwoStar.setOnClickListener(goTwoStarMenu);
        btnOneStar.setOnClickListener(goOneStarMenu);

        // 별 2, 3개 메뉴 누를 때 1개 메뉴 pressed 해제
        btnTwoStar.setOnTouchListener(setThreeStarPressed);
        btnOneStar.setOnTouchListener(setThreeStarPressed);

        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200826", "20200823", "없음", 2);
        Cosmetic cosmetic2 = new Cosmetic("아큐브 투명 렌즈", "20200926", "20200823", "없음", 3);
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic2);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic2);
        cosmetics.add(cosmetic);

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        rankingItemAdapter = new RankingItemAdapter(cosmetics, mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        rankingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        rankingRecyclerView.setAdapter(rankingItemAdapter);
    }

    View.OnTouchListener setThreeStarPressed = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) return false;

            if (event.getAction() != MotionEvent.ACTION_UP) return true;

            btnThreeStar.setPressed(false);
            return false;
        }
    };

    View.OnClickListener goThreeStarMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    View.OnClickListener goTwoStarMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    View.OnClickListener goOneStarMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
