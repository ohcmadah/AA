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

import com.ninecm.aa.Costemic;
import com.ninecm.aa.R;
import com.ninecm.aa.adapter.TimeItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class TimeFragment extends Fragment {
    private RecyclerView timeRecyclerView;
    private TimeItemAdapter timeItemAdapter;
    private ArrayList<Costemic> costemics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_fragment, container, false);

        timeRecyclerView = (RecyclerView) view.findViewById(R.id.time_recyclerview);

        Costemic costemic = new Costemic("에뛰드 스킨", "2020년 8월 26일 (수)", "2020년 8월 23일 (일)", "없음", 2);
        costemics = new ArrayList<>();
        costemics.add(costemic);
        costemics.add(costemic);
        costemics.add(costemic);

        timeItemAdapter = new TimeItemAdapter(costemics);
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        timeRecyclerView.setAdapter(timeItemAdapter);

        return view;
    }
}
