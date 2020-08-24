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
import com.ninecm.aa.adapter.TimeItemAdapter;

import java.util.ArrayList;

public class TimeFragment extends Fragment {
    private RecyclerView timeRecyclerView;
    private TimeItemAdapter timeItemAdapter;
    private ArrayList<Cosmetic> cosmetics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_fragment, container, false);

        timeRecyclerView = (RecyclerView) view.findViewById(R.id.time_recyclerview);

        Cosmetic cosmetic = new Cosmetic("에뛰드 스킨", "20200826", "20200823", "없음", 2);
        cosmetics = new ArrayList<>();
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);
        cosmetics.add(cosmetic);

        timeItemAdapter = new TimeItemAdapter(cosmetics);
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        timeRecyclerView.setAdapter(timeItemAdapter);

        return view;
    }
}
