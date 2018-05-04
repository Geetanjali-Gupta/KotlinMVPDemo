package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.ui.base.BasePresenter;


/**
 * Developer: Geetanjali Gupta
 */
public interface OTPVerificationPresenter extends BasePresenter {

    /**
     * Used to handle Continue Button Click
     *
     * @param mobileNumber Phone number to be verified
     * @param otp          OTP entered by user
     */
    void onContinueBtnClick(final String mobileNumber, final String otp);

    /**
     * Used to handle Resend Button Click
     *
     * @param mobileNumber Phone number to be verified
     */
    void onResendBtnClick(final String mobileNumber);

    /**
     * On Otp Verification success.
     *
     * @param commonResponse the common response
     */
    void onOtpVerificationSuccess(CommonResponse commonResponse);

    /**
     * On Otp Verification success.
     *
     * @param commonResponse the common response
     */
    void onResendOtpSuccess(CommonResponse commonResponse);

    /**
     * Handle On Back Press
     */
    void onBackPress();

    /**
     * Expire session that is clear access token On Back Press
     */
    void onBackExpireSession();
}
