package com.skeleton.mvp.ui.onboarding.landing;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Created by clicklabs on 03/04/18.
 */

public class LandingPresenterImpl extends BasePresenterImpl implements LandingPresenter {
    private LandingView mLandingView;
    private DataManager mDataManager;

    /**
     * Instantiates a new Login presenter.
     *
     * @param mLandingView the m landing view
     * @param mDataManager the m data manager
     */
    LandingPresenterImpl(final LandingView mLandingView, final DataManagerImpl mDataManager) {
        this.mLandingView = mLandingView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void onSignInClicked() {
        mLandingView.navigateToSignInScreen();
    }

    @Override
    public void onSignUpClicked() {
        mLandingView.navigateToSignUpScreen();
    }
}
