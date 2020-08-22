package com.ninecm.aa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.R;

import java.util.List;

public class TimeItemAdapter extends RecyclerView.Adapter<TimeItemAdapter.ItemViewHolder> {

    private List<String> list;

    public TimeItemAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.time_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (position == 0) {
            holder.timeItem.setBackgroundResource(R.drawable.round_corner_top);
        }

        if (position == list.size() - 1) {
            holder.timeItem.setBackgroundResource(R.drawable.round_corner_bottom);
            holder.divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout timeItem;
        View divider;

        public ItemViewHolder(View itemView) {
            super(itemView);
            timeItem = itemView.findViewById(R.id.time_item_container);
            divider = itemView.findViewById(R.id.divider);
        }
    }

}
