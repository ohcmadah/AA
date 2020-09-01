package com.ninecm.aa.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ninecm.aa.fragment.RankingFragment;
import com.ninecm.aa.fragment.TimeFragment;

public class MainAdapterPager extends FragmentStatePagerAdapter {
    private int count;
    private Activity mainActivity;

    // 메뉴에 들어갈 String 목록
    private String[] tabTitles = new String[]{"TIME", "RANKING"};

    // Constructor
    public MainAdapterPager(@NonNull FragmentManager fm, int count, Activity mainActivity) {
        super(fm, count);
        this.count = count;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // TIME 선택 시
                TimeFragment timeFragment = new TimeFragment(mainActivity);
                return timeFragment;
            case 1: // RANKING 선택 시
                RankingFragment rankingFragment = new RankingFragment(mainActivity);
                return rankingFragment;
            default:
                return null;
        }
    }

    // 메뉴 갯수 반환
    @Override
    public int getCount() {
        return count;
    }

    // 메뉴 이름 반환
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
