package com.skeleton.mvp.ui.base;


import com.skeleton.mvp.data.db.CommonData;

/**
 * Developer: Click Labs
 */
public class BaseInteractorImpl implements BaseInteractor {

    @Override
    public String getAccessToken() {
        return CommonData.getAccessToken();
    }

    @Override
    public void clearSessionManager() {
        CommonData.clearData();
    }

}
