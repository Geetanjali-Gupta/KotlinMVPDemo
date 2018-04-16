package com.skeleton.mvp.data.model.responsemodel.onboarding.signin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 13/04/18.
 */
public class Contact {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isPrimary")
    @Expose
    private boolean isPrimary;
    @SerializedName("isVerified")
    @Expose
    private boolean isVerified;

    public String getMobile() {
        return mobile;
    }
}
