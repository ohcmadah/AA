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
        // 기본 눌려진 버튼
        btnThreeStar.setSelected(true);
        // 버튼 액션 리스너 추가
        btnThreeStar.setOnTouchListener(changeStarPage);
        btnTwoStar.setOnTouchListener(changeStarPage);
        btnOneStar.setOnTouchListener(changeStarPage);

        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200823", 2, "없음");
        Cosmetic cosmetic2 = new Cosmetic("아큐브 투명 렌즈", "20200823", 3, "없음");
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic2);

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        rankingItemAdapter = new RankingItemAdapter(cosmetics, mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        rankingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        rankingRecyclerView.setAdapter(rankingItemAdapter);

        return view;
    }

    private void init(View view) {
        rankingRecyclerView = (RecyclerView) view.findViewById(R.id.ranking_recyclerview);
        btnThreeStar = (ImageButton) view.findViewById(R.id.btn_three_star);
        btnTwoStar = (ImageButton) view.findViewById(R.id.btn_two_star);
        btnOneStar = (ImageButton) view.findViewById(R.id.btn_one_star);
    }

    View.OnTouchListener changeStarPage = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                switch (view.getId()) {
                    case R.id.btn_three_star:
                        btnTwoStar.setSelected(false);
                        btnOneStar.setSelected(false);
                        btnThreeStar.setSelected(true);
                        break;

                    case R.id.btn_two_star:
                        btnThreeStar.setSelected(false);
                        btnOneStar.setSelected(false);
                        btnTwoStar.setSelected(true);
                        break;

                    case R.id.btn_one_star:
                        btnThreeStar.setSelected(false);
                        btnTwoStar.setSelected(false);
                        btnOneStar.setSelected(true);
                        break;
                }
            }

            return false;
        }
    };
}
