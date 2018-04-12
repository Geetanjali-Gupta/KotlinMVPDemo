package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.ui.base.locationbase.BaseLocationView;


/**
 * Developer: Geetanjali Gupta
 */
public interface SignUpView extends BaseLocationView {
    /**
     * Show email error.
     *
     * @param resId the res id
     */
    void showEmailError(int resId);

    /**
     * Show phone number error.
     *
     * @param resId the res id
     */
    void showPhoneNumberError(int resId);


    /**
     * On sign up success.
     *
     * @param message the message
     */
    void onSignUpSuccess(String message);

}
