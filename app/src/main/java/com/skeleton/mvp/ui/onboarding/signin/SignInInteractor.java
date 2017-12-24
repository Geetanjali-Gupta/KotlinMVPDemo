package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.ui.base.BaseInteractor;

/**
 * Developer: Click Labs
 */
public interface SignInInteractor extends BaseInteractor {

    /**
     * Do login
     *
     * @param email        the provided email
     * @param password     the provided password
     * @param mApiListener the m api listener
     */
    void login(String email, String password, BaseInteractor.ApiListener mApiListener);
}
