package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.ui.base.BaseView


/**
 * Developer: Click Labs
 */
interface SignInView : BaseView {

    /**
     * On sign in success.
     *
     * @param message the message
     */
    fun onSignInSuccess(message: String)

    /**
     * Show phone number error.
     *
     * @param resId the res id
     */
    fun showPhoneNumberError(resId: Int)
}
