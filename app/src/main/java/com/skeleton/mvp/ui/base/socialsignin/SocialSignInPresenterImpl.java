package com.skeleton.mvp.ui.base.socialsignin;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.ui.base.BaseInteractor;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.ui.base.BaseView;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

/**
 * Developer: Click Labs
 */
class SocialSignInPresenterImpl extends BasePresenterImpl implements SocialSignInPresenter {

    private SocialSignInView mSocialSignInView;
    private SocialSignInInteractor mSocialSignInInteractor;

    /**
     * Instantiates a new Social sign in presenter.
     *
     * @param mSocialSignInView the m social sign in view
     */
    SocialSignInPresenterImpl(final SocialSignInView mSocialSignInView) {
        this.mSocialSignInView = mSocialSignInView;
        this.mSocialSignInInteractor = new SocialSignInInteractorImpl();
    }

    @Override
    public void onFbLogin(final FbUserDetails fbUserDetails) {
        if (isViewAttached()) {
            mSocialSignInView.showLoading();
            mSocialSignInInteractor.apiCallForFbLogin(fbUserDetails, new BaseInteractor.ApiListener() {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {
                    if (isViewAttached()) {
                        mSocialSignInView.hideLoading();
                        mSocialSignInView.onFbLoginSuccess(commonResponse);
                    }
                }

                @Override
                public void onFailure(final ApiError apiError, final Throwable throwable) {
                    if (isViewAttached()) {
                        mSocialSignInView.hideLoading();
                        if (apiError != null) {
                            if (apiError.getStatusCode() == AppConstant.FB_USER_NOT_REGISTERED) {
                                mSocialSignInView.onFbUserNotRegistered(apiError.getMessage(), fbUserDetails);
                            } else {
                                mSocialSignInView.showErrorMessage(apiError.getMessage(), new BaseView.OnErrorHandleCallback() {
                                    @Override
                                    public void onErrorCallback() {
                                        onFbLogin(fbUserDetails);
                                    }
                                });
                            }
                        } else {
                            mSocialSignInView.showErrorMessage(parseThrowableMessage(throwable), new BaseView.OnErrorHandleCallback() {
                                @Override
                                public void onErrorCallback() {
                                    onFbLogin(fbUserDetails);
                                }
                            });
                        }
                    }
                }
            });
        }
    }
}
