package com.skeleton.mvp.ui.onboarding.otpverification;


import com.skeleton.mvp.ui.base.BaseView;

/**
 * Developer: Geetanjali Gupta
 */
public interface OTPView extends BaseView {

    /**
     * On Verification Complete
     *
     * @param message Success Message
     */
    void onOtpVerificationSuccessful(final String message);

    /**
     * On Verification Complete
     *
     * @param message Success Message
     */
    void onResendOtpSuccessful(final String message);
}
