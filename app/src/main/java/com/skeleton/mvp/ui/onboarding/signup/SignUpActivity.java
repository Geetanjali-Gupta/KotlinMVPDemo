package com.skeleton.mvp.ui.onboarding.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.util.CommonUtil;

/**
 * Developer: Geetanjali Gupta
 */
public class SignUpActivity extends BaseActivity implements SignUpView, View.OnClickListener {
    private SignUpPresenter mSignUpPresenter;
    private EditText etPhone, etEmail, etPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
    }

    private void initViews() {
        mSignUpPresenter = new SignUpPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignUpPresenter.onAttach();

        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnSignUp).setOnClickListener(this);
    }

    @Override
    public void showEmailError(final int resId) {
        etEmail.setError(getString(resId));
    }

    @Override
    public void showPhoneNumberError(final int resId) {
        etPhone.setError(getString(resId));
    }

    @Override
    public void showPasswordError(final int resId) {
        etPassword.setError(getString(resId));
    }

    @Override
    public void onSignUpSuccess(final String message) {
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @Override
    protected void onDestroy() {
        mSignUpPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                SignUpModel signUpModel = new SignUpModel(etEmail.getText().toString(), etPhone.getText().toString(),
                        "+91", etPassword.getText().toString(), CommonUtil.getAppVersionCode(this), 0.0, 0.0
                );
                mSignUpPresenter.onSignUpClicked(signUpModel);
                break;
            default:
                break;
        }
    }
}
