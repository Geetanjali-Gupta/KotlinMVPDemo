package com.skeleton.mvp.ui.onboarding.otpverification;

import android.os.Bundle;

import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.BaseActivity;

/**
 * Developer: Click Labs
 */
public class OTPVerificationActivity extends BaseActivity implements OTPView {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
    }
}
