package com.skeleton.mvp.ui.base.basedatamanager

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.data.network.ApiError
import com.skeleton.mvp.data.network.ResponseResolver
import com.skeleton.mvp.util.DateTimeUtil
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import java.util.*

/**
 * Created by clicklabs on 06/07/18.
 */
open class BaseApiHelperImpl : BaseApiHelper {
    lateinit var mRetrofit: Retrofit
    lateinit var mDbHelper: BaseDBHelper
    var isApiCalling: Boolean = false


    /**
     * Gets api header.
     *
     * @param isWithAccessToken the is with access token
     * @return the api header
     */
    fun getApiHeader(isWithAccessToken: Boolean): HashMap<String, String> {
        val map = HashMap<String, String>()
        map.put("content-language", "en")
        map.put("utcoffset", DateTimeUtil.getCurrentZoneOffset())
        if (isWithAccessToken) {
            map.put("authorization", mDbHelper.getAccessToken())
        }
        return map
    }

    /**
     * Gets api interface.
     *
     * @return the api interface
     */
    fun getApiInterface(): ApiInterface {
        return mRetrofit.create(ApiInterface::class.java)
    }

    /**
     * Execute api call.
     *
     * @param mApiCall     the m api call
     * @param mApiListener the m api listener
     */
    fun executeApiCall(mApiCall: Call<CommonResponse>, mApiListener: BaseApiHelper.ApiListener) {
        if (!isApiCalling) {
            isApiCalling = true
            mApiCall.enqueue(object : ResponseResolver<CommonResponse>(mRetrofit) {
                override fun onSuccess(commonResponse: CommonResponse) {
                    isApiCalling = false
                    mApiListener.onSuccess(commonResponse)
                }

                override fun onError(error: ApiError) {
                    isApiCalling = false
                    mApiListener.onFailure(error, null)
                }

                override fun onFailure(throwable: Throwable) {
                    isApiCalling = false
                    mApiListener.onFailure(null, throwable)
                }
            })
        }
    }

    /**
     * The interface Api interface.
     */
    interface ApiInterface {

        /**
         * Gets call with header.
         *
         * @param url       the url
         * @param headerMap the header map
         * @return the call with header
         */
        @GET
        fun getCall(@Url url: String, @HeaderMap headerMap: Map<String, String>): Call<CommonResponse>

        /**
         * Gets call with query.
         *
         * @param url       the url
         * @param headerMap the header map
         * @param queryMap  the query map
         * @return the call with query
         */
        @GET
        fun getCall(@Url url: String, @HeaderMap headerMap: Map<String, String>,
                    @QueryMap queryMap: Map<String, String>): Call<CommonResponse>

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
        fun postCall(@Url url: String, @HeaderMap headerMap: Map<String, String>,
                     @FieldMap fieldMap: HashMap<String, String>): Call<CommonResponse>

        /**
         * Post call without data.
         *
         * @param url       the url
         * @param headerMap the header map
         * @return the call
         */
        @POST
        fun postCall(@Url url: String, @HeaderMap headerMap: Map<String, String>): Call<CommonResponse>

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
        fun putCall(@Url url: String, @HeaderMap headerMap: Map<String, String>,
                    @FieldMap fieldMap: HashMap<String, String>): Call<CommonResponse>

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
        fun putMultipartCall(@Url url: String, @HeaderMap headerMap: Map<String, String>,
                             @PartMap partMap: HashMap<String, RequestBody>): Call<CommonResponse>

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
        fun postMultipartCall(@Url url: String, @HeaderMap headerMap: Map<String, String>,
                              @PartMap partMap: HashMap<String, RequestBody>): Call<CommonResponse>

    }
}
