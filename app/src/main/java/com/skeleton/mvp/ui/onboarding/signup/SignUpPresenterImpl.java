package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.model.requestmodel.SignUpModel;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.model.responsemodel.onboarding.signup.SignUpResponseModel;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.util.ValidationUtil;

/**
 * Developer: Geetanjali Gupta
 */
public class SignUpPresenterImpl extends BasePresenterImpl implements SignUpPresenter {
    private SignUpView mSignUpView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param mSignUpView  the associated sign up view
     * @param mDataManager the m data manager
     */
    SignUpPresenterImpl(final SignUpView mSignUpView, final DataManager mDataManager) {
        this.mSignUpView = mSignUpView;
        this.mDataManager = mDataManager;
    }


    @Override
    public void onSignUpClicked(final SignUpModel signUpModel) {
        if (!ValidationUtil.checkPhoneNumber(signUpModel.getMobile())) {
            mSignUpView.showPhoneNumberError(R.string.error_invalid_phone_number);
            return;
        }
        if (!ValidationUtil.checkEmail(signUpModel.getEmail())) {
            mSignUpView.showEmailError(R.string.error_invalid_email);
            return;
        }
        mSignUpView.showLoading();
        mDataManager.apiCallToRegisterUser(signUpModel, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                onSignUpSuccess(commonResponse);
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                if (isViewAttached()) {
                    mSignUpView.hideLoading();
                    if (apiError != null) {
                        mSignUpView.showErrorMessage(apiError.getMessage());
                    } else {
                        mSignUpView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        });
    }

    @Override
    public void onSignUpSuccess(final CommonResponse commonResponse) {
        if (isViewAttached()) {
            mSignUpView.hideLoading();
            SignUpResponseModel signUpResponseModel = commonResponse.toResponseModel(SignUpResponseModel.class);
            mDataManager.saveAccessToken(signUpResponseModel.getToken());
            mSignUpView.onSignUpSuccess(commonResponse.getMessage());
        }
    }

    @Override
    public void onBackPress() {
        mSignUpView.onBackPress();
    }
}
