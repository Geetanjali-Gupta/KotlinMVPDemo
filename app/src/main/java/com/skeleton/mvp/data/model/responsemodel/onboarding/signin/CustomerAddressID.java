package com.skeleton.mvp.data.model.responsemodel.onboarding.signin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developer: Geetanjali Gupta on 13/04/18.
 */
public class CustomerAddressID {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("addressType")
    @Expose
    private String addressType;
    @SerializedName("__v")
    @Expose
    private int v;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("insuranceCompanyID")
    @Expose
    private Object insuranceCompanyID;
    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("zipCode")
    @Expose
    private Object zipCode;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("latitude")
    @Expose
    private double latitude;

}
