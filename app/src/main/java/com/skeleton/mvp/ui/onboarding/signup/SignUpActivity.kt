package com.skeleton.mvp.ui.onboarding.signup;

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.hbb20.CountryCodePicker
import com.skeleton.mvp.R
import com.skeleton.mvp.data.model.requestmodel.SignUpModel
import com.skeleton.mvp.data.network.RestClient
import com.skeleton.mvp.ui.base.locationbase.BaseLocationActivity
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManagerImpl
import com.skeleton.mvp.ui.onboarding.otpverification.OTPVerificationActivity
import com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_OTP_VERIFICATION
import com.skeleton.mvp.util.CommonUtil
import com.skeleton.mvp.util.ExplicitIntentUtil
import com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER
import com.skeleton.mvp.util.locationlib.MyLocationRequest

/**
 * Developer: Geetanjali Gupta
 */
class SignUpActivity : BaseLocationActivity(), SignUpView, View.OnClickListener {
    lateinit var mSignUpPresenter: SignUpPresenter
    lateinit var etPhone: EditText
    lateinit var etEmail: EditText
    lateinit var countryCodePicker: CountryCodePicker;
    var currentLocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()
    }

    /**
     * Used to initialise Views
     */
    fun initViews() {
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        countryCodePicker = findViewById(R.id.countryCodePicker);

        findViewById<Button>(R.id.btnSignUp).setOnClickListener(this);
        findViewById<ImageView>(R.id.ivBack).setOnClickListener(this);

        mSignUpPresenter = SignUpPresenterImpl(this, OnBoardingDataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignUpPresenter.onAttach();

        val builder: MyLocationRequest = MyLocationRequest.Builder().build()
        resolveMyLocationRequest(builder.getMyLocationRequest(),
                getString(R.string.rational_msg_location_permissions), false, true);
    }

    override fun showEmailError(resId: Int) {
        etEmail.setError(getString(resId));
    }

    override fun showPhoneNumberError(resId: Int) {
        etPhone.setError(getString(resId));
    }

    override fun onSignUpSuccess(message: String) {
        CommonUtil.showToast(this, message);
        val phoneNumberBundle = Bundle();
        phoneNumberBundle.putString(EXTRA_PHONE_NUMBER, etPhone.text.toString());
        ExplicitIntentUtil.startActivityForResult(this, OTPVerificationActivity::class.java, REQ_CODE_OTP_VERIFICATION, phoneNumberBundle);
    }

    override fun onDestroy() {
        super.onDestroy()
        mSignUpPresenter.onDetach();
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSignUp ->
                if (currentLocation != null) {
                    mSignUpPresenter.onSignUpClicked(SignUpModel(etEmail.getText().toString(), etPhone.getText().toString(),
                            countryCodePicker.selectedCountryCodeWithPlus, CommonUtil.getAppVersionCode(this),
                            currentLocation!!.latitude, currentLocation!!.longitude));
                } else {
                    showDialogForEnablingGPS();
                }
            R.id.ivBack ->
                onBackPress()
        }
    }

    override fun onLocationReceived(location: Location?) {
        if (location != null && location.latitude != 0.0) {
            currentLocation = location;
        }
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
