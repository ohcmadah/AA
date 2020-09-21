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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Calculator;
import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.ItemClickListener;
import com.ninecm.aa.MainViewModel;
import com.ninecm.aa.R;
import com.ninecm.aa.adapter.TimeItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class TimeFragment extends Fragment implements ViewModelStoreOwner {
    private RecyclerView timeRecyclerView;
    private TimeItemAdapter timeItemAdapter;
    private LinearLayout emergContainer;
    private TextView emergTitle;
    private TextView emergDday;
    private Activity mainActivity;

    private MainViewModel viewModel;

    private List<Cosmetic> cosmeticList = new ArrayList<>();

    public TimeFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_fragment, container, false);

        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(mainActivity.getApplication()))
                .get(MainViewModel.class);

        init(view);

        emergTitle.setText("제품을 추가해주세요.");

        // RecyclerView Adapter 생성 및 Cosmetic List 전달
        timeItemAdapter = new TimeItemAdapter(mainActivity);
        // RecyclerView Manager를 LinearLayout으로 설정
        timeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // RecyclerView Adapter 설정
        timeRecyclerView.setAdapter(timeItemAdapter);

        // 데이터베이스의 상태가 변경되면 자동으로 실행
        // 데이터베이스에서 Cosmetic List를 받아 Emergency 계산 후 Adapter에 전달함
        viewModel.getAll().observe(this, dbCosmetics -> {
            calEmergency(dbCosmetics);
            timeItemAdapter.setCosmetics(cosmeticList);
        });

        return view;
    }

    public void init(View view) {
        timeRecyclerView = (RecyclerView) view.findViewById(R.id.time_recyclerview);
        emergContainer = (LinearLayout) view.findViewById(R.id.emerg_container);
        emergTitle = (TextView) view.findViewById(R.id.emerg_title);
        emergDday = (TextView) view.findViewById(R.id.emerg_dday);
    }

    public void calEmergency(List<Cosmetic> cosmetics) {
        cosmeticList = cosmetics;
        /* emergency 계산 */
        int emergIndex = Calculator.getEmergIndex(cosmeticList);

        if (emergIndex != -1) {
            String title = cosmeticList.get(emergIndex).getTitle();

            // Calculator의 Calendar 생성
            Calculator calculator = Calculator.setCalculator(cosmeticList, emergIndex);

            // D-Day 계산
            int dCount = calculator.calDday();
            String dDay = calculator.stringDday(dCount);

            // emergency setting
            emergTitle.setText(title);
            emergDday.setText(dDay);

            int emergeId = cosmeticList.get(emergIndex).getId();

            // emergency 삭제
            cosmeticList.remove(emergIndex);

            emergContainer.setVisibility(View.VISIBLE);

            // Emergency Card 클릭 Action Event
            emergContainer.setOnClickListener(new ItemClickListener(mainActivity, emergeId));
        }
    }
}
