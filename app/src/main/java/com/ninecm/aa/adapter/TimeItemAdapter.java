package com.ninecm.aa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Calculator;
import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.ItemClickListener;
import com.ninecm.aa.R;

import java.util.ArrayList;
import java.util.List;

public class TimeItemAdapter extends RecyclerView.Adapter<TimeItemAdapter.TimeItemViewHolder> {

    private List<Cosmetic> cosmetics = new ArrayList<>();
    private Activity mainActivity;

    public static class TimeItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout timeItemContainer;
        View divider;
        TextView timeTitle;
        TextView timeDday;

        // Constructor
        public TimeItemViewHolder(View v) {
            super(v);
            timeItemContainer = (LinearLayout) v.findViewById(R.id.time_item_container);
            divider = (View) v.findViewById(R.id.divider);
            timeTitle = (TextView) v.findViewById(R.id.time_title);
            timeDday = (TextView) v.findViewById(R.id.time_dday);
        }
    }

    // Constructor
    public TimeItemAdapter(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TimeItemAdapter.TimeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.time_item, viewGroup, false);
        return new TimeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeItemViewHolder holder, int position) {
        if (cosmetics.size() == 1) {
            // 둥근 사각형으로 배경 설정
            holder.timeItemContainer.setBackgroundResource(R.drawable.black_solid_round_rect);
            // divider 안 보이도록 설정
            holder.divider.setVisibility(View.GONE);
        } else {
            // 첫 번째 목록일 때
            if (position == 0) {
                // 윗쪽 모서리 둥근 사각형으로 배경 설정
                holder.timeItemContainer.setBackgroundResource(R.drawable.round_corner_top);
            }

            // 마지막 목록일 때
            if (position == cosmetics.size() - 1) {
                // 아래쪽 모서리 둥근 사각형으로 배경 설정
                holder.timeItemContainer.setBackgroundResource(R.drawable.round_corner_bottom);
                // divider 안 보이도록 설정
                holder.divider.setVisibility(View.GONE);
            }
        }

        // Calculator의 Calendar 생성
        Calculator calculator = Calculator.setCalculator(cosmetics, position);

        // D-Day 계산
        int dCount = calculator.calDday();
        String dDay = calculator.stringDday(dCount);
        // 물품 제목 가져옴
        String title = cosmetics.get(position).getTitle();
        int startNum = cosmetics.get(position).getStar();

        // 구한 D-Day, 물품 제목으로 TextView 설정
        holder.timeTitle.setText(title);
        holder.timeDday.setText(dDay);

        int id = cosmetics.get(position).getId();
        // onClick event
        holder.timeItemContainer.setOnClickListener(new ItemClickListener(mainActivity, id, startNum));
    }

    public void setCosmetics(List<Cosmetic> cosmetics) {
        this.cosmetics = cosmetics;
        notifyDataSetChanged();
    }

    // 몇 개의 Item을 목록에 띄워야 하는지 갯수 반환
    @Override
    public int getItemCount() {
        return cosmetics.size();
    }

}
