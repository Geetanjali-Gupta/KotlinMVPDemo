package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.ui.base.BasePresenter;


/**
 * Developer: Geetanjali Gupta
 */
interface OTPVerificationPresenter : BasePresenter {

    /**
     * Used to handle Continue Button Click
     *
     * @param mobileNumber Phone number to be verified
     * @param otp          OTP entered by user
     */
    fun onContinueBtnClick(mobileNumber: String, otp: String);

    /**
     * Used to handle Resend Button Click
     *
     * @param mobileNumber Phone number to be verified
     */
    fun onResendBtnClick(mobileNumber: String);

    /**
     * On Otp Verification success.
     *
     * @param commonResponse the common response
     */
    fun onOtpVerificationSuccess(commonResponse: CommonResponse);

    /**
     * On Otp Verification success.
     *
     * @param commonResponse the common response
     */
    fun onResendOtpSuccess(commonResponse: CommonResponse);

    /**
     * Handle On Back Press
     */
    fun onBackPress();

    /**
     * Expire session that is clear access token On Back Press
     */
    fun onBackExpireSession();
}
