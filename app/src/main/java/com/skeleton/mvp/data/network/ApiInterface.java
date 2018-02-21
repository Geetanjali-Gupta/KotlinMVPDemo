package com.skeleton.mvp.data.network;

import com.google.gson.JsonObject;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.ShortLinkModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Developer: Click Labs
 * <p>
 * The API interface for your application
 */
public interface ApiInterface {

    //todo Declare your API endpoints here

    /**
     * Dummy sign in endpoint
     *
     * @param map the map of params to go along with reqquest
     * @return parsed common response object
     */
    @FormUrlEncoded
    @POST("/signIn")
    Call<CommonResponse> signIn(@FieldMap Map<String, String> map);

    /**
     * Used to create shortLinks
     *
     * @param key        google key
     * @param jsonObject request Object
     * @return response in the form of model
     */
    @POST("/v1/shortLinks")
    Call<ShortLinkModel> getShortLink(@Query("key") String key, @Body JsonObject jsonObject);

}
