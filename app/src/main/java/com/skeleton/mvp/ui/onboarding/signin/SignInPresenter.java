package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Developer: Click Labs
 */

public interface SignInPresenter extends BasePresenter {

    /**
     * On SignIn clicked
     *
     * @param phoneNumber the provided email
     */
    void onSignInClicked(final String phoneNumber);

    /**
     * On sign in success.
     *
     * @param commonResponse the common response
     */
    void onSignInSuccess(CommonResponse commonResponse);
}
