package com.skeleton.mvp.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.ui.dialog.ProgressDialog;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.CommonUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Developer: Click Labs
 * <p>
 * Base Activity
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static final int OVERLAY_TEXT_SIZE_INT = 15;
    private static final int TEN = 10;
    private static final String OVERLAY_TEXT = BuildConfig.APP_NAME + "_" + BuildConfig.FLAVOR + "_v" + BuildConfig.VERSION_CODE;
    private BasePresenter mBasePresenter;

    /**
     * Receiver To handle Notification When App is in Foreground state
     */
    private BroadcastReceiver notificationReceiver;
    private AlertDialog mNotificationDialog;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                showNotificationDialog(intent.getExtras());
            }
        };
    }

    @Override
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(notificationReceiver, new IntentFilter(AppConstant.NOTIFICATION_RECEIVED));

        /**
         * Draw Code Version On the Every Screen Of the APP
         */
        if (BuildConfig.WATER_MARK) {
            final DrawOnTop mDraw = new DrawOnTop(this);
            addContentView(mDraw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mDraw.bringToFront();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);
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
        new AlertDialog.Builder(this)
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
    public void showErrorMessage(final ApiError apiError) {
        showErrorMessage(apiError, null);
    }

    @Override
    public void showErrorMessage(final ApiError apiError, final OnErrorHandleCallback mOnErrorHandleCallback) {
        if (apiError != null) {
            if (apiError.getStatusCode() == AppConstant.SESSION_EXPIRED) {
                CommonUtil.showToast(this, getString(R.string.error_session_expired));
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
        return CommonUtil.isNetworkAvailable(this);
    }


    @Override
    public void showLoading() {
        ProgressDialog.showProgressDialog(this);
    }

    @Override
    public void showLoading(final String message) {
        ProgressDialog.showProgressDialog(this, message);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.dismissProgressDialog();
    }


    @Override
    public boolean dispatchTouchEvent(final MotionEvent event) {
        final View view = getCurrentFocus();
        try {
            final boolean ret = super.dispatchTouchEvent(event);
            if (view != null && view instanceof EditText) {
                final View w = getCurrentFocus();
                final int[] scrcoords = new int[2];
                assert w != null;
                w.getLocationOnScreen(scrcoords);
                final float x = event.getRawX() + w.getLeft() - scrcoords[0];
                final float y = event.getRawY() + w.getTop() - scrcoords[1];
                if (event.getAction() == MotionEvent.ACTION_UP
                        && (x < w.getLeft() || x >= w.getRight()
                        || y < w.getTop() || y > w.getBottom())) {
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }
            }
            return ret;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Show notification dialog
     *
     * @param mBundle notification bundle
     */
    public void showNotificationDialog(final Bundle mBundle) {
        if (mNotificationDialog != null && mNotificationDialog.isShowing()) {
            mNotificationDialog.dismiss();
        }
        mNotificationDialog = new AlertDialog.Builder(BaseActivity.this)
                .setMessage(mBundle.getString(AppConstant.MESSAGE))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        // handle your direction
                    }
                })
                .show();
    }


    /**
     * Class to Draw the Version Code
     */
    public class DrawOnTop extends View {
        private Paint paintText;
        private Rect bounds;

        /**
         * Instantiates a new Draw on top.
         *
         * @param activity current activity context
         */
        public DrawOnTop(final Context activity) {
            super(activity);
            paintText = new Paint();
            bounds = new Rect();
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            // put your drawing commands here
            paintText.setColor(Color.GRAY);
            paintText.setTextSize(CommonUtil.dpToPx(BaseActivity.this, OVERLAY_TEXT_SIZE_INT));
            paintText.getTextBounds(OVERLAY_TEXT, 0, OVERLAY_TEXT.length(), bounds);
            canvas.drawText(OVERLAY_TEXT,
                    getWidth() - (bounds.width() + TEN),
                    this.getHeight() - OVERLAY_TEXT_SIZE_INT,
                    paintText);
        }
    }
}

