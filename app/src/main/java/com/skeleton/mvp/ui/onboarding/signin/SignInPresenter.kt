package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.ui.base.BasePresenter

/**
 * Developer: Click Labs
 */

interface SignInPresenter : BasePresenter {

    /**
     * On SignIn clicked
     *
     * @param phoneNumber the provided email
     */
    fun onSignInClicked(phoneNumber: String)

    /**
     * On sign in success.
     *
     * @param commonResponse the common response
     */
    fun onSignInSuccess(commonResponse: CommonResponse)

    /**
     * Handle On Back Press
     */
    fun onBackPress();
}
