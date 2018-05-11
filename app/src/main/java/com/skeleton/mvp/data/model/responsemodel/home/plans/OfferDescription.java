package com.skeleton.mvp.data.model.responsemodel.home.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developer: Geetanjali Gupta
 * Dated: 11/May/2018
 */
public class OfferDescription {

    @SerializedName("subTitle")
    @Expose
    private String subTitle;
    @SerializedName("title")
    @Expose
    private String title;

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }
}