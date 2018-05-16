package com.skeleton.mvp.ui.home;

import android.support.v4.app.Fragment;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Created by clicklabs on 23/04/18.
 */

public class HomePresenterImpl extends BasePresenterImpl implements HomePresenter {
    private HomeView mHomeView;
    private DataManager mDataManager;

    /**
     * Instantiates a new Home presenter.
     *
     * @param mHomeView    the m home view
     * @param mDataManager the m data manager
     */
    HomePresenterImpl(final HomeView mHomeView, final DataManagerImpl mDataManager) {
        this.mHomeView = mHomeView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void onHomeTabClick(final Fragment intendedFragment, final String tag) {
        mHomeView.changeFragment(intendedFragment, tag);
    }

    @Override
    public void onPlansTabClick(final Fragment intendedFragment, final String tag) {
        mHomeView.changeFragment(intendedFragment, tag);
    }

    @Override
    public void onCartTabClick(final Fragment intendedFragment, final String tag) {
        mHomeView.changeFragment(intendedFragment, tag);
    }

    @Override
    public void onAccountsTabClick(final Fragment intendedFragment, final String tag) {
        mHomeView.changeFragment(intendedFragment, tag);
    }

    @Override
    public void onNotificationClick() {

    }
}
