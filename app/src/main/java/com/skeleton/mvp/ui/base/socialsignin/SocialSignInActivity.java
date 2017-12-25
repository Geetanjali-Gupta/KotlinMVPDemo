package com.skeleton.mvp.ui.base.socialsignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.util.facebookutil.FacebookManager;
import com.skeleton.mvp.util.facebookutil.FacebookResponseHandler;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

/**
 * Developer: Click Labs
 */
public abstract class SocialSignInActivity extends BaseActivity implements SocialSignInView {

    private CallbackManager mCallbackManager;
    private SocialSignInPresenter mSocialSignInPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
        mSocialSignInPresenter = new SocialSignInPresenterImpl(this);
        mSocialSignInPresenter.onAttach();
    }

    @Override
    protected void onDestroy() {
        mSocialSignInPresenter.onDetach();
        super.onDestroy();
    }

    /**
     * On fb login clicked.
     */
    protected void onFbLoginClicked() {
        if (isNetworkConnected()) {
            fbLogin();
        } else {
            showErrorMessage(R.string.error_internet_not_connected);
        }
    }

    /**
     * Method to get user fb details
     */
    private void fbLogin() {
        new FacebookManager(SocialSignInActivity.this).getFbUserDetails(mCallbackManager, new FacebookResponseHandler() {
            @Override
            public void onSuccess(final FbUserDetails fbUserDetails) {
                mSocialSignInPresenter.onFbLogin(fbUserDetails);
            }

            @Override
            public void onCancel(final String msg) {
                showFbLoginError(msg);
            }

            @Override
            public void onError(final FacebookException e) {
                showFbLoginError(e.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Show fb login error.
     *
     * @param message the message
     */
    private void showFbLoginError(final String message) {
        showErrorMessage(message);
    }

    /**
     * Fb login success.
     *
     * @param message       the message
     * @param fbUserDetails the fb user details
     */
    protected abstract void registerFbUser(String message, FbUserDetails fbUserDetails);

    /**
     * Fb login success.
     *
     * @param commonResponse the common response
     */
    protected abstract void fbLoginSuccess(CommonResponse commonResponse);

    @Override
    public void onFbLoginSuccess(final CommonResponse commonResponse) {
        fbLoginSuccess(commonResponse);
    }

    @Override
    public void onFbUserNotRegistered(final String message, final FbUserDetails fbUserDetails) {
        registerFbUser(message, fbUserDetails);
    }
}
