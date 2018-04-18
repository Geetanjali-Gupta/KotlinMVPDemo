package com.skeleton.mvp.ui.onboarding.otpverification;

import com.skeleton.mvp.data.DataManagerImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer: Geetanjali Gupta on  18/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class OTPVerificationPresenterUnitTestCases {
    private OTPVerificationPresenterImpl mOTPVerificationPresenterImpl;
    @Mock
    private DataManagerImpl dataManager;
    @Mock
    private OTPView mOTPView;

    @Before
    public void initialisePresenter() throws Exception {
        mOTPVerificationPresenterImpl = new OTPVerificationPresenterImpl(mOTPView, dataManager);
        mOTPVerificationPresenterImpl.onAttach();
    }

    /**
     * Used to Verify showError method is called when invalid OTP
     */
    @Test
    public void onContinueClick_showErrorIfInvalidOTP() {
        mOTPVerificationPresenterImpl.onContinueBtnClick(Mockito.anyString(), "63");
        verify(mOTPView, times(1)).showErrorMessage(Mockito.anyInt());
    }

    /**
     * Used to Verify showError method is called when empty OTP
     */
    @Test
    public void onContinueClick_showErrorIfEmptyOTP() {
        mOTPVerificationPresenterImpl.onContinueBtnClick(Mockito.anyString(), "");
        verify(mOTPView, times(1)).showErrorMessage(Mockito.anyInt());
    }

    /**
     * Used to Verify showLoading method is called when valid OTP
     */
    @Test
    public void onContinueBtnClick_showLoadingIfValidOTP() {
        mOTPVerificationPresenterImpl.onContinueBtnClick("", "1111");
        verify(mOTPView, times(1)).showLoading();
    }

    /**
     * Used to Verify showLoading method is called when valid OTP
     */
    @Test
    public void onResendBtnClick_showLoading() {
        mOTPVerificationPresenterImpl.onResendBtnClick("");
        verify(mOTPView, times(1)).showLoading();
    }

    /**
     * Used to Verify onBackPress method is called when back Press Called
     */
    @Test
    public void onBackPress_backPressCalled() {
        mOTPVerificationPresenterImpl.onBackPress();
        verify(mOTPView, times(1)).onBackPress();
    }

    /**
     * Used to verify is view attached or not
     */
    @Test
    public void isViewAttached_ReturnTrueIfViewAttached() {
        assertTrue(mOTPVerificationPresenterImpl.isViewAttached());
    }

    /**
     * Used to verify is view attached or not after onDetach() called
     */
    @Test
    public void isViewAttached_ReturnFalseIfViewNotAttached() {
        mOTPVerificationPresenterImpl.onDetach();
        assertFalse(mOTPVerificationPresenterImpl.isViewAttached());
    }
}
