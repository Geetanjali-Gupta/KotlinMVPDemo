package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.R
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.data.network.ApiError
import com.skeleton.mvp.ui.base.BasePresenterImpl
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManager
import com.skeleton.mvp.util.AppConstant.OTP_LENGTH


/**
 * Developer: Geetanjali Gupta
 */
class OTPVerificationPresenterImpl(val mOTPView: OTPView, val mDataManager: OnBoardingDataManager) : BasePresenterImpl(), OTPVerificationPresenter {


    override fun onContinueBtnClick(mobileNumber: String, otp: String) {
        if (otp.isEmpty()) {
            mOTPView.showErrorMessage(R.string.error_please_enter_otp);
            return;
        }
        if (otp.length < OTP_LENGTH) {
            mOTPView.showErrorMessage(R.string.error_please_enter_four_digit_otp);
            return;
        }
        mOTPView.showLoading()
        mDataManager.apiCallToVerifyOtp(mobileNumber, otp, object : BaseApiHelper.ApiListener {
            override fun onSuccess(commonResponse: CommonResponse) {
                onOtpVerificationSuccess(commonResponse);
            }

            override fun onFailure(apiError: ApiError?, throwable: Throwable?) {
                if (isViewAttached) {
                    mOTPView.hideLoading();
                    if (apiError != null) {
                        mOTPView.showErrorMessage(apiError.message);
                    } else {
                        mOTPView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        })
    }

    override fun onResendBtnClick(mobileNumber: String) {
        mOTPView.showLoading();
        mDataManager.apiCallToResendOtp(mobileNumber, object : BaseApiHelper.ApiListener {
            override fun onSuccess(commonResponse: CommonResponse) {
                onResendOtpSuccess(commonResponse);
            }

            override fun onFailure(apiError: ApiError?, throwable: Throwable?) {
                if (isViewAttached) {
                    mOTPView.hideLoading();
                    if (apiError != null) {
                        mOTPView.showErrorMessage(apiError.message);
                    } else {
                        mOTPView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        });
    }

    override fun onOtpVerificationSuccess(commonResponse: CommonResponse) {
        if (isViewAttached) {
            mOTPView.hideLoading();
            mOTPView.onOtpVerificationSuccessful(commonResponse.getMessage());
        }
    }

    override fun onResendOtpSuccess(commonResponse: CommonResponse) {
        if (isViewAttached) {
            mOTPView.hideLoading();
            mOTPView.onResendOtpSuccessful(commonResponse.getMessage());
        }
    }

    override fun onBackPress() {
        mOTPView.onBackPress();
    }

    override fun onBackExpireSession() {
        mDataManager.clearSessionManager();
    }
}
