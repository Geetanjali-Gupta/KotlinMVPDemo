package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.ui.base.BasePresenter;


/**
 * Developer: Geetanjali Gupta
 */
public interface OTPVerificationPresenter extends BasePresenter {

    /**
     * Used to handle Continue Button Click
     *
     * @param otp OTP entered by user
     */
    void onContinueBtnClick(final String otp);

    /**
     * Used to handle Resend Button Click
     */
    void onResendBtnClick();

    /**
     * Handle On Back Press
     */
    void onBackPress();
}
