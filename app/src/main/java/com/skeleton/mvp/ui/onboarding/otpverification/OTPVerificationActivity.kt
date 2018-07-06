package com.skeleton.mvp.ui.onboarding.otpverification;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.skeleton.mvp.R
import com.skeleton.mvp.data.network.RestClient
import com.skeleton.mvp.ui.base.BaseActivity
import com.skeleton.mvp.ui.dialog.CustomAlertDialog
import com.skeleton.mvp.ui.onboarding.onboardingmanager.OnBoardingDataManagerImpl
import com.skeleton.mvp.util.AppConstant.OTP_LENGTH
import com.skeleton.mvp.util.CommonUtil
import com.skeleton.mvp.util.ExplicitIntentUtil
import com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER

/**
 * Developer: Geetanjali Gupta
 */
class OTPVerificationActivity : BaseActivity(), OTPView, View.OnClickListener {

    lateinit var llVerificationCode: LinearLayout
    lateinit var mOTPVerificationPresenter: OTPVerificationPresenter
    lateinit var phoneNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification);
        initViews();
    }

    /**
     * Used to initialise Views
     */

    fun initViews() {
        mOTPVerificationPresenter = OTPVerificationPresenterImpl(this, OnBoardingDataManagerImpl(RestClient.getRetrofitBuilder()));
        mOTPVerificationPresenter.onAttach();

        llVerificationCode = findViewById(R.id.llVerificationCode);

        findViewById<Button>(R.id.btnContinue).setOnClickListener(this);
        findViewById<Button>(R.id.btnResend).setOnClickListener(this);
        findViewById<ImageView>(R.id.ivBack).setOnClickListener(this);

        handleOtpContainerFields();

        getIntentData();
    }

    /**
     *
     */
    fun getIntentData() {
        val extras: Bundle = getIntent().getExtras()
        phoneNumber = extras.getString(EXTRA_PHONE_NUMBER);

    }

    /**
     * Used to handle Focus on entering text
     */
    private fun handleOtpContainerFields() {
        for (i in 1 until OTP_LENGTH) {
            val editText: EditText = llVerificationCode.getChildAt(i) as EditText;
            val j: Int = i
            if (j < OTP_LENGTH) {
                editText.setOnKeyListener { _, keyCode, keyEvent ->
                    if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.action == KeyEvent.ACTION_DOWN
                            && editText.text.toString().isEmpty() && j != 0) {
                        llVerificationCode.getChildAt(j - 1).requestFocus();
                    }
                    false;
                }
                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {

                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        if (editText.text.toString().isEmpty() && j > 0) {
                            llVerificationCode.getChildAt(j - 1).requestFocus();
                        }
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        if (!editText.text.toString().isEmpty() && j != (OTP_LENGTH - 1)) {
                            llVerificationCode.getChildAt(j + 1).requestFocus();
                        }
                    }
                })
            }
        }

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnContinue -> {
                val strOTP: String = (llVerificationCode.getChildAt(0) as EditText).text.toString() +
                        ((llVerificationCode.getChildAt(1) as EditText).text.toString()) +
                        (llVerificationCode.getChildAt(2) as EditText).getText().toString() +
                        (llVerificationCode.getChildAt(3) as EditText).getText().toString()
                mOTPVerificationPresenter.onContinueBtnClick(phoneNumber, strOTP);
            }
            R.id.btnResend ->
                mOTPVerificationPresenter.onResendBtnClick(phoneNumber);
            R.id.ivBack ->
                mOTPVerificationPresenter.onBackPress();
        }
    }

    override fun onBackPressed() {
        onBackPress();
    }

    override fun onBackPress() {
        CustomAlertDialog.Builder(this)
                .setMessage(R.string.exit_confirmation_msg)
                .setNegativeButton(R.string.text_cancel, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {

                    }
                })
                .setPositiveButton(R.string.text_ok, object : CustomAlertDialog.CustomDialogInterface.OnClickListener {
                    override fun onClick() {
                        mOTPVerificationPresenter.onBackExpireSession();
                        ExplicitIntentUtil.finishActivity(this@OTPVerificationActivity);
                    }
                }).setCancelable(false).show();
    }

    override fun onOtpVerificationSuccessful(message: String) {
        CommonUtil.showToast(this, message);
        ExplicitIntentUtil.finishActivityForResultOk(this);
    }

    override fun onResendOtpSuccessful(message: String) {
        showErrorMessage(message);
    }
}
