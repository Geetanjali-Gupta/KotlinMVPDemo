package com.skeleton.mvp.ui.onboarding.signup;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rilixtech.CountryCodePicker;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.requestmodel.SignUpModel;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.locationbase.BaseLocationActivity;
import com.skeleton.mvp.ui.onboarding.otpverification.OTPVerificationActivity;
import com.skeleton.mvp.util.CommonUtil;
import com.skeleton.mvp.util.ExplicitIntentUtil;
import com.skeleton.mvp.util.locationlib.MyLocationRequest;

import static com.skeleton.mvp.util.AppConstant.RequestCodes.REQ_CODE_OTP_VERIFICATION;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER;

/**
 * Developer: Geetanjali Gupta
 */
public class SignUpActivity extends BaseLocationActivity implements SignUpView, View.OnClickListener {
    private SignUpPresenter mSignUpPresenter;
    private EditText etPhone, etEmail;
    private CountryCodePicker countryCodePicker;

    private Location currentLocation;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
    }

    /**
     * Used to initialise Views
     */
    private void initViews() {
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        countryCodePicker = findViewById(R.id.countryCodePicker);

        findViewById(R.id.btnSignUp).setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);

        mSignUpPresenter = new SignUpPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignUpPresenter.onAttach();

        MyLocationRequest builder = new MyLocationRequest.Builder().build();
        resolveMyLocationRequest(builder.getMyLocationRequest(),
                getString(R.string.rational_msg_location_permissions), false, true);
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
    public void onSignUpSuccess(final String message) {
        CommonUtil.showToast(this, message);
        Bundle phoneNumberBundle = new Bundle();
        phoneNumberBundle.putString(EXTRA_PHONE_NUMBER, etPhone.getText().toString());
        ExplicitIntentUtil.startActivityForResult(this, OTPVerificationActivity.class, REQ_CODE_OTP_VERIFICATION, phoneNumberBundle);
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
                mSignUpPresenter.onSignUpClicked(new SignUpModel(etEmail.getText().toString(), etPhone.getText().toString(),
                        countryCodePicker.getSelectedCountryCodeWithPlus(), CommonUtil.getAppVersionCode(this),
                        currentLocation.getLatitude(), currentLocation.getLongitude()));
                break;
            case R.id.ivBack:
                mSignUpPresenter.onBackPress();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onLocationReceived(final Location location) {
        if (location != null && location.getLatitude() != 0.0) {
            currentLocation = location;
        }
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
