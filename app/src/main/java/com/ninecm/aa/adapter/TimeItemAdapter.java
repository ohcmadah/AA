package com.ninecm.aa.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Calculator;
import com.ninecm.aa.Costemic;
import com.ninecm.aa.R;

import java.util.ArrayList;

public class TimeItemAdapter extends RecyclerView.Adapter<TimeItemAdapter.TimeItemViewHolder> {

    private ArrayList<Costemic> costemics;

    public static class TimeItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout timeItem;
        View divider;
        TextView timeTitle;
        TextView timeDday;

        public TimeItemViewHolder(View v) {
            super(v);
            timeItem = (LinearLayout) v.findViewById(R.id.time_item_container);
            divider = (View) v.findViewById(R.id.divider);
            timeTitle = (TextView) v.findViewById(R.id.time_title);
            timeDday = (TextView) v.findViewById(R.id.time_dday);
        }
    }

    public TimeItemAdapter(ArrayList<Costemic> costemics) {
        this.costemics = costemics;
    }

    @NonNull
    @Override
    public TimeItemAdapter.TimeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.time_item, viewGroup, false);
        return new TimeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeItemViewHolder holder, int position) {
        if (position == 0) {
            holder.timeItem.setBackgroundResource(R.drawable.round_corner_top);
        }

        if (position == costemics.size() - 1) {
            holder.timeItem.setBackgroundResource(R.drawable.round_corner_bottom);
            holder.divider.setVisibility(View.GONE);
        }

        int year = Calculator.getYear(costemics.get(position).getEndDay());
        int month = Calculator.getMonth(costemics.get(position).getEndDay());
        int day = Calculator.getDay(costemics.get(position).getEndDay());
        Calculator calculator = new Calculator(year, month, day);

        String dDay = "D - " + String.valueOf(calculator.calDday());
        String title = costemics.get(position).getTitle();

        holder.timeTitle.setText(title);
        holder.timeDday.setText(dDay);
    }

    @Override
    public int getItemCount() {
        return costemics.size();
    }

}
