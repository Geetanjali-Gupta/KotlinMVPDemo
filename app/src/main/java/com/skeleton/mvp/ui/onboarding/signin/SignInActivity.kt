package com.skeleton.mvp.ui.onboarding.signin;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.skeleton.mvp.R
import com.skeleton.mvp.data.network.RestClient
import com.skeleton.mvp.ui.base.BaseActivity
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManagerImpl
import com.skeleton.mvp.ui.onboarding.otpverification.OTPVerificationActivity
import com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_OTP_VERIFICATION
import com.skeleton.mvp.util.CommonUtil
import com.skeleton.mvp.util.ExplicitIntentUtil
import com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER


/**
 * Developer: Click Labs
 */
class SignInActivity : BaseActivity(), View.OnClickListener, SignInView {

    lateinit var etPhone: EditText
    lateinit var mSignInPresenter: SignInPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in);

        init();
    }

    override fun onDestroy() {
        super.onDestroy()
        mSignInPresenter.onDetach()
    }

    /**
     * Init the views
     */
    fun init() {
        mSignInPresenter = SignInPresenterImpl(this, OnBoardingDataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignInPresenter.onAttach();

        etPhone = findViewById(R.id.etPhone);

        findViewById<Button>(R.id.btnSignIn).setOnClickListener(this);
        findViewById<ImageView>(R.id.ivBack).setOnClickListener(this);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSignIn ->
                mSignInPresenter.onSignInClicked(etPhone.getText().toString().trim());
            R.id.ivBack ->
                mSignInPresenter.onBackPress();
        }
    }

    override fun onSignInSuccess(message: String) {
        CommonUtil.showToast(this, message)
        val phoneNumberBundle = Bundle()
        phoneNumberBundle.putString(EXTRA_PHONE_NUMBER, etPhone.getText().toString());
        ExplicitIntentUtil.startActivityForResult(this, OTPVerificationActivity::class.java, REQ_CODE_OTP_VERIFICATION, phoneNumberBundle);
    }

    override fun showPhoneNumberError(resId: Int) {
        etPhone.setError(getString(resId));
    }


    override fun onBackPress() {
        ExplicitIntentUtil.finishActivity(this);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_OTP_VERIFICATION ->
                if (resultCode == RESULT_OK) {
                    ExplicitIntentUtil.finishActivityForResultOk(this);
                } else {
                    ExplicitIntentUtil.finishActivityForResultCancel(this);
                }
        }
    }
}
