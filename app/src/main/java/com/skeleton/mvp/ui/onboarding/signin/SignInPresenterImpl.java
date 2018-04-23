package com.skeleton.mvp.ui.onboarding.signin;


import android.support.annotation.NonNull;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.util.ValidationUtil;

/**
 * Developer: Click Labs
 */

public class SignInPresenterImpl extends BasePresenterImpl implements SignInPresenter {

    private SignInView mSignInView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param signInView   the associated SignIn view
     * @param mDataManager the m data manager
     */
    SignInPresenterImpl(@NonNull final SignInView signInView, final DataManagerImpl mDataManager) {
        mSignInView = signInView;
        this.mDataManager = mDataManager;

    }

    @Override
    public void onSignInClicked(final String phoneNumber) {
        // checking for validation
        if (!ValidationUtil.checkPhoneNumber(phoneNumber)) {
            mSignInView.showPhoneNumberError(R.string.error_invalid_phone_number);
            return;
        }
        mSignInView.showLoading();
        mDataManager.apiCallForLogin(phoneNumber, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                onSignInSuccess(commonResponse);
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
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
        });
    }

    @Override
    public void onSignInSuccess(final CommonResponse commonResponse) {
        if (isViewAttached()) {
            mSignInView.hideLoading();
            SignInResponseModel signInResponseModel = commonResponse.toResponseModel(SignInResponseModel.class);
            mDataManager.saveAccessToken(signInResponseModel.getToken());
            mSignInView.onSignInSuccess(commonResponse.getMessage());
        }
    }

    @Override
    public void onBackPress() {
        mSignInView.onBackPress();
    }
}
