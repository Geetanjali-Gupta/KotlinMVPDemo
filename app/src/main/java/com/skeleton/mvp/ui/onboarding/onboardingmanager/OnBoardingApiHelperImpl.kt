package com.skeleton.mvp.ui.onboarding.onboardingmanager

import com.skeleton.mvp.BuildConfig
import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.data.network.CommonParams
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelperImpl
import com.skeleton.mvp.ui.base.basedatamanager.BaseDBHelper
import com.skeleton.mvp.util.AppConstant
import retrofit2.Retrofit

/**
 * Created by clicklabs on 05/07/18.
 */
class OnBoardingApiHelperImpl(mRetrofit: Retrofit, mDbHelper: BaseDBHelper) : BaseApiHelperImpl(), OnBoardingApiHelper {
    private val LOGIN = "user/login"
    private val REGISTER = "customer/registerFromEmail"
    private val OTP_VERIFICATION = "user/verifyMobileOTP"
    private val RESEND_OTP = "/user/resendOTP"

    /**
     * Instantiates a new OnBoarding Api helper.
     *
     * @param mRetrofit the m retrofit
     * @param mDbHelper the m db helper
     */
    init {
        this.mRetrofit = mRetrofit
        this.mDbHelper = mDbHelper
    }


    override fun apiCallForLogin(phoneNumber: String, mApiListener: BaseApiHelper.ApiListener) {
        val mCommonParams = CommonParams.Builder()
                .add("primaryMobile", phoneNumber)
                .add("role", AppConstant.ROLE)
                .add("deviceToken", mDbHelper.getFcmToken())
                .add("appVersion", BuildConfig.VERSION_CODE)
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .build()
        val mCommonResponseCall = getApiInterface()
                .postCall(LOGIN, getApiHeader(false), mCommonParams.map)
        executeApiCall(mCommonResponseCall, mApiListener)
    }

    override fun apiCallToRegisterUser(signUpModel: SignUpModel, mApiListener: BaseApiHelper.ApiListener) {
        val mCommonParams = CommonParams.Builder()
                .add("email", signUpModel.email)
                .add("mobile", signUpModel.mobile)
                .add("countryCode", signUpModel.countryCode)
                .add("appVersion", signUpModel.appVersion)
                .add("latitude", signUpModel.latitude)
                .add("longitude", signUpModel.longitude)
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .add("deviceToken", mDbHelper.getFcmToken())
                .build()
        val mCommonResponseCall = getApiInterface()
                .postCall(REGISTER, getApiHeader(false), mCommonParams.map)
        executeApiCall(mCommonResponseCall, mApiListener)
    }

    override fun apiCallToVerifyOtp(mobileNumber: String, otpCode: String, mApiListener: BaseApiHelper.ApiListener) {
        val mCommonParams = CommonParams.Builder()
                .add("mobile", mobileNumber)
                .add("OTPCode", otpCode).build()
        val mCommonResponseCall = getApiInterface()
                .putCall(OTP_VERIFICATION, getApiHeader(true), mCommonParams.map)
        executeApiCall(mCommonResponseCall, mApiListener)
    }

    override fun apiCallToResendOtp(phone: String, mApiListener: BaseApiHelper.ApiListener) {
        val mCommonParams = CommonParams.Builder()
                .add("mobile", phone).build()
        val mCommonResponseCall = getApiInterface()
                .putCall(RESEND_OTP, getApiHeader(true), mCommonParams.map)
        executeApiCall(mCommonResponseCall, mApiListener)
    }

}