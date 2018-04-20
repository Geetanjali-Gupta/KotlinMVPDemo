package com.skeleton.mvp.ui.invite;

import com.skeleton.mvp.FileUtils;
import com.skeleton.mvp.SynchronousExecutorService;
import com.skeleton.mvp.data.network.ApiInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Geetanjali on 17/01/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class InviteInteractorUnitTestCases {

    @Mock
    private InviteInteractor.OnLinkCreatedListener linkCreatedListener;
    private InviteInteractorImpl inviteInteractorImpl;
    private MockWebServer mockWebServer;

    @Before
    public void initialiseServerInteractor() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApiInterface apiInterface = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .dispatcher(new Dispatcher(new SynchronousExecutorService()))
                        .build())
                .build().create(ApiInterface.class);
        inviteInteractorImpl = new InviteInteractorImpl(apiInterface);
    }

    /**
     * Used to verify onSuccess Call on API Hit Success
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void shortLinkCreated_callsOnSuccess_onResponseCode_200() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("success.json"))));
        inviteInteractorImpl.createDynamicShortLink("", "", linkCreatedListener);
        verify(linkCreatedListener, times(1)).onSuccessful(null);
    }

    /**
     * Used to verify onFailure Call on API Hit Success
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void shortLinkCreated_callsOnFailure_onResponseCode_400() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("failure.json"))));
        inviteInteractorImpl.createDynamicShortLink("", "", linkCreatedListener);
        verify(linkCreatedListener, times(1)).onFailure(anyString());
    }

    /**
     * Used to verify onFailure Call on Faulty Json
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void shortLinkCreated_callsOnFailure_onFaultyJson() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(FileUtils.convertStreamToString(getClass().getClassLoader().getResourceAsStream("faulty.json"))));
        inviteInteractorImpl.createDynamicShortLink("", "", linkCreatedListener);
        verify(linkCreatedListener, times(1)).onFailure(anyString());
    }

    /**
     * Used to verify onFailure Call on API Hit Fails due to socket timeout
     * because response on provided by mock server
     *
     * @throws Exception , In convertStreamToString()
     */
    @Test
    public void shortLinkCreated_callsOnFailure_onSocketTimeOut() throws Exception {
        inviteInteractorImpl.createDynamicShortLink("", "", linkCreatedListener);
        verify(linkCreatedListener, times(1)).onFailure(anyString());
    }

    @After
    public void shutDownServer() throws IOException {
        mockWebServer.shutdown();
    }
}
