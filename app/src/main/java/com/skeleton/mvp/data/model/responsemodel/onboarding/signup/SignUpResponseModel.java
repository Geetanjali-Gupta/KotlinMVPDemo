package com.skeleton.mvp.data.model.responsemodel.onboarding.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 13/04/18.
 */

public class SignUpResponseModel {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("appVersion")
    @Expose
    private String appVersion;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("medicalHistory")
    @Expose
    private String medicalHistory;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("MSISDN")
    @Expose
    private String mSISDN;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("customerAddressId")
    @Expose
    private String customerAddressId;
    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;
    @SerializedName("contacts")
    @Expose
    private Contacts contacts;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("utcoffset")
    @Expose
    private int utcoffset;
    @SerializedName("isPhoneVerified")
    @Expose
    private boolean isPhoneVerified;
    @SerializedName("isEmailVerified")
    @Expose
    private boolean isEmailVerified;
    @SerializedName("isAdminVerified")
    @Expose
    private boolean isAdminVerified;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("isBlocked")
    @Expose
    private boolean isBlocked;
    @SerializedName("currentCustomerLocation")
    @Expose
    private CurrentCustomerLocation currentCustomerLocation;
    @SerializedName("rememberMe")
    @Expose
    private boolean rememberMe;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("otp")
    @Expose
    private int otp;

    public String getToken() {
        return token;
    }
}
