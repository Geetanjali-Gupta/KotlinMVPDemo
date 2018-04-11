package com.skeleton.mvp.util;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Developer: Click Labs
 */

public final class ValidationUtil {

    private static final String REGEX_ALL_DIGITS = "\\d+";
    private static final int PHONE_NUMBER_MIN_LENGTH = 7;
    private static final int PHONE_NUMBER_MAX_LENGTH = 12;
    private static final int PASSWORD_LENGTH = 6;

    /**
     * Prevent instantiation
     */
    private ValidationUtil() {
    }

    /**
     * Method to validate email id
     *
     * @param email user email
     * @return whether email is valid
     */
    public static boolean checkEmail(final String email) {
        if (TextUtils.isEmpty(email) || (!email.matches(Patterns.EMAIL_ADDRESS.toString()))) {
            return false;
        }
        return true;
    }

    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        if (TextUtils.isEmpty(password) || (password.length() < PASSWORD_LENGTH)) {
            return false;
        }
        return true;
    }

    /**
     * Check phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public static boolean checkPhoneNumber(final String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        if (!phoneNumber.matches(REGEX_ALL_DIGITS)) {
            return false;
        }
        final int phoneNumberLength = phoneNumber.length();
        return phoneNumberLength >= PHONE_NUMBER_MIN_LENGTH && phoneNumberLength <= PHONE_NUMBER_MAX_LENGTH;
    }
}
