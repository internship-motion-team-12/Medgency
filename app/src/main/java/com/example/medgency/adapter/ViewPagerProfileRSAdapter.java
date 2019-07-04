package com.example.medgency.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerProfileRSAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitle = new ArrayList<>();

    public ViewPagerProfileRSAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return FragmentListTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitle.get(position);
    }

    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        FragmentListTitle.add(Title);
    }
}
