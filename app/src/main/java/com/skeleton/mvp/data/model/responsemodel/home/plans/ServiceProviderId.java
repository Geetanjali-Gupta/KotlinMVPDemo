package com.skeleton.mvp.data.model.responsemodel.home.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 10/05/18.
 */
public class ServiceProviderId {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("mobile")
    @Expose
    private String mobile;

}
