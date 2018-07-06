package com.skeleton.mvp.ui.onboarding.landing

import com.skeleton.mvp.ui.base.BasePresenter

/**
 * Created by clicklabs on 05/07/18.
 */

interface LandingPresenter : BasePresenter {

    /**
     * Handle Sign In Button Click
     */
    fun onSignInClicked()

    /**
     * Handle Sign In Button Click
     */
    fun onSignUpClicked()
}