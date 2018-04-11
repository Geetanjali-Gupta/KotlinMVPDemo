package com.skeleton.mvp.data.model;

/**
 * Created by clicklabs on 11/04/18.
 */

public class SignUpModel {
    private String email;
    private String mobile;
    private String countryCode;
    private String password;
    private double appVersion;
    private double latitude;
    private double longitude;

    /**
     * Constructor
     *
     * @param email       email Id
     * @param mobile      phone Number
     * @param countryCode country code
     * @param password    password
     * @param appVersion  app version
     * @param latitude    latitude
     * @param longitude   longitude
     */
    public SignUpModel(final String email, final String mobile, final String countryCode,
                       final String password, final double appVersion, final double latitude,
                       final double longitude) {
        this.email = email;
        this.mobile = mobile;
        this.countryCode = countryCode;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public double getAppVersion() {
        return appVersion;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
