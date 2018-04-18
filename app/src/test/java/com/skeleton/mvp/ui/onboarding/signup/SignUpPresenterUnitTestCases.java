package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.requestmodel.SignUpModel;
import com.skeleton.mvp.util.ValidationUtil;

import junit.framework.Assert;

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
 * Developer:Geetanjali Gupta on 18/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterUnitTestCases {
    private SignUpPresenterImpl mSignUpPresenterIml;
    @Mock
    private DataManagerImpl mDataManager;
    @Mock
    private SignUpView mSignUpView;

    @Before
    public void initialisePresenter() throws Exception {
        mSignUpPresenterIml = new SignUpPresenterImpl(mSignUpView, mDataManager);
        mSignUpPresenterIml.onAttach();
    }

    /**
     * Used to Verify showNetworkError method is called when internet not connected
     */
    @Test
    public void onSignUpClicked_showErrorIfWrongPhoneNumber() {
        ValidationUtil.checkPhoneNumber("6565");

        mSignUpPresenterIml.onSignUpClicked(invalidPhoneValidEmailSignUpModel());
        verify(mSignUpView, times(1)).showPhoneNumberError(Mockito.anyInt());
    }

    @Test
    public void emptyPhoneNumber_returnsFalse() {
        Assert.assertEquals(false, ValidationUtil.checkPhoneNumber(""));
    }

    @Test
    public void shortWrongPhoneNumber_returnsFalse() {
        Assert.assertEquals(false, ValidationUtil.checkPhoneNumber("764366"));
    }

    @Test
    public void longWrongPhoneNumber_returnsFalse() {
        Assert.assertEquals(false, ValidationUtil.checkPhoneNumber("1234567890123"));
    }

    @Test
    public void validPhoneNumber_returnsTrue() {
        Assert.assertEquals(true, ValidationUtil.checkPhoneNumber("1234567890"));
    }


    @Test
    public void incompleteEmailIdPattern_returnsFalse() {
        Assert.assertEquals(false, ValidationUtil.checkEmail("a@"));
    }

    @Test
    public void validEmail_returnsTrue() {
        Assert.assertEquals(true, ValidationUtil.checkEmail("a@a.com"));
    }


    /**
     * Used to Verify showError method is called when invalid phone number
     */
    @Test
    public void onSignUpClick_showErrorIfInvalidPhoneNumber() {
        mSignUpPresenterIml.onSignUpClicked(invalidPhoneValidEmailSignUpModel());
        verify(mSignUpView, times(1)).showPhoneNumberError(Mockito.anyInt());
    }

    /**
     * Used to Verify showError method is called when invalid phone number
     */
    @Test
    public void onSignUpClick_showErrorIfInvalidEmailId() {
        mSignUpPresenterIml.onSignUpClicked(validPhoneInvalidEmailSignUpModel());
        verify(mSignUpView, times(1)).showEmailError(Mockito.anyInt());
    }

    /**
     * Used to Verify showLoading method is called when valid phone number
     */
    @Test
    public void onSignUpClick_showLoadingIfValidPhoneNumber() {
        mSignUpPresenterIml.onSignUpClicked(validPhoneEmailSignUpModel());
        verify(mSignUpView, times(1)).showLoading();
    }

    /**
     * Used to Verify onBackPress method is called when back Press Called
     */
    @Test
    public void onBackPress_backPressCalled() {
        mSignUpPresenterIml.onBackPress();
        verify(mSignUpView, times(1)).onBackPress();
    }

    /**
     * Used to verify is view attached or not
     */
    @Test
    public void isViewAttached_ReturnTrueIfViewAttached() {
        assertTrue(mSignUpPresenterIml.isViewAttached());
    }

    /**
     * Used to verify is view attached or not after onDetach() called
     */
    @Test
    public void isViewAttached_ReturnFalseIfViewNotAttached() {
        mSignUpPresenterIml.onDetach();
        assertFalse(mSignUpPresenterIml.isViewAttached());
    }

    private SignUpModel validPhoneEmailSignUpModel() {
        return new SignUpModel("abc@gmail.com", "1234567890",
                "+91", 100, 0.0, 0.0);
    }

    private SignUpModel validPhoneInvalidEmailSignUpModel() {
        return new SignUpModel("abc@.com", "1234567890",
                "+91", 100, 0.0, 0.0);
    }

    private SignUpModel invalidPhoneValidEmailSignUpModel() {
        return new SignUpModel("abc@.com", "123",
                "+91", 100, 0.0, 0.0);
    }
}
