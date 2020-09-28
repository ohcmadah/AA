package com.ninecm.aa.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ninecm.aa.Cosmetic;
import com.ninecm.aa.ItemClickListener;
import com.ninecm.aa.R;

import java.util.ArrayList;
import java.util.List;

public class RankingItemAdapter extends RecyclerView.Adapter<RankingItemAdapter.RankingItemViewHolder> {

    private List<Cosmetic> cosmetics = new ArrayList<>();
    private Activity mainActivity;

    public static class RankingItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rankingItemContainer;
        View rankingDivider;
        TextView rankingTitle;

        // Constructor
        public RankingItemViewHolder(View v) {
            super(v);
            rankingItemContainer = (LinearLayout) v.findViewById(R.id.ranking_item_container);
            rankingDivider = (View) v.findViewById(R.id.ranking_divider);
            rankingTitle = (TextView) v.findViewById(R.id.ranking_title);
        }
    }

    // Constructor
    public RankingItemAdapter(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public RankingItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ranking_item, viewGroup, false);
        return new RankingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingItemViewHolder holder, int position) {
        // 첫 번째 목록일 때
        if (position == 0) {
            // 위쪽 모서리 둥근 사각형으로 배경 설정
            holder.rankingItemContainer.setBackgroundResource(R.drawable.blue_round_corner_top);
        }

        // 마지막 목록일 때
        if (position == cosmetics.size() - 1) {
            // 아래쪽 모서리 둥근 사각형으로 배경 설정
            holder.rankingItemContainer.setBackgroundResource(R.drawable.blue_round_corner_bottom);
            // divider 안 보이도록 설정
            holder.rankingDivider.setVisibility(View.GONE);
        }

        // 물품 제목 가져옴
        String title = cosmetics.get(position).getTitle();

        // 구한 D-Day, 물품 제목으로 TextView 설정
        holder.rankingTitle.setText(title);

        if (title != "제품을 추가해주세요") {
            // onClick event
            holder.rankingItemContainer.setOnClickListener(new ItemClickListener(mainActivity, cosmetics.get(position).getId()));
        }
    }

    public void setCosmetics(List<Cosmetic> cosmetics) {
        this.cosmetics.clear();
        this.cosmetics = new ArrayList<>();
        this.cosmetics = cosmetics;
        this.notifyDataSetChanged();
    }

    // 몇 개의 Item을 목록에 띄워야 하는지 갯수 반환
    @Override
    public int getItemCount() {
        return cosmetics.size();
    }

}
