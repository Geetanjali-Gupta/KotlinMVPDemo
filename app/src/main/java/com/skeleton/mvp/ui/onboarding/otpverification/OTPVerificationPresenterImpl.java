package com.skeleton.mvp.ui.onboarding.otpverification;

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
    public void onContinueBtnClick(final String otp) {
        int length;
        if (otp.isEmpty()) {
            length = otp.length();
        } else if (otp.length() < OTP_LENGTH) {
            length = otp.length();
        } else {
            mDataManager.apiCallToVerifyOtp(otp, new ApiHelper.ApiListener() {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {

                }

                @Override
                public void onFailure(final ApiError apiError, final Throwable throwable) {

                }
            });
        }
    }

    @Override
    public void onResendBtnClick() {
        mDataManager.apiCallToResendOtp("345365", new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {

            }
        });
    }

    @Override
    public void onBackPress() {
        mOTPView.onBackPress();
    }
}
