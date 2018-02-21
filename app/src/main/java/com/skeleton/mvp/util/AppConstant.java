package com.skeleton.mvp.util;


/**
 * Developer: Click labs
 */
public final class AppConstant {

    // notification related
    public static final String NOTIFICATION_RECEIVED = "notification_received";
    public static final String MESSAGE = "message";

    //status code
    public static final int SESSION_EXPIRED = 401;
    public static final int FB_USER_NOT_REGISTERED = SESSION_EXPIRED;


    public static final String LINK = "link=";
    public static final String APN = "&apn=";
    public static final String AFL = "&afl=";
    public static final String IFL = "&ifl=";
    public static final String REFERRER = "&referrer=";

    /**
     * Prevent instantiation
     */
    private AppConstant() {
    }
}