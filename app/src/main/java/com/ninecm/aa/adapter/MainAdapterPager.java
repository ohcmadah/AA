package com.ninecm.aa.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ninecm.aa.fragment.RankingFragment;
import com.ninecm.aa.fragment.TimeFragment;

public class MainAdapterPager extends FragmentStatePagerAdapter {
    private int count;

    private String[] tabTitles = new String[]{"TIME", "RANKING"};

    public MainAdapterPager(@NonNull FragmentManager fm, int count) {
        super(fm, count);
        this.count = count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TimeFragment timeFragment = new TimeFragment();
                return timeFragment;
            case 1:
                RankingFragment rankingFragment = new RankingFragment();
                return rankingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
