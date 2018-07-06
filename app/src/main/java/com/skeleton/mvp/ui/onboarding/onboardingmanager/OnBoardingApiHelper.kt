package com.skeleton.mvp.ui.onboarding.onboardingmanager

import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper

/**
 * Created by clicklabs on 06/07/18.
 */
interface OnBoardingApiHelper : BaseApiHelper {
    /**
     * Api call for login.
     *
     * @param phoneNumber  the phone number
     * @param mApiListener the m api listener
     */
    fun apiCallForLogin(phoneNumber: String, mApiListener: BaseApiHelper.ApiListener)

    /**
     * Api call to register user.
     *
     * @param signUpModel  the sign up model
     * @param mApiListener the m api listener
     */
    fun apiCallToRegisterUser(signUpModel: SignUpModel, mApiListener: BaseApiHelper.ApiListener)

    /**
     * Api call to verify otp.
     *
     * @param otpCode      the otp code
     * @param mobileNumber phone number to be verified
     * @param mApiListener the m api listener
     */
    abstract fun apiCallToVerifyOtp(mobileNumber: String, otpCode: String, mApiListener: BaseApiHelper.ApiListener)

    /**
     * Api call to resend otp.
     *
     * @param phone        the phone
     * @param mApiListener the m api listener
     */
    abstract fun apiCallToResendOtp(phone: String, mApiListener: BaseApiHelper.ApiListener)


}