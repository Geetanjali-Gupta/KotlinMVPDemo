package com.skeleton.mvp.ui.base.basedatamanager

import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel

/**
 * Created by clicklabs on 06/07/18.
 */
open class BaseDataManagerImpl : BaseDataManager {
    lateinit var mDbHelper: BaseDBHelper


    override fun getAccessToken(): String {
        return mDbHelper.getAccessToken()
    }

    override fun getFcmToken(): String {
        return mDbHelper.getFcmToken()
    }

    override fun saveFcmToken(token: String) {
        mDbHelper.saveFcmToken(token)
    }

    override fun clearSessionManager() {
        mDbHelper.clearSessionManager()
    }

    override fun saveUserData(mUserData: SignInResponseModel) {
        mDbHelper.saveUserData(mUserData)
    }

    override fun getUserData(): SignInResponseModel {
        return mDbHelper.getUserData()
    }

    override fun updateUserOtpVerification(isOtpVerificationSuccess: Boolean) {
        mDbHelper.updateUserOtpVerification(isOtpVerificationSuccess)
    }

    override fun saveAccessToken(accessToken: String) {
        mDbHelper.saveAccessToken(accessToken)
    }

}