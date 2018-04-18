package com.skeleton.mvp.util;

import java.util.regex.Pattern;

/**
 * Developer: Click Labs
 */
public final class ValidationUtil {
    private static final String REGEX_EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
            + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    private static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final String REGEX_NAME = "^[\\p{L} .'-]+$";
    private static final String REGEX_ALL_DIGITS = "\\d+";
    private static final int PHONE_NUMBER_MIN_LENGTH = 7;
    private static final int PHONE_NUMBER_MAX_LENGTH = 12;

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
        return !(email == null
                || email.trim().isEmpty()
                || (!email.matches(Pattern.compile(REGEX_EMAIL_ADDRESS).toString())));
    }

    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        return !(password == null
                || password.trim().isEmpty()
                || (!password.matches(REGEX_PASSWORD)));
    }

    /**
     * Check name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean checkName(final String name) {
        return !(name == null
                || name.trim().isEmpty()
                || (!name.matches(REGEX_NAME)));
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

    /**
     * Is empty boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public static boolean isEmpty(final String value) {
        return !(value == null || value.trim().isEmpty());
    }
}
