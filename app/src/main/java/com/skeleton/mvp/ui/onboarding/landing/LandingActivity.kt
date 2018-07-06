package com.skeleton.mvp.ui.onboarding.landing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.skeleton.mvp.R
import com.skeleton.mvp.data.DataManagerImpl
import com.skeleton.mvp.data.network.RestClient
import com.skeleton.mvp.ui.base.BaseActivity
import com.skeleton.mvp.ui.home.HomeActivity
import com.skeleton.mvp.ui.onboarding.signin.SignInActivity
import com.skeleton.mvp.ui.onboarding.signup.SignUpActivity
import com.skeleton.mvp.util.AppConstant
import com.skeleton.mvp.util.ExplicitIntentUtil

/**
 * Created by clicklabs on 05/07/18.
 */

class LandingActivity : BaseActivity(), LandingView, View.OnClickListener {

    lateinit var landingPresenter: LandingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        init();
    }

    fun init() {
        landingPresenter = LandingPresenterImpl(this, DataManagerImpl(RestClient.getRetrofitBuilder()))
        landingPresenter.onAttach()

        findViewById<Button>(R.id.btnSignIn).setOnClickListener(this);
        findViewById<Button>(R.id.btnSignUp).setOnClickListener(this);
    }

    override fun navigateToSignInScreen() {
        ExplicitIntentUtil.startActivityForResult(this, SignInActivity::class.java, AppConstant.RequestCodes.REQ_CODE_SIGN_IN, null)
    }

    override fun navigateToSignUpScreen() {
        ExplicitIntentUtil.startActivityForResult(this, SignUpActivity::class.java, AppConstant.RequestCodes.REQ_CODE_SIGN_UP, null)
    }

    override fun navigateToHomeScreen() {
        ExplicitIntentUtil.startActivity(this, HomeActivity::class.java, null)
    }

    override fun onBackPress() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignIn ->
                landingPresenter.onSignInClicked()
            R.id.btnSignUp ->
                landingPresenter.onSignUpClicked()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AppConstant.RequestCodes.REQ_CODE_SIGN_IN -> if (resultCode == Activity.RESULT_OK) {
                navigateToHomeScreen()
            }
            AppConstant.RequestCodes.REQ_CODE_SIGN_UP -> if (resultCode == Activity.RESULT_OK) {
                navigateToHomeScreen()
            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        landingPresenter.onDetach()
        super.onDestroy()
    }
}