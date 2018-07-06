package com.skeleton.mvp.ui.onboarding.otpverification;


import com.skeleton.mvp.ui.base.BaseView;

/**
 * Developer: Geetanjali Gupta
 */
interface OTPView : BaseView {

    /**
     * On Verification Complete
     *
     * @param message Success Message
     */
    fun onOtpVerificationSuccessful(message: String);

    /**
     * On Verification Complete
     *
     * @param message Success Message
     */
    fun onResendOtpSuccessful(message: String);
}
