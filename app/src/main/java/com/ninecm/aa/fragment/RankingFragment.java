package com.ninecm.aa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

    private boolean isThreePressed;
    private boolean isTwoPressed;
    private boolean isOnePressed;
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
        // ImageButton 배경, flag 설정
        setButtonStyle(3, true);
        setButtonStyle(2, false);
        setButtonStyle(1, false);

        // 버튼 액션 리스너 추가
        btnThreeStar.setOnTouchListener(goThreeStarMenu);
        btnTwoStar.setOnTouchListener(goTwoStarMenu);
        btnOneStar.setOnTouchListener(goOneStarMenu);

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

    private void setButtonStyle(int btnStr, boolean flag) {
        if (btnStr == 3) {
            isThreePressed = flag;
            if (isThreePressed) {
                btnThreeStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_three_star));
            } else {
                btnThreeStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_empty_three_star));
            }
        } else if (btnStr == 2) {
            isTwoPressed = flag;
            if (isTwoPressed) {
                btnTwoStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_two_star));
            } else {
                btnTwoStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_empty_two_star));
            }
        } else {
            isOnePressed = flag;
            if (isOnePressed) {
                btnOneStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_one_star));
            } else {
                btnOneStar.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.btn_empty_one_star));
            }
        }
    }

    View.OnTouchListener goThreeStarMenu = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (!isThreePressed) setButtonStyle(3, !isThreePressed);
                if (isTwoPressed) setButtonStyle(2, !isTwoPressed);
                if (isOnePressed) setButtonStyle(1, !isOnePressed);
            }
            return true;
        }
    };

    View.OnTouchListener goTwoStarMenu = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (!isTwoPressed) setButtonStyle(2, !isTwoPressed);
                if (isThreePressed) setButtonStyle(3, !isThreePressed);
                if (isOnePressed) setButtonStyle(1, !isOnePressed);
            }
            return true;
        }
    };

    View.OnTouchListener goOneStarMenu = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (!isOnePressed) setButtonStyle(1, !isOnePressed);
                if (isThreePressed) setButtonStyle(3, !isThreePressed);
                if (isTwoPressed) setButtonStyle(2, !isTwoPressed);
            }
            return true;
        }
    };
}
