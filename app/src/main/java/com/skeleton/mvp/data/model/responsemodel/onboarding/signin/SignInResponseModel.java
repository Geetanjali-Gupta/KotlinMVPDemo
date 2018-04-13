package com.skeleton.mvp.data.model.responsemodel.onboarding.signin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by clicklabs on 13/04/18.
 */

public class SignInResponseModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("utcoffset")
    @Expose
    private int utcoffset;
    @SerializedName("userName")
    @Expose
    private String userName;
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
    @SerializedName("serviceProviderAddressID")
    @Expose
    private Object serviceProviderAddressID;
    @SerializedName("customerAddressID")
    @Expose
    private CustomerAddressID customerAddressID;
    @SerializedName("isMSISDNVerified")
    @Expose
    private Object isMSISDNVerified;
    @SerializedName("MSISDN")
    @Expose
    private Object mSISDN;
    @SerializedName("insuranceCompanyID")
    @Expose
    private Object insuranceCompanyID;
    @SerializedName("customerID")
    @Expose
    private CustomerID customerID;
    @SerializedName("role")
    @Expose
    private List<String> role = null;
    @SerializedName("totalCreatedUsers")
    @Expose
    private int totalCreatedUsers;
    @SerializedName("assignedTo")
    @Expose
    private Object assignedTo;
    @SerializedName("assignedBy")
    @Expose
    private Object assignedBy;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("ratedByUserCount")
    @Expose
    private int ratedByUserCount;
    @SerializedName("totalRatingPoints")
    @Expose
    private int totalRatingPoints;
    @SerializedName("passwordResetToken")
    @Expose
    private Object passwordResetToken;
    @SerializedName("cronHardDeleteCount")
    @Expose
    private int cronHardDeleteCount;
    @SerializedName("rememberMe")
    @Expose
    private boolean rememberMe;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts = null;
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
}