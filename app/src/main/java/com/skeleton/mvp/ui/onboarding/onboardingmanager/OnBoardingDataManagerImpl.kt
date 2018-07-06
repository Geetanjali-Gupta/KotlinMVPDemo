package com.skeleton.mvp.ui.onboarding.onboardingmanager

import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.ui.base.basedatamanager.BaseApiHelper
import com.skeleton.mvp.ui.base.basedatamanager.BaseDBHelperImpl
import com.skeleton.mvp.ui.base.basedatamanager.BaseDataManagerImpl
import retrofit2.Retrofit

/**
 * Created by clicklabs on 06/07/18.
 */
class OnBoardingDataManagerImpl(mRetrofit: Retrofit) : BaseDataManagerImpl(), OnBoardingDataManager {
    var mOnBoardingApiHelper: OnBoardingApiHelper

    /**
     * Instantiates a new Data manager.
     *
     * @param mRetrofit the m retrofit
     */
    init {
        this.mDbHelper = BaseDBHelperImpl()
        this.mOnBoardingApiHelper = OnBoardingApiHelperImpl(mRetrofit, mDbHelper)
    }

    override fun apiCallForLogin(phoneNumber: String, mApiListener: BaseApiHelper.ApiListener) {
        mOnBoardingApiHelper.apiCallForLogin(phoneNumber, mApiListener)
    }

    override fun apiCallToRegisterUser(signUpModel: SignUpModel, mApiListener: BaseApiHelper.ApiListener) {
        mOnBoardingApiHelper.apiCallToRegisterUser(signUpModel, mApiListener)
    }

    override fun apiCallToVerifyOtp(mobileNumber: String, otpCode: String, mApiListener: BaseApiHelper.ApiListener) {
        mOnBoardingApiHelper.apiCallToVerifyOtp(mobileNumber, otpCode, mApiListener)
    }

    override fun apiCallToResendOtp(phone: String, mApiListener: BaseApiHelper.ApiListener) {
        mOnBoardingApiHelper.apiCallToResendOtp(phone, mApiListener)
    }

}