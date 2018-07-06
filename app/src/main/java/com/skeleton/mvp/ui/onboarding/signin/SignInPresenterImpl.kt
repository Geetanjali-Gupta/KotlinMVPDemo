package com.skeleton.mvp.ui.onboarding.signin;


import com.skeleton.mvp.R
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel
import com.skeleton.mvp.data.network.ApiError
import com.skeleton.mvp.ui.base.BasePresenterImpl
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManager
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManagerImpl
import com.skeleton.mvp.util.ValidationUtil

/**
 * Developer: Click Labs
 */
class SignInPresenterImpl(signInView: SignInView, mDataManager: OnBoardingDataManagerImpl) : BasePresenterImpl(), SignInPresenter {

    val mSignInView: SignInView
    val mDataManager: OnBoardingDataManager

    /**
     * Constructor
     *
     * @param signInView   the associated SignIn view
     * @param mDataManager the m data manager
     */
    init {
        mSignInView = signInView;
        this.mDataManager = mDataManager;

    }

    override fun onSignInClicked(phoneNumber: String) {
        // checking for validation
        if (!ValidationUtil.checkPhoneNumber(phoneNumber)) {
            mSignInView.showPhoneNumberError(R.string.error_invalid_phone_number)
            return;
        }
        mSignInView.showLoading()
        mDataManager.apiCallForLogin(phoneNumber, object : BaseApiHelper.ApiListener {
            override fun onSuccess(commonResponse: CommonResponse) {
                onSignInSuccess(commonResponse);
            }

            override fun onFailure(apiError: ApiError?, throwable: Throwable?) {
                if (isViewAttached()) {
                    mSignInView.hideLoading();
                    if (apiError != null) {
                        mSignInView.showErrorMessage(apiError.getMessage());
                    } else {
                        // resolve error through throwable
                        mSignInView.showErrorMessage(parseThrowableMessage(throwable));

                    }
                }
            }

        })
    }


    override fun onSignInSuccess(commonResponse: CommonResponse) {
        if (isViewAttached()) {
            mSignInView.hideLoading();
            val signInResponseModel: SignInResponseModel = commonResponse.toResponseModel(SignInResponseModel::class.java);
            mDataManager.saveUserData(signInResponseModel);
            mDataManager.saveAccessToken(signInResponseModel.getToken());
            mSignInView.onSignInSuccess(commonResponse.getMessage());
        }
    }

    override fun onBackPress() {
        mSignInView.onBackPress();
    }
}
