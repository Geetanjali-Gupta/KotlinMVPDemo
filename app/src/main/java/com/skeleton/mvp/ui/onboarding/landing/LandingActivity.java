package com.skeleton.mvp.ui.onboarding.landing;

import android.os.Bundle;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.onboarding.signin.SignInActivity;
import com.skeleton.mvp.ui.onboarding.signup.SignUpActivity;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import static com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_SIGN_IN;
import static com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_SIGN_UP;


/**
 * Developer: Geetanjali Gupta
 */
public class LandingActivity extends BaseActivity implements LandingView, View.OnClickListener {
    private LandingPresenter mLandingPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        init();
    }

    private void init() {
        mLandingPresenter = new LandingPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mLandingPresenter.onAttach();


        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnSignUp).setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        mLandingPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                mLandingPresenter.onSignInClicked();
                break;
            case R.id.btnSignUp:
                mLandingPresenter.onSignUpClicked();
                break;
            default:
                break;

        }
    }

    @Override
    public void navigateToSignInScreen() {
        ExplicitIntentUtil.startActivityForResult(this, SignInActivity.class, REQ_CODE_SIGN_IN, null);
    }

    @Override
    public void navigateToSignUpScreen() {
        ExplicitIntentUtil.startActivityForResult(this, SignUpActivity.class, REQ_CODE_SIGN_UP, null);
    }
}
