package com.skeleton.mvp.ui.onboarding.landing;

import com.skeleton.mvp.ui.base.BaseView;


/**
 * Developer: Geetanjali Gupta
 */
public interface LandingView extends BaseView {

    /**
     * Navigate to login screen.
     */
    void navigateToSignInScreen();

    /**
     * Navigate to sign up screen.
     */
    void navigateToSignUpScreen();

}
