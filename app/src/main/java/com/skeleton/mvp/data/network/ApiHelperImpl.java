package com.skeleton.mvp.data.network;


import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.data.db.DbHelper;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.DateTimeUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Developer: Saurabh Verma
 * Dated: 09/03/18.
 */
public class ApiHelperImpl implements ApiHelper {
    private static final String LOGIN = "user/login";
    private static final String REGISTER = "customer/registerFromEmail";
    private static final String OTP_VERIFICATION = "user/verifyMobileOTP";

    private Retrofit mRetrofit;
    private DbHelper mDbHelper;
    private boolean isApiCalling;

    /**
     * Instantiates a new Api helper.
     *
     * @param mRetrofit the m retrofit
     * @param mDbHelper the m db helper
     */
    public ApiHelperImpl(final Retrofit mRetrofit,
                         final DbHelper mDbHelper) {
        this.mRetrofit = mRetrofit;
        this.mDbHelper = mDbHelper;
    }

    /**
     * Gets api header.
     *
     * @param isWithAccessToken the is with access token
     * @return the api header
     */
    private HashMap<String, String> getApiHeader(final boolean isWithAccessToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("content-language", "en");
        map.put("utcoffset", DateTimeUtil.getCurrentZoneOffset());
        if (isWithAccessToken) {
            map.put("authorization", mDbHelper.getAccessToken());
        }
        return map;
    }

    /**
     * Gets api interface.
     *
     * @return the api interface
     */
    private ApiInterface getApiInterface() {
        return mRetrofit.create(ApiInterface.class);
    }

    /**
     * Execute api call.
     *
     * @param mApiCall     the m api call
     * @param mApiListener the m api listener
     */
    private void executeApiCall(final Call<CommonResponse> mApiCall, final ApiListener mApiListener) {
        if (!isApiCalling) {
            isApiCalling = true;
            mApiCall.enqueue(new ResponseResolver<CommonResponse>(mRetrofit) {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {
                    isApiCalling = false;
                    mApiListener.onSuccess(commonResponse);
                }

                @Override
                public void onError(final ApiError error) {
                    isApiCalling = false;
                    mApiListener.onFailure(error, null);
                }

                @Override
                public void onFailure(final Throwable throwable) {
                    isApiCalling = false;
                    mApiListener.onFailure(null, throwable);
                }
            });
        }
    }

    @Override
    public void apiCallToGetAppVersion(final ApiListener mApiListener) {

    }

    @Override
    public void apiCallForAccessTokenLogin(final ApiListener mApiListener) {

    }

    @Override
    public void apiCallForLogin(final String phoneNumber, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("primaryMobile", phoneNumber)
                .add("role", AppConstant.ROLE)
                .add("deviceToken", mDbHelper.getFcmToken())
                .add("appVersion", BuildConfig.VERSION_CODE)
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(LOGIN, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }


    @Override
    public void apiCallToVerifyOtp(final String mobileNumber, final String otpCode, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("mobile", mobileNumber)
                .add("OTPCode", otpCode).build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(OTP_VERIFICATION, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToResendOtp(final String phone, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToRegisterUser(final SignUpModel signUpModel, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("email", signUpModel.getEmail())
                .add("mobile", signUpModel.getMobile())
                .add("countryCode", signUpModel.getCountryCode())
                .add("appVersion", signUpModel.getAppVersion())
                .add("latitude", signUpModel.getLatitude())
                .add("longitude", signUpModel.getLongitude())
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .add("deviceToken", mDbHelper.getFcmToken())
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(REGISTER, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    /**
     * The interface Api interface.
     */
    public interface ApiInterface {

        /**
         * Gets call with header.
         *
         * @param url       the url
         * @param headerMap the header map
         * @return the call with header
         */
        @GET
        Call<CommonResponse> getCall(@Url String url, @HeaderMap Map<String, String> headerMap);

        /**
         * Gets call with query.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param queryMap  the query map
         * @return the call with query
         */
        @GET
        Call<CommonResponse> getCall(@Url String url, @HeaderMap Map<String, String> headerMap,
                                     @QueryMap Map<String, String> queryMap);

        /**
         * Post call call.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param fieldMap  the field map
         * @return the call
         */
        @FormUrlEncoded
        @POST
        Call<CommonResponse> postCall(@Url String url, @HeaderMap Map<String, String> headerMap,
                                      @FieldMap HashMap<String, String> fieldMap);

        /**
         * Put call call.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param fieldMap  the field map
         * @return the call
         */
        @FormUrlEncoded
        @PUT
        Call<CommonResponse> putCall(@Url String url, @HeaderMap Map<String, String> headerMap,
                                     @FieldMap HashMap<String, String> fieldMap);

        /**
         * Put multipart call call.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param partMap   the part map
         * @return the call
         */
        @Multipart
        @PUT
        Call<CommonResponse> putMultipartCall(@Url String url, @HeaderMap Map<String, String> headerMap,
                                              @PartMap HashMap<String, RequestBody> partMap);

        /**
         * Post multipart call call.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param partMap   the part map
         * @return the call
         */
        @Multipart
        @POST
        Call<CommonResponse> postMultipartCall(@Url String url, @HeaderMap Map<String, String> headerMap,
                                               @PartMap HashMap<String, RequestBody> partMap);

    }
}
