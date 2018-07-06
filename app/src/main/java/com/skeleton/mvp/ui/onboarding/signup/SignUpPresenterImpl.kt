package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.R
import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.data.model.responsemodel.onboarding.signup.SignUpResponseModel
import com.skeleton.mvp.data.network.ApiError
import com.skeleton.mvp.ui.base.BasePresenterImpl
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManager
import com.skeleton.mvp.util.ValidationUtil

/**
 * Developer: Geetanjali Gupta
 */
class SignUpPresenterImpl(val mSignUpView: SignUpView, val mDataManager: OnBoardingDataManager) : BasePresenterImpl(), SignUpPresenter {


    override fun onSignUpClicked(signUpModel: SignUpModel) {
        if (!ValidationUtil.checkPhoneNumber(signUpModel.getMobile())) {
            mSignUpView.showPhoneNumberError(R.string.error_invalid_phone_number);
            return;
        }
        if (!ValidationUtil.checkEmail(signUpModel.getEmail())) {
            mSignUpView.showEmailError(R.string.error_invalid_email);
            return;
        }
        mSignUpView.showLoading();
        mDataManager.apiCallToRegisterUser(signUpModel, object : BaseApiHelper.ApiListener {

            override fun onSuccess(commonResponse: CommonResponse) {
                onSignUpSuccess(commonResponse);
            }

            override fun onFailure(apiError: ApiError?, throwable: Throwable?) {
                if (isViewAttached()) {
                    mSignUpView.hideLoading();
                    if (apiError != null) {
                        mSignUpView.showErrorMessage(apiError.getMessage());
                    } else {
                        mSignUpView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        })
    }

    override fun onSignUpSuccess(commonResponse: CommonResponse) {
        if (isViewAttached()) {
            mSignUpView.hideLoading();
            val signUpResponseModel: SignUpResponseModel = commonResponse.toResponseModel(SignUpResponseModel::class.java);
            mDataManager.saveAccessToken(signUpResponseModel.getToken());
            mSignUpView.onSignUpSuccess(commonResponse.getMessage());
        }
    }

    override fun onBackPress() {
        mSignUpView.onBackPress()
    }

}
