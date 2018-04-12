package com.skeleton.mvp.util.locationlib;

import com.google.android.gms.location.LocationRequest;

/**
 * Created by Click-labs.
 */
//CHECKSTYLE:OFF
public class MyLocationRequest {

    private LocationRequest mLocationRequest;

    private MyLocationRequest(final Builder builder) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(builder.mTimeInterval);
        mLocationRequest.setFastestInterval(builder.mShortestTimeInterval);
        mLocationRequest.setSmallestDisplacement(builder.mDisplacement);
    }

    /**
     * Get LocationRequestObject
     *
     * @return LocationRequestObject
     */
    public LocationRequest getMyLocationRequest() {
        if (mLocationRequest != null) {
            return mLocationRequest;
        } else {
            throw new IllegalArgumentException("Please build MyLocationRequest.Builder first");
        }
    }


    /**
     * Location builder a builder pattern to get location
     */
    public static class Builder {
        private int mTimeInterval = 1000;
        private int mShortestTimeInterval = 1000;
        private int mDisplacement = 100;


        /**
         * setInterval method set the interval after which location is required repetitively
         *
         * @param timeInterval accepts int to set the interval
         * @return LocationBuilder return LocationBuilder object
         */
        public Builder setInterval(final int timeInterval) {
            mTimeInterval = timeInterval;
            return this;
        }

        /**
         * set the minimum displacemet for which location is updated
         *
         * @param displacement for diaplacement
         * @return LocationBuilder return LocationBuilder object
         */
        public Builder setDisplacement(final int displacement) {
            mDisplacement = displacement;
            return this;
        }

        /**
         * @param shortestTimeInterval int
         * @return LocationBuilder return LocationBuilder object
         */
        public Builder setShortestTimeInterval(final int shortestTimeInterval) {
            mShortestTimeInterval = shortestTimeInterval;
            return this;
        }


        /**
         * build method creates location fetcher object
         *
         * @return MyLocationRequest object
         */
        public MyLocationRequest build() {
            return new MyLocationRequest(this);
        }

    }


}
////CHECKSTYLE:ON
