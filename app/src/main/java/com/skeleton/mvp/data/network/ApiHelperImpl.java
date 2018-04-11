package com.skeleton.mvp.data.network;


import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.data.db.DbHelper;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.DateTimeUtil;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

import java.io.File;
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
    private static final String GET_CURRENT_APP_VERSION = "appVersion/getCurrentVersions";
    private static final String ACCESS_TOKEN_LOGIN = "user/accessTokenLogin";
    private static final String LOGIN = "user/login";
    private static final String REGISTER = "customer/registerFromEmail";
    private static final String VERIFY_OTP = "user/verifyOTP";
    private static final String RESEND_OTP = "user/resendOTP";
    private static final String GET_RESET_PASSWORD_TOKEN = "user/getResetPasswordToken";
    private static final String VERIFY_FORGOT_PASSWORD_OTP = "user/verifyForgotPasswordOTP";
    private static final String RESET_PASSWORD = "user/resetPassword";
    private static final String UPDATE_PROFILE = "customer/updateProfile";
    private static final String VEHICLES_PERMITTED = "user/getVehiclesPermitted";
    private static final String CHANGE_PASSWORD = "user/changePassword";

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

/*    @Override
    public void apiCallForAccessTokenLogin(final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(ACCESS_TOKEN_LOGIN,
                        getApiHeader(true), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallForLogin(final String countryDialCode, final String countryIsoCode,
                                final String phoneNumber, final String password, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("primaryMobile", phoneNumber)
                .add("role", AppConstant.ROLE)
                .add("password", password)
                .add("deviceToken", mDbHelper.getFcmToken())
                .add("appVersion", BuildConfig.VERSION_CODE)
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(LOGIN, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallForFbLogin(final FbUserDetails mFbUserDetails, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("role", AppConstant.ROLE)
                .add("fbSocialID", mFbUserDetails.getId())
                .add("deviceToken", mDbHelper.getFcmToken())
                .add("appVersion", BuildConfig.VERSION_CODE)
                .add("deviceType", AppConstant.DEVICE_TYPE)
                //.add("accessToken", mFbUserDetails.getAccessToken())
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(LOGIN, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToRegisterUser(final SignUpModel signUpModel, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("name", signUpModel.getFirstName())
                .add("lastName", signUpModel.getLastName())
                .add("email", signUpModel.getEmail())
                .add("password", signUpModel.getPassword())
                .add("mobile", signUpModel.getPhoneNumber())
                .add("countryCode", signUpModel.getCountryCode())
                .add("countryIsoCode", signUpModel.getCountryIsoCode())
                .add("appVersion", BuildConfig.VERSION_CODE)
                .add("deviceToken", mDbHelper.getFcmToken())
                .add("deviceType", AppConstant.DEVICE_TYPE)
                .add("fbSocialID", signUpModel.getFbId())
                //.add("accessToken", signUpModel.getFbAccessToken())
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .postCall(REGISTER, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToVerifyOtp(final String otpCode, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("role", AppConstant.ROLE)
                .add("OTPCode", otpCode)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(VERIFY_OTP, getApiHeader(true), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToResendOtp(final String phone, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("mobile", phone)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(RESEND_OTP, getApiHeader(true), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallForResetPassword(final String countryDialCode, final String countryIsoCode,
                                        final String phoneNumber, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("mobile", phoneNumber)
                .add("countryCode", countryDialCode)
                .add("role", AppConstant.ROLE)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .getCall(GET_RESET_PASSWORD_TOKEN, getApiHeader(false),
                        mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToVerifyForgotPasswordOtp(final String otp, final String countryDialCode,
                                                 final String countryIsoCode, final String phoneNumber, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("mobile", phoneNumber)
                .add("countryCode", countryDialCode)
                .add("OTP", otp)
                .add("role", AppConstant.ROLE)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(VERIFY_FORGOT_PASSWORD_OTP,
                        getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToResetPassword(final String token, final String newPassword, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("token", token)
                .add("newPassword", newPassword)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(RESET_PASSWORD, getApiHeader(false), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToUploadLicence(final File mFile, final ApiListener mApiListener) {
        final MultipartParams multipartParams = new MultipartParams.Builder()
                .addFile("licenceImage", mFile)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putMultipartCall(UPDATE_PROFILE, getApiHeader(true), multipartParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToGetVehiclesPermittedList(final ApiListener mApiListener) {
        executeApiCall(getApiInterface().getCall(VEHICLES_PERMITTED,
                getApiHeader(true)),
                mApiListener);
    }

    @Override
    public void apiCallToCompleteDetails(final String firstName, final String lastName, final String email,
                                         final String licenseNumber, final String licenseIssueDate, final String licenseExpirationDate,
                                         final ArrayList<DropDownItem> mVehiclePermitted, final ApiListener mApiListener) {
        JSONArray mJsonArray = new JSONArray();
        for (final DropDownItem mDropDownItem : mVehiclePermitted) {
            mJsonArray.put(mDropDownItem.getId());
        }
        final MultipartParams multipartParams = new MultipartParams.Builder()
                .add("name", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("licenceNumber", licenseNumber)
                .add("licenceIssueDate", licenseIssueDate)
                .add("licenceExpireDate", licenseExpirationDate)
                .add("vehiclePermitted", mJsonArray)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putMultipartCall(UPDATE_PROFILE, getApiHeader(true), multipartParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToUpdatePersonalDetails(final EditPersonalInfoModel editPersonalInfoModel, final ApiListener mApiListener) {
        final MultipartParams multipartParams = new MultipartParams.Builder()
                .add("name", editPersonalInfoModel.getFirstName())
                .add("lastName", editPersonalInfoModel.getLastName())
                .add("email", editPersonalInfoModel.getEmail(), true)
                .add("mobile", editPersonalInfoModel.getPhoneNumber())
                .add("countryCode", editPersonalInfoModel.getCountryCode())
                .add("countryIsoCode", editPersonalInfoModel.getCountryIsoCode())
                .add("companyAddress", editPersonalInfoModel.getAddress())
                .add("latitude", editPersonalInfoModel.getLatitude())
                .add("longitude", editPersonalInfoModel.getLongitude())
                .addFile("image", editPersonalInfoModel.getProfilePic())
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putMultipartCall(UPDATE_PROFILE, getApiHeader(true), multipartParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToUpdateLicenseDetails(final EditLicenseDetailsModel editLicenseDetailsModel, final ApiListener mApiListener) {
        JSONArray mJsonArray = new JSONArray();
        for (final DropDownItem mDropDownItem : editLicenseDetailsModel.getmVehiclePermitted()) {
            mJsonArray.put(mDropDownItem.getId());
        }
        final MultipartParams multipartParams = new MultipartParams.Builder()
                .add("licenceNumber", editLicenseDetailsModel.getLicenseNumber())
                .add("licenceIssueDate", editLicenseDetailsModel.getLicenseIssueDate())
                .add("licenceExpireDate", editLicenseDetailsModel.getLicenseExpirationDate())
                .add("vehiclePermitted", mJsonArray)
                .addFile("licenceImage", editLicenseDetailsModel.getmLicensePic())
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putMultipartCall(UPDATE_PROFILE, getApiHeader(true), multipartParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }

    @Override
    public void apiCallToChangePassword(final String oldPassword, final String newPassword, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("oldPassword", oldPassword)
                .add("newPassword", newPassword)
                .build();
        final Call<CommonResponse> mCommonResponseCall = getApiInterface()
                .putCall(CHANGE_PASSWORD, getApiHeader(true), mCommonParams.getMap());
        executeApiCall(mCommonResponseCall, mApiListener);
    }*/

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
    public void apiCallForFbLogin(final FbUserDetails mFbUserDetails, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToVerifyOtp(final String otpCode, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToResendOtp(final String phone, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallForResetPassword(final String countryDialCode, final String countryIsoCode,
                                        final String phoneNumber, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToVerifyForgotPasswordOtp(final String otp, final String countryDialCode,
                                                 final String countryIsoCode, final String phoneNumber, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToResetPassword(final String token, final String newPassword, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToUploadLicence(final File mFile, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToGetVehiclesPermittedList(final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToChangePassword(final String oldPassword, final String newPassword, final ApiListener mApiListener) {

    }

    @Override
    public void apiCallToRegisterUser(final SignUpModel signUpModel, final ApiListener mApiListener) {
        final CommonParams mCommonParams = new CommonParams.Builder()
                .add("email", signUpModel.getEmail())
                .add("mobile", signUpModel.getMobile())
                .add("countryCode", signUpModel.getCountryCode())
                .add("password", signUpModel.getPassword())
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
        Call<CommonResponse> getCall(@Url String url,
                                     @HeaderMap Map<String, String> headerMap);

        /**
         * Gets call with query.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param queryMap  the query map
         * @return the call with query
         */
        @GET
        Call<CommonResponse> getCall(@Url String url,
                                     @HeaderMap Map<String, String> headerMap,
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
        Call<CommonResponse> postCall(@Url String url,
                                      @HeaderMap Map<String, String> headerMap,
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
        Call<CommonResponse> putCall(@Url String url,
                                     @HeaderMap Map<String, String> headerMap,
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
        Call<CommonResponse> putMultipartCall(@Url String url,
                                              @HeaderMap Map<String, String> headerMap,
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
        Call<CommonResponse> postMultipartCall(@Url String url,
                                               @HeaderMap Map<String, String> headerMap,
                                               @PartMap HashMap<String, RequestBody> partMap);

    }
}
