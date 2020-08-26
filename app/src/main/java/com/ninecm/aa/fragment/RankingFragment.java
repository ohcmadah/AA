package com.ninecm.aa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private RecyclerView rankingRecyclerView;
    private RankingItemAdapter rankingItemAdapter;
    private ArrayList<Cosmetic> cosmetics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);

        // RecyclerView를 가져옴
        rankingRecyclerView = (RecyclerView) view.findViewById(R.id.ranking_recyclerview);

        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200826", "20200823", "없음", 2);
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        rankingItemAdapter = new RankingItemAdapter(cosmetics);
        // RecyclerView Manager를 LinearLayout으로 설정
        rankingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        rankingRecyclerView.setAdapter(rankingItemAdapter);

        return view;
    }
}
