package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ResponseResolver;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseInteractorImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Developer: Click Labs
 */

public class SignInInteractorImpl extends BaseInteractorImpl implements SignInInteractor {

    @Override
    public void login(final String email, final String password, final ApiListener mApiListener) {

        Map<String, String> map = new HashMap<>();

        // todo add params and hit api

        // dummy implementation
        RestClient.getApiInterface().signIn(map).enqueue(new ResponseResolver<CommonResponse>() {

            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                mApiListener.onSuccess(commonResponse);
            }

            @Override
            public void onError(final ApiError error) {
                mApiListener.onFailure(error, null);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                mApiListener.onFailure(null, throwable);
            }
        });
    }
}
