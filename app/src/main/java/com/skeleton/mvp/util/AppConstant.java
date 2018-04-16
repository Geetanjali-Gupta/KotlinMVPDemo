package com.skeleton.mvp.util;


/**
 * Developer: Click labs
 */
public final class AppConstant {
    public static final int OTP_LENGTH = 4;

    public static final String DEVICE_TYPE = "ANDROID";
    public static final String ROLE = "customer";

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

    /**
     * Request Codes List
     */
    public interface RequestCodes {
        int REQ_CODE_LANDING = 501;
        int REQ_CODE_SIGN_IN = 502;
        int REQ_CODE_SIGN_UP = 503;
        int REQ_CODE_OTP_VERIFICATION = 504;

        /**
         * Dummy method
         *
         * @return request code
         */
        int getReqCodeSign();
    }
}