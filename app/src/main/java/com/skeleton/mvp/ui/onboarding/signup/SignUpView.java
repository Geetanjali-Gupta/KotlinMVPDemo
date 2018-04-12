package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.ui.base.BaseView;

/**
 * Created by clicklabs on 11/04/18.
 */

public interface SignUpView extends BaseView {
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
