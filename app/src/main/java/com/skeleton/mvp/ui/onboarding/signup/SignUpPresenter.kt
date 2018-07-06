package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.ui.base.BasePresenter


/**
 * Developer: Geetanjali Gupta
 */
interface SignUpPresenter : BasePresenter {

    /**
     * Used to handle Sign Up Click
     *
     * @param signUpModel sign up data
     */
    fun onSignUpClicked(signUpModel: SignUpModel)

    /**
     * On sign in success.
     *
     * @param commonResponse the common response
     */
    fun onSignUpSuccess(commonResponse: CommonResponse);

    /**
     * Handle On Back Press
     */
    fun onBackPress();
}
