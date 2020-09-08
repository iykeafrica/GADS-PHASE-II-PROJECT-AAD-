package com.example.gadsleaderboard.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gadsleaderboard.fragments.LearningLeadersFragment;
import com.example.gadsleaderboard.fragments.SkillIQLeadersFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new LearningLeadersFragment();
        } else if (position == 1){
            fragment = new SkillIQLeadersFragment();
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = null;
        if (position == 0){
            title = "Learning Leaders";
        } else if (position == 1){
            title = "Skill IQ Leaders";
        }
        return title;
    }
}
