package com.skeleton.mvp.ui.base.baserecycler;

/**
 * Created by soc-lap-18k.n on 1/29/18.
 */
public interface ActionItemListener {

    /**
     * On retry.
     */
    void onRetry();

    /**
     * On Loading more data
     */
    void onLoadMore();

    /**
     * On retry.
     *
     * @param id Item Id
     */
    void onItemClick(final String id);
}
