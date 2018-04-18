package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.util.ValidationUtil;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer:Geetanjali Gupta on 16/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterUnitTestCases {
    private SignInPresenterImpl signInPresenterImpl;
    @Mock
    private DataManagerImpl dataManager;
    @Mock
    private SignInView signInView;

    @Before
    public void initialisePresenter() throws Exception {
        signInPresenterImpl = new SignInPresenterImpl(signInView, dataManager);
        signInPresenterImpl.onAttach();
    }

    /**
     * Used to Verify showNetworkError method is called when internet not connected
     */
    @Test
    public void onSignInClicked_showErrorIfWrongPhoneNumber() {
        ValidationUtil.checkPhoneNumber("6565");
        signInPresenterImpl.onSignInClicked(Mockito.anyString());
        verify(signInView, times(1)).showErrorMessage(Mockito.anyInt());
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
        signInPresenterImpl.onSignInClicked(Mockito.anyString());
        verify(signInView, times(1)).showErrorMessage(Mockito.anyInt());
    }

    /**
     * Used to Verify showLoading method is called when valid phone number
     */
    @Test
    public void onSignInClick_showLoadingIfValidPhoneNumber() {
        signInPresenterImpl.onSignInClicked("1234567890");
        verify(signInView, times(1)).showLoading();
    }

    /**
     * Used to Verify onBackPress method is called when back Press Called
     */
    @Test
    public void onBackPress_backPressCalled() {
        signInPresenterImpl.onBackPress();
        verify(signInView, times(1)).onBackPress();
    }

    /**
     * Used to verify is view attached or not
     */
    @Test
    public void isViewAttached_ReturnTrueIfViewAttached() {
        assertTrue(signInPresenterImpl.isViewAttached());
    }

    /**
     * Used to verify is view attached or not after onDetach() called
     */
    @Test
    public void isViewAttached_ReturnFalseIfViewNotAttached() {
        signInPresenterImpl.onDetach();
        assertFalse(signInPresenterImpl.isViewAttached());
    }
}
