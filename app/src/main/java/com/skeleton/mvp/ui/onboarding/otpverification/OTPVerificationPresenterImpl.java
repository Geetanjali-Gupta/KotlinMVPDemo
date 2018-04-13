package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

import static com.skeleton.mvp.util.AppConstant.OTP_LENGTH;


/**
 * Developer: Geetanjali Gupta
 */
public class OTPVerificationPresenterImpl extends BasePresenterImpl implements OTPVerificationPresenter {

    private OTPView mOTPView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param mOTPView     the associated splash view
     * @param mDataManager the m data manager
     */
    OTPVerificationPresenterImpl(final OTPView mOTPView, final DataManagerImpl mDataManager) {
        this.mOTPView = mOTPView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void onContinueBtnClick(final String mobileNumber, final String otp) {
        int length;
        if (otp.isEmpty()) {
            mOTPView.showErrorMessage(R.string.error_please_enter_otp);
        } else if (otp.length() < OTP_LENGTH) {
            mOTPView.showErrorMessage(R.string.error_please_enter_four_digit_otp);
        } else {
            mOTPView.showLoading();
            mDataManager.apiCallToVerifyOtp(mobileNumber, otp, new ApiHelper.ApiListener() {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {
                    mOTPView.hideLoading();
                    onOtpVerificationSuccess(commonResponse);
                }

                @Override
                public void onFailure(final ApiError apiError, final Throwable throwable) {
                    if (isViewAttached()) {
                        mOTPView.hideLoading();
                        if (apiError != null) {
                            mOTPView.showErrorMessage(apiError.getMessage());
                        } else {
                            // resolve error through throwable
                            mOTPView.showErrorMessage(parseThrowableMessage(throwable));

                        }
                    }
                }
            });
        }
    }

    @Override
    public void onResendBtnClick(final String mobileNumber) {
        mDataManager.apiCallToResendOtp(mobileNumber, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {

            }
        });
    }

    @Override
    public void onOtpVerificationSuccess(final CommonResponse commonResponse) {
        if (isViewAttached()) {
            //  mDataManager.saveUserData(commonResponse.toResponseModel(UserData.class));
            mOTPView.onOtpVerificationSuccess(commonResponse.getMessage());
        }
    }

    @Override
    public void onBackPress() {
        mOTPView.onBackPress();
    }
}
