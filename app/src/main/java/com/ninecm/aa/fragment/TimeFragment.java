package com.ninecm.aa.fragment;

import android.app.Activity;
import android.content.Context;
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
import androidx.room.Room;

import com.ninecm.aa.AppDatabase;
import com.ninecm.aa.Calculator;
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

        Context context = getContext();
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "cosmetic-db")
                .allowMainThreadQueries()
                .build();

        init(view);

        // db.cosmeticDao().insert(new Cosmetic("에뛰드 스킨", "20200823", 2, "없음"));

        cosmetics = new ArrayList<>();
        for (int i = 0; i < db.cosmeticDao().getAll().size(); i++) {
            String dbTitle = db.cosmeticDao().getAll().get(i).getTitle();
            String dbEndDay = db.cosmeticDao().getAll().get(i).getEndDay();
            int dbStar = db.cosmeticDao().getAll().get(i).getStar();
            String dbMemo = db.cosmeticDao().getAll().get(i).getMemo();
            Cosmetic cosmetic = new Cosmetic(dbTitle, dbEndDay, dbStar, dbMemo);
            cosmetics.add(cosmetic);
        }

        calEmergency();

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        timeItemAdapter = new TimeItemAdapter(cosmetics, mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        timeRecyclerView.setAdapter(timeItemAdapter);

        emergContainer.setOnClickListener(new ItemClickListener(mainActivity, 1));

        return view;
    }

    public void init(View view) {
        timeRecyclerView = (RecyclerView) view.findViewById(R.id.time_recyclerview);
        emergContainer = (LinearLayout) view.findViewById(R.id.emerg_container);
        emergTitle = (TextView) view.findViewById(R.id.emerg_title);
        emergDday = (TextView) view.findViewById(R.id.emerg_dday);
    }

    public void calEmergency() {
        /* emergency 계산 */
        int emergIndex = Calculator.getEmergIndex(cosmetics);
        String title = cosmetics.get(emergIndex).getTitle();
        // Calculator의 Calendar 생성
        Calculator calculator = Calculator.setCalculator(cosmetics, emergIndex);
        // D-Day 계산
        int dCount = calculator.calDday();
        String dDay = calculator.stringDday(dCount);
        // emergency setting
        emergTitle.setText(title);
        emergDday.setText(dDay);
        // emergency 삭제
        cosmetics.remove(emergIndex);
    }
}
