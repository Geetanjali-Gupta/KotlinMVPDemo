package com.skeleton.mvp.data.model.requestmodel;

/**
 * Created by clicklabs on 11/04/18.
 */

public class SignUpModel {
    private String email;
    private String mobile;
    private String countryCode;
    private int appVersion;
    private double latitude;
    private double longitude;

    /**
     * Constructor
     *
     * @param email       email Id
     * @param mobile      phone Number
     * @param countryCode country code
     * @param appVersion  app version
     * @param latitude    latitude
     * @param longitude   longitude
     */
    public SignUpModel(final String email, final String mobile, final String countryCode,
                       final int appVersion, final double latitude,
                       final double longitude) {
        this.email = email;
        this.mobile = mobile;
        this.countryCode = countryCode;
        this.appVersion = appVersion;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
