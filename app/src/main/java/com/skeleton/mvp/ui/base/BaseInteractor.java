package com.skeleton.mvp.ui.base;


import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;

/**
 * Developer: Click Labs
 */
public interface BaseInteractor {


    /**
     * Gets access token.
     *
     * @return the access token
     */
    String getAccessToken();

    /**
     * Clear session manager.
     */
    void clearSessionManager();


    /**
     * The interface Api listener.
     */
    interface ApiListener {
        /**
         * On success.
         *
         * @param commonResponse the common response
         */
        void onSuccess(CommonResponse commonResponse);

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
