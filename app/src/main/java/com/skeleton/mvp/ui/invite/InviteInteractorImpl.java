package com.skeleton.mvp.ui.invite;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.skeleton.mvp.data.model.ShortLinkModel;
import com.skeleton.mvp.data.network.ApiInterface;
import com.skeleton.mvp.ui.base.BaseInteractorImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Geetanjali on 15/01/18.
 */

public class InviteInteractorImpl extends BaseInteractorImpl implements InviteInteractor {
    private ApiInterface firebaseApiInterface;

    /**
     * Constructor to create Api Interface
     *
     * @param firebaseApiInterface interface instance
     */
    InviteInteractorImpl(final ApiInterface firebaseApiInterface) {
        this.firebaseApiInterface = firebaseApiInterface;
    }

    /**
     * Used to create dynamic link
     *
     * @param key                   google api key
     * @param longLink              longLink to be shorten
     * @param onLinkCreatedListener listener to be called
     */
    @Override
    public void createDynamicShortLink(final String key, final String longLink, final OnLinkCreatedListener onLinkCreatedListener) {
        final JsonObject requestBody = new JsonObject();
        requestBody.addProperty("longDynamicLink", longLink);
        firebaseApiInterface.getShortLink(key, requestBody).enqueue(new Callback<ShortLinkModel>() {
            @Override
            public void onResponse(@NonNull final Call<ShortLinkModel> call, @NonNull final Response<ShortLinkModel> response) {
                if (response.isSuccessful()) {
                    ShortLinkModel shortLinkModel = response.body();
                    if (shortLinkModel != null) {
                        onLinkCreatedListener.onSuccessful(shortLinkModel.getShortLink());
                    }
                } else {
                    onLinkCreatedListener.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull final Call<ShortLinkModel> call, @NonNull final Throwable t) {
                onLinkCreatedListener.onFailure(t.getMessage());
            }
        });
    }
}
