package com.ninecm.aa.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.ItemClickListener;
import com.ninecm.aa.R;
import com.ninecm.aa.adapter.TimeItemAdapter;

import java.util.ArrayList;

public class TimeFragment extends Fragment {
    private RecyclerView timeRecyclerView;
    private TimeItemAdapter timeItemAdapter;
    private LinearLayout emergContainer;
    private TextView emergTitle;
    private TextView emergDday;

    private ArrayList<Cosmetic> cosmetics;
    private Activity mainActivity;

    public TimeFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_fragment, container, false);

        timeRecyclerView = (RecyclerView) view.findViewById(R.id.time_recyclerview);
        emergContainer = (LinearLayout) view.findViewById(R.id.emerg_container);
        emergTitle = (TextView) view.findViewById(R.id.emerg_title);
        emergDday = (TextView) view.findViewById(R.id.emerg_dday);

        // 임의로 물품 생성 (나중엔 DB와 연결해 그 값으로 생성)
        Cosmetic cosmetic = new Cosmetic(1, "에뛰드 스킨", "20200823", "없음", 2);
        Cosmetic cosmetic2 = new Cosmetic(2, "아큐브 투명 렌즈", "20200823", "없음", 3);
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic2);

        // emergency 계산

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        timeItemAdapter = new TimeItemAdapter(cosmetics, mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        timeRecyclerView.setAdapter(timeItemAdapter);

        emergContainer.setOnClickListener(new ItemClickListener(mainActivity, 1));

        return view;
    }
}
