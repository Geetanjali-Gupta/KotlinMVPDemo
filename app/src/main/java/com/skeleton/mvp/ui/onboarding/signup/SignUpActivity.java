package com.skeleton.mvp.ui.onboarding.signup;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rilixtech.CountryCodePicker;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.locationbase.BaseLocationActivity;
import com.skeleton.mvp.util.CommonUtil;

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
        mSignUpPresenter = new SignUpPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSignUpPresenter.onAttach();

        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        countryCodePicker = findViewById(R.id.countryCodePicker);

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
                mSignUpPresenter.onSignUpClicked(new SignUpModel(etEmail.getText().toString(), etPhone.getText().toString(),
                        countryCodePicker.getSelectedCountryCodeWithPlus(), CommonUtil.getAppVersionCode(this),
                        currentLocation.getLatitude(), currentLocation.getLongitude()));
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
}
