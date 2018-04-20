package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.DataManagerImpl;
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
 * Developer:Geetanjali Gupta on 16/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterUnitTestCases {
    private SignInPresenterImpl mSignInPresenterImpl;
    @Mock
    private DataManagerImpl mDataManager;
    @Mock
    private SignInView mSignInView;

    @Before
    public void initialisePresenter() throws Exception {
        mSignInPresenterImpl = new SignInPresenterImpl(mSignInView, mDataManager);
        mSignInPresenterImpl.onAttach();
    }

    /**
     * Used to Verify showNetworkError method is called when internet not connected
     */
    @Test
    public void onSignInClicked_showErrorIfWrongPhoneNumber() {
        ValidationUtil.checkPhoneNumber("6565");
        mSignInPresenterImpl.onSignInClicked(Mockito.anyString());
        verify(mSignInView, times(1)).showErrorMessage(Mockito.anyInt());
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

    /**
     * Used to Verify showError method is called when invalid phone number
     */
    @Test
    public void onSignInClick_showErrorIfInvalidPhoneNumber() {
        mSignInPresenterImpl.onSignInClicked(Mockito.anyString());
        verify(mSignInView, times(1)).showErrorMessage(Mockito.anyInt());
    }

    /**
     * Used to Verify showLoading method is called when valid phone number
     */
    @Test
    public void onSignInClick_showLoadingIfValidPhoneNumber() {
        mSignInPresenterImpl.onSignInClicked("1234567890");
        verify(mSignInView, times(1)).showLoading();
    }

    /**
     * Used to Verify onBackPress method is called when back Press Called
     */
    @Test
    public void onBackPress_backPressCalled() {
        mSignInPresenterImpl.onBackPress();
        verify(mSignInView, times(1)).onBackPress();
    }

    /**
     * Used to verify is view attached or not
     */
    @Test
    public void isViewAttached_ReturnTrueIfViewAttached() {
        assertTrue(mSignInPresenterImpl.isViewAttached());
    }

    /**
     * Used to verify is view attached or not after onDetach() called
     */
    @Test
    public void isViewAttached_ReturnFalseIfViewNotAttached() {
        mSignInPresenterImpl.onDetach();
        assertFalse(mSignInPresenterImpl.isViewAttached());
    }
}
