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
    void onOtpVerificationSuccess(final String message);
}
