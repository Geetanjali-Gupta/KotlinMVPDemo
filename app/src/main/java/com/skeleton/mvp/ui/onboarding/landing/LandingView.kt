package com.skeleton.mvp.ui.onboarding.landing;

import com.skeleton.mvp.ui.base.BaseView;


/**
 * Developer: Geetanjali Gupta
 */
interface LandingView : BaseView {

    /**
     * Navigate to login screen.
     */
    fun navigateToSignInScreen()

    /**
     * Navigate to sign up screen.
     */
    fun navigateToSignUpScreen()

    /**
     * Navigate to home screen.
     */
    fun navigateToHomeScreen()

}
