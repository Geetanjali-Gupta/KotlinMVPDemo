package com.skeleton.mvp.ui.onboarding.signin;


import com.skeleton.mvp.ui.base.BaseView;

/**
 * Developer: Click Labs
 */

public interface SignInView extends BaseView {

    /**
     * On sign in success.
     *
     * @param message the message
     */
    void onSignInSuccess(String message);
}
