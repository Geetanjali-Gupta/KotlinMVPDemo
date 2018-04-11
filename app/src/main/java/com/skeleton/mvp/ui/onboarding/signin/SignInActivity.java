package com.skeleton.mvp.ui.onboarding.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;


/**
 * Developer: Click Labs
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener, SignInView {

    private AppCompatEditText etPhone, etPassword;
    private SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        mSignInPresenter = new SignInPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignInPresenter.onAttach();
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
        etPhone = findViewById(R.id.etPhone);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                mSignInPresenter.onSignInClicked(etPhone.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess(final String message) {
        setResult(RESULT_OK, getIntent());
        finish();
    }
}
