package com.skeleton.mvp.ui.onboarding.landing

import com.skeleton.mvp.data.DataManagerImpl
import com.skeleton.mvp.ui.base.BasePresenterImpl

/**
 * Created by clicklabs on 05/07/18.
 */
class LandingPresenterImpl(val mLandingView: LandingView, val mDataManager: DataManagerImpl) : BasePresenterImpl(), LandingPresenter {

    override fun onSignInClicked() {
        mLandingView.navigateToSignInScreen()
    }

    override fun onSignUpClicked() {
        mLandingView.navigateToSignUpScreen()
    }
}