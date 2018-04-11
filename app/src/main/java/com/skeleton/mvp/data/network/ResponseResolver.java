package com.skeleton.mvp.data.network;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Developer: Click Labs
 * <p>
 * Retrofit Response resolver
 */

/**
 * Custom Retrofit ResponseResolver
 *
 * @param <T> the response type
 */
public abstract class ResponseResolver<T> implements Callback<T> {

    private Retrofit mRetrofit;

    /**
     * public paramaterized constructor
     *
     * @param mRetrofit the m retrofit
     */
    public ResponseResolver(final Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    /**
     * On Api response success
     *
     * @param t the response
     */
    public abstract void onSuccess(T t);

    /**
     * On Api error received
     *
     * @param error the error sent by the endpoint
     */
    public abstract void onError(ApiError error);

    /**
     * Indicates failure of the request
     *
     * @param throwable the throwable generated
     */
    public abstract void onFailure(Throwable throwable);

    @Override
    public void onResponse(@NonNull final Call<T> t, @NonNull final Response<T> tResponse) {

        if (tResponse.isSuccessful()) {
            onSuccess(tResponse.body());
        } else {
            onError(ErrorUtils.parseError(mRetrofit, tResponse));
            // onError(ErrorUtils.parseError(tResponse));
        }

    }

    @Override
    public void onFailure(@NonNull final Call<T> t, @NonNull final Throwable throwable) {
        onFailure(throwable);
    }


}
