package com.skeleton.mvp.ui.onboarding.landing;

import com.skeleton.mvp.ui.base.BaseView;

/**
 * Created by clicklabs on 28/03/18.
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
