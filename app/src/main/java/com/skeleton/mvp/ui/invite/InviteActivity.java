package com.skeleton.mvp.ui.invite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;

import static com.skeleton.mvp.util.AppConstant.AFL;
import static com.skeleton.mvp.util.AppConstant.APN;
import static com.skeleton.mvp.util.AppConstant.IFL;
import static com.skeleton.mvp.util.AppConstant.LINK;
import static com.skeleton.mvp.util.AppConstant.REFERRER;


/**
 * Developer: Click Labs
 * Refer detailed documentation on mentioned link
 *
 * @link https://docs.google.com/document/d/1De9xunYTlPK1zvUBtoa2nzI-5o5bU51eo1-0lBYx-Z8/edit
 */
public class InviteActivity extends BaseActivity implements InviteView, View.OnClickListener {
    private InvitePresenter invitePresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        initViews();
    }

    /**
     * Used to initialise Views
     */
    private void initViews() {
        invitePresenter = new InvitePresenterImpl(this, new InviteInteractorImpl(RestClient.getFirebaseApiInterface()));
        invitePresenter.onAttach();
        findViewById(R.id.btnInvite).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        invitePresenter.onDetach();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnInvite:
                invitePresenter.onInviteClicked(getString(R.string.google_api_key), getLongLink());
                break;
            default:
                break;
        }
    }

    /**
     * Used to get Long Link
     *
     * @return longLink as String
     */
    private String getLongLink() {
        String firebaseDynamicLinkDomain = "https://g9qbu.app.goo.gl?";
        String websiteUrl = "http://kurdtaxi.fairtrade-iq.com/";
        String androidFallbackLink = "https://play.google.com/store/apps/details?id=com.kurdTaxi.cp&hl=en";
        String iosFallbackLink = "https://itunes.apple.com/us/app/kurdtaxi/id1297253538?ls=1&mt=8";
        return firebaseDynamicLinkDomain + LINK + websiteUrl + APN + this.getPackageName()
                + AFL + androidFallbackLink + IFL + iosFallbackLink + REFERRER + "abc123";


    }

    /**
     * Used to send Intent for referral link
     *
     * @param shortLink link to be shared
     */
    @Override
    public void shareLink(final String shortLink) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        String message = getString(R.string.has_send_you_a_referral_link) + getString(R.string.app_name) + "\n" + shortLink;

        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
    }

    @Override
    public void showNetworkError() {
        new AlertDialog.Builder(InviteActivity.this)
                .setMessage(R.string.error_internet_not_connected)
                .setCancelable(false)
                .setPositiveButton(R.string.text_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        invitePresenter.onInviteClicked(getString(R.string.google_api_key), getLongLink());
                    }
                })
                .show();
    }
}
