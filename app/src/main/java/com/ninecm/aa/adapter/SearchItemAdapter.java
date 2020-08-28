package com.ninecm.aa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.R;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {
    private ArrayList<Cosmetic> cosmetics;

    public static class SearchItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout searchItemContainer;
        View searchDivider;
        TextView searchTitle;

        // Constructor
        public SearchItemViewHolder(View v) {
            super(v);
            searchItemContainer = (LinearLayout) v.findViewById(R.id.ranking_item_container);
            searchDivider = (View) v.findViewById(R.id.ranking_divider);
            searchTitle = (TextView) v.findViewById(R.id.ranking_title);
        }
    }

    // Constructor
    public SearchItemAdapter(ArrayList<Cosmetic> cosmetics) {
        this.cosmetics = cosmetics;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.searchItemContainer.setBackgroundResource(R.drawable.search_item_rect);
    }

    @Override
    public int getItemCount() {
        return cosmetics.size();
    }
}
