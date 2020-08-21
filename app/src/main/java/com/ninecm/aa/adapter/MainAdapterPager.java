package com.ninecm.aa.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ninecm.aa.fragment.RankingFragment;
import com.ninecm.aa.fragment.TimeFragment;

public class MainAdapterPager extends FragmentStatePagerAdapter {
    public static final int COUNT = 2;

    public MainAdapterPager(@NonNull FragmentManager fm) {
        super(fm);
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
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
