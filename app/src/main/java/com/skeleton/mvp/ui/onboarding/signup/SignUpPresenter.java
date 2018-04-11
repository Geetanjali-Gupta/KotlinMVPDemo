package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by clicklabs on 11/04/18.
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
}
