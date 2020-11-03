package com.ninecm.aa.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.ItemClickListener;
import com.ninecm.aa.R;

import java.util.ArrayList;
import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private List<Cosmetic> cosmetics = new ArrayList<>();
    private Activity searchActivity;

    class SearchItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout searchItemContainer;
        TextView searchTitle;

        // Constructor
        public SearchItemViewHolder(View v) {
            super(v);
            searchItemContainer = (LinearLayout) v.findViewById(R.id.search_item_container);
            searchTitle = (TextView) v.findViewById(R.id.search_title);
        }
    }

    // Constructor
    public SearchItemAdapter(List<Cosmetic> cosmetics, Activity searchActivity) {
        this.cosmetics = cosmetics;
        this.searchActivity = searchActivity;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) { // onClick event
        holder.searchItemContainer.setOnClickListener(new ItemClickListener(searchActivity, cosmetics.get(position).getId(), cosmetics.get(position).getStar()));
        holder.searchTitle.setText(cosmetics.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return cosmetics.size();
    }

    public void setCosmetics(List<Cosmetic> cosmetics) {
        this.cosmetics.clear();
        this.cosmetics = cosmetics;
        this.notifyDataSetChanged();
    }
}
