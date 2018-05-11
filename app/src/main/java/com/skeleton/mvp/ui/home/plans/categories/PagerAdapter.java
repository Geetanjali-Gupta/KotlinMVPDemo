package com.skeleton.mvp.ui.home.plans.categories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skeleton.mvp.ui.base.BaseFragment;

import java.util.List;

/**
 * Developer: Geetanjali Gupta
 * Dated: 11/May/2018
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragmentsList;

    /**
     * constructor with
     *
     * @param manager      support fragment manager
     * @param fragmentList list of all the fragments to be added.
     */
    public PagerAdapter(final FragmentManager manager, final List<BaseFragment> fragmentList) {
        super(manager);
        this.fragmentsList = fragmentList;
    }


    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(final int position) {
        return fragmentsList.get(position);
    }

}

