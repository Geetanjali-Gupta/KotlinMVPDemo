package com.skeleton.mvp.ui.onboarding.otpverification;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.util.CommonUtil;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import static com.skeleton.mvp.util.AppConstant.OTP_LENGTH;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PHONE_NUMBER;

/**
 * Developer: Geetanjali Gupta
 */
public class OTPVerificationActivity extends BaseActivity implements OTPView, View.OnClickListener {

    private LinearLayout llVerificationCode;
    private OTPVerificationPresenter otpVerificationPresenter;

    private String phoneNumber;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        initViews();
    }

    /**
     * Used to initialise Views
     */
    private void initViews() {
        otpVerificationPresenter = new OTPVerificationPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        otpVerificationPresenter.onAttach();

        llVerificationCode = findViewById(R.id.llVerificationCode);

        findViewById(R.id.btnContinue).setOnClickListener(this);
        findViewById(R.id.btnResend).setOnClickListener(this);

        handleOtpContainerFields();

        getIntentData();
    }

    /**
     *
     */
    private void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phoneNumber = extras.getString(EXTRA_PHONE_NUMBER);
        }
    }

    /**
     * Used to handle Focus on entering text
     */
    private void handleOtpContainerFields() {
        for (int i = 0; i < OTP_LENGTH; i++) {
            final EditText editText = (EditText) llVerificationCode.getChildAt(i);
            final int j = i;
            if (j < OTP_LENGTH) {
                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(final View view, final int keyCode, final KeyEvent keyEvent) {
                        if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN
                                && editText.getText().toString().isEmpty() && j != 0) {
                            llVerificationCode.getChildAt(j - 1).requestFocus();
                        }
                        return false;
                    }
                });
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
                        if (editText.getText().length() <= 0 && j > 0) {
                            llVerificationCode.getChildAt(j - 1).requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(final Editable editable) {
                        if (!editText.getText().toString().isEmpty() && j != (OTP_LENGTH - 1)) {
                            llVerificationCode.getChildAt(j + 1).requestFocus();
                        }
                    }
                });
            }
        }

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnContinue:
                String strOTP = ((EditText) llVerificationCode.getChildAt(0)).getText().toString()
                        + ((EditText) llVerificationCode.getChildAt(1)).getText().toString()
                        + ((EditText) llVerificationCode.getChildAt(2)).getText().toString()
                        + ((EditText) llVerificationCode.getChildAt(3)).getText().toString();
                otpVerificationPresenter.onContinueBtnClick(phoneNumber, strOTP);
                break;
            case R.id.btnResend:
                otpVerificationPresenter.onResendBtnClick(phoneNumber);
                break;
            case R.id.ivBack:
                otpVerificationPresenter.onBackPress();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPress() {
        ExplicitIntentUtil.finishActivity(this);
    }

    @Override
    public void onOtpVerificationSuccessful(final String message) {
        CommonUtil.showToast(this, message);
        ExplicitIntentUtil.finishActivityForResultOk(this);
    }

    @Override
    public void onResendOtpSuccessful(final String message) {
        showErrorMessage(message);
    }
}
