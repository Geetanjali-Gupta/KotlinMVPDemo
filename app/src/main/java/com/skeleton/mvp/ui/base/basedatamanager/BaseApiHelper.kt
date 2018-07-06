package com.skeleton.mvp.ui.base.basedatamanager

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse
import com.skeleton.mvp.data.network.ApiError

/**
 * Created by clicklabs on 06/07/18.
 */
interface BaseApiHelper {

    /**
     * The interface Api listener.
     */
    interface ApiListener {
        /**
         * On success.
         *
         * @param commonResponse the common response
         */
        fun onSuccess(commonResponse: CommonResponse)

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        fun onFailure(apiError: ApiError?, throwable: Throwable?)
    }

}