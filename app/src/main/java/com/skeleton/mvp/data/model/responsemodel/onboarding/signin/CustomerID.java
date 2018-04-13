package com.skeleton.mvp.data.model.responsemodel.onboarding.signin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 13/04/18.
 */
public class CustomerID {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("appVersion")
    @Expose
    private String appVersion;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("__v")
    @Expose
    private int v;
    @SerializedName("userID")
    @Expose
    private Object userID;
    @SerializedName("MSISDN")
    @Expose
    private Object mSISDN;
    @SerializedName("weight")
    @Expose
    private Object weight;
    @SerializedName("height")
    @Expose
    private Object height;
    @SerializedName("age")
    @Expose
    private Object age;
    @SerializedName("medicalHistory")
    @Expose
    private Object medicalHistory;
    @SerializedName("image")
    @Expose
    private Object image;

}
