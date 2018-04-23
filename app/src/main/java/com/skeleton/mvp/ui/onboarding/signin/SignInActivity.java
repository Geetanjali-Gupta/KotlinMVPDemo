package com.skeleton.mvp.ui.onboarding.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.onboarding.otpverification.OTPVerificationActivity;
import com.skeleton.mvp.util.CommonUtil;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import static com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_OTP_VERIFICATION;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER;


/**
 * Developer: Click Labs
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener, SignInView {

    private AppCompatEditText etPhone;
    private SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignInPresenter.onDetach();
    }

    /**
     * Init the views
     */
    private void init() {
        mSignInPresenter = new SignInPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignInPresenter.onAttach();

        etPhone = findViewById(R.id.etPhone);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                mSignInPresenter.onSignInClicked(etPhone.getText().toString().trim());
                break;
            case R.id.ivBack:
                mSignInPresenter.onBackPress();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess(final String message) {
        CommonUtil.showToast(this, message);
        Bundle phoneNumberBundle = new Bundle();
        phoneNumberBundle.putString(EXTRA_PHONE_NUMBER, etPhone.getText().toString());
        ExplicitIntentUtil.startActivityForResult(this, OTPVerificationActivity.class, REQ_CODE_OTP_VERIFICATION, phoneNumberBundle);
    }

    @Override
    public void showPhoneNumberError(final int resId) {
        etPhone.setError(getString(resId));
    }

    @Override
    public void onBackPress() {
        ExplicitIntentUtil.finishActivity(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_OTP_VERIFICATION:
                if (resultCode == RESULT_OK) {
                    ExplicitIntentUtil.finishActivityForResultOk(this);
                } else {
                    ExplicitIntentUtil.finishActivityForResultCancel(this);
                }
                break;
            default:
                break;
        }
    }
}
