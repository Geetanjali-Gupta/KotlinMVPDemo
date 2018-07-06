package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.ui.base.locationbase.BaseLocationView;


/**
 * Developer: Geetanjali Gupta
 */
interface SignUpView : BaseLocationView {
    /**
     * Show email error.
     *
     * @param resId the res id
     */
    fun showEmailError(resId: Int);

    /**
     * Show phone number error.
     *
     * @param resId the res id
     */
    fun showPhoneNumberError(resId: Int);


    /**
     * On sign up success.
     *
     * @param message the message
     */
    fun onSignUpSuccess(message: String);

}
