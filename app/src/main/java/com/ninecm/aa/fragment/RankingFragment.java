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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.MainViewModel;
import com.ninecm.aa.R;
import com.ninecm.aa.adapter.RankingItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {

    private Activity mainActivity;
    private RecyclerView rankingRecyclerView;
    private RankingItemAdapter rankingItemAdapter;
    private ImageButton btnThreeStar;
    private ImageButton btnTwoStar;
    private ImageButton btnOneStar;

    private List<Cosmetic> cosmetics;

    private MainViewModel viewModel;

    public RankingFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);

        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(mainActivity.getApplication()))
                .get(MainViewModel.class);


        // init
        init(view);

        // set up
        // 기본 눌려진 버튼
        btnThreeStar.setSelected(true);
        // 버튼 액션 리스너 추가
        btnThreeStar.setOnTouchListener(changeStarPage);
        btnTwoStar.setOnTouchListener(changeStarPage);
        btnOneStar.setOnTouchListener(changeStarPage);

        // RecyclerView Adapter 생성
        rankingItemAdapter = new RankingItemAdapter(mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        rankingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        rankingRecyclerView.setAdapter(rankingItemAdapter);

        viewModel.getAll().observe(this, dbCosmetics -> {
            cosmetics = dbCosmetics;
            List<Cosmetic> changedCosmetics = calStarPage(dbCosmetics, 3);
            rankingItemAdapter.setCosmetics(changedCosmetics);
        });

        return view;
    }

    private void init(View view) {
        rankingRecyclerView = (RecyclerView) view.findViewById(R.id.ranking_recyclerview);
        btnThreeStar = (ImageButton) view.findViewById(R.id.btn_three_star);
        btnTwoStar = (ImageButton) view.findViewById(R.id.btn_two_star);
        btnOneStar = (ImageButton) view.findViewById(R.id.btn_one_star);
    }

    private List<Cosmetic> calStarPage(List<Cosmetic> cosmeticList, int starNum) {
        List<Cosmetic> cosmetics = new ArrayList<>();
        for (int i = 0; i < cosmeticList.size(); i++) {
            if (cosmeticList.get(i).getStar() == starNum) {
                cosmetics.add(cosmeticList.get(i));
            }
        }
        if (cosmeticList == null) {
            Cosmetic cosmetic = new Cosmetic("제품을 추가해주세요.", "", 0, "");
            cosmeticList.add(cosmetic);
        }


        return cosmeticList;
    }

    View.OnTouchListener changeStarPage = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            List<Cosmetic> changedCosmetics = new ArrayList<>();
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                switch (view.getId()) {
                    case R.id.btn_three_star:
                        btnTwoStar.setSelected(false);
                        btnOneStar.setSelected(false);
                        btnThreeStar.setSelected(true);

                        changedCosmetics = calStarPage(cosmetics, 3);
                        break;

                    case R.id.btn_two_star:
                        btnThreeStar.setSelected(false);
                        btnOneStar.setSelected(false);
                        btnTwoStar.setSelected(true);

                        changedCosmetics = calStarPage(cosmetics, 2);
                        break;

                    case R.id.btn_one_star:
                        btnThreeStar.setSelected(false);
                        btnTwoStar.setSelected(false);
                        btnOneStar.setSelected(true);

                        changedCosmetics = calStarPage(cosmetics, 1);
                        break;
                }
            }
            rankingItemAdapter.setCosmetics(changedCosmetics);

            return false;
        }
    };
}
