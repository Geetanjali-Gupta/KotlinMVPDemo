package com.skeleton.mvp.data.model.responsemodel.home.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by clicklabs on 10/05/18.
 */
public class Plan {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("parentCategory")
    @Expose
    private ParentCategory parentCategory;
    @SerializedName("serviceProviderId")
    @Expose
    private ServiceProviderId serviceProviderId;
    @SerializedName("planName")
    @Expose
    private String planName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private int v;
    @SerializedName("isOnOffer")
    @Expose
    private boolean isOnOffer;
    @SerializedName("bannerImageUrl")
    @Expose
    private Object bannerImageUrl;
    @SerializedName("offerPercentage")
    @Expose
    private int offerPercentage;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("isBlocked")
    @Expose
    private boolean isBlocked;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("oneTimeFee")
    @Expose
    private int oneTimeFee;
    @SerializedName("fixedRate")
    @Expose
    private int fixedRate;
    @SerializedName("maturityValue")
    @Expose
    private int maturityValue;
    @SerializedName("sumAssured")
    @Expose
    private int sumAssured;
    @SerializedName("insuranceTerm")
    @Expose
    private int insuranceTerm;
    @SerializedName("amountInvestedYearly")
    @Expose
    private int amountInvestedYearly;
    @SerializedName("amountInvestedMonthly")
    @Expose
    private int amountInvestedMonthly;

    public String getPlanName() {
        return planName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getOneTimeFee() {
        return oneTimeFee;
    }

    public int getFixedRate() {
        return fixedRate;
    }

    public int getAmountInvestedMonthly() {
        return amountInvestedMonthly;
    }
}