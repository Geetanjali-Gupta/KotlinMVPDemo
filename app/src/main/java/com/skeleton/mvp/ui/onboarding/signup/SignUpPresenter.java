package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.ui.base.BasePresenter;


/**
 * Developer: Geetanjali Gupta
 */
public interface SignUpPresenter extends BasePresenter {

    /**
     * Used to handle Sign Up Click
     *
     * @param signUpModel sign up data
     */
    void onSignUpClicked(final SignUpModel signUpModel);

    /**
     * On sign in success.
     *
     * @param commonResponse the common response
     */
    void onSignUpSuccess(CommonResponse commonResponse);

    /**
     * Handle On Back Press
     */
    void onBackPress();
}
