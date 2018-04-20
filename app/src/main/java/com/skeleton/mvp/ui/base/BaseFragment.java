package com.skeleton.mvp.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.ui.dialog.ProgressDialog;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.CommonUtil;

/**
 * Developer: Geetanjali Gupta
 * Dated: 19/04/18.
 */

public class BaseFragment extends Fragment implements BaseView {
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void showErrorMessage(final int resId) {
        showErrorMessage(getString(resId), null);
    }


    @Override
    public void showErrorMessage(final int resId, final OnErrorHandleCallback mOnErrorHandleCallback) {
        showErrorMessage(getString(resId), mOnErrorHandleCallback);
    }

    @Override
    public void showErrorMessage(final String errorMessage) {
        showErrorMessage(errorMessage, null);
    }

    @Override
    public void showErrorMessage(final String errorMessage, final OnErrorHandleCallback mOnErrorHandleCallback) {
        new AlertDialog.Builder(mContext)
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        if (mOnErrorHandleCallback != null) {
                            mOnErrorHandleCallback.onErrorCallback();
                        }
                    }
                })
                .show();
    }

    @Override
    public void restartApp() {

    }

    @Override
    public void onBackPress() {

    }


    @Override
    public void showErrorMessage(final ApiError apiError) {
        showErrorMessage(apiError, null);
    }

    @Override
    public void showErrorMessage(final ApiError apiError, final OnErrorHandleCallback mOnErrorHandleCallback) {
        if (apiError != null) {
            if (apiError.getStatusCode() == AppConstant.SESSION_EXPIRED) {
                CommonUtil.showToast(mContext, getString(R.string.error_session_expired));
                restartApp();
            } else {
                showErrorMessage(apiError.getMessage(), mOnErrorHandleCallback);
            }
        } else {
            showErrorMessage(getString(R.string.error_unexpected_error), mOnErrorHandleCallback);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return CommonUtil.isNetworkAvailable(mContext);
    }


    @Override
    public void showLoading() {
        ProgressDialog.showProgressDialog(mContext);
    }

    @Override
    public void showLoading(final String message) {
        ProgressDialog.showProgressDialog(mContext, message);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.dismissProgressDialog();
    }
}
