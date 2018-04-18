package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.FileUtils;
import com.skeleton.mvp.SynchronousExecutorService;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.db.CommonData;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer:Geetanjali Gupta on 17/04/18.
 */
//PowerMock and MockWebServer do not play well together
//that's why need annotation @PowerMockIgnore
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest({CommonData.class})
@RunWith(PowerMockRunner.class)
public class SignInApiCallUnitTestCases {

    private SignInPresenterImpl mSignInPresenterImpl;
    @Mock
    private DataManagerImpl mDataManager;
    @Mock
    private SignInView mSignInView;
    @Mock
    private ApiHelper.ApiListener mApiListener;
    private MockWebServer mMockWebServer;
    private CommonResponse mCommonResponse;
    private ApiError mApiError;
    private Throwable mThrowable;

    @Before
    public void initialisePresenter() throws Exception {
        mMockWebServer = new MockWebServer();
        try {
            mMockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mMockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .dispatcher(new Dispatcher(new SynchronousExecutorService())).build())
                .build();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CommonData.class);
        mDataManager = new DataManagerImpl(retrofit);
        mSignInPresenterImpl = new SignInPresenterImpl(mSignInView, mDataManager);
        mSignInPresenterImpl.onAttach();
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
     * Used to verify onSuccess Call on API Hit Success
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void onSignIn_callsOnSuccess_onResponseCode_200() throws Exception {
        mMockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("success.json"))));

        mDataManager.apiCallForLogin(Mockito.anyString(), new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                mCommonResponse = commonResponse;
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {

            }
        });
        mApiListener.onSuccess(mCommonResponse);
        verify(mApiListener, times(1)).onSuccess(mCommonResponse);
        mSignInPresenterImpl.onSignInSuccess(mCommonResponse);
        verify(mSignInView, times(1)).hideLoading();
        verify(mSignInView, times(1)).onSignInSuccess(Mockito.anyString());
    }

    /**
     * Used to verify onFailure Call on API Hit Success
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void onSignIn_callsOnFailure_onResponseCode_400() throws Exception {
        mMockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("failure.json"))));
        mDataManager.apiCallForLogin(Mockito.anyString(), new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                mApiError = apiError;
                mThrowable = throwable;
                mApiListener.onFailure(apiError, throwable);
            }
        });
        verify(mApiListener, times(1)).onFailure(mApiError, mThrowable);
    }

    /**
     * Used to verify onFailure Call on Faulty Json
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void onSignIn_callsOnFailure_onFaultyJson() throws Exception {
        mMockWebServer.enqueue(new MockResponse()
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("faulty.json"))));
        mDataManager.apiCallForLogin(Mockito.anyString(), new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                mApiError = apiError;
                mThrowable = throwable;
                mApiListener.onFailure(apiError, throwable);
            }
        });
        verify(mApiListener, times(1)).onFailure(mApiError, mThrowable);
    }

    /**
     * Used to verify onFailure Call on API Hit Fails due to socket timeout
     * because response on provided by mock server
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void onSignIn_callsOnFailure_onSocketTimeOut() throws Exception {
        mDataManager.apiCallForLogin(Mockito.anyString(), new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                mApiError = apiError;
                mThrowable = throwable;
                mApiListener.onFailure(apiError, throwable);
            }
        });
        verify(mApiListener, times(1)).onFailure(mApiError, mThrowable);
    }

    @After
    public void shutDownServer() throws IOException {
        mMockWebServer.shutdown();
    }
}
