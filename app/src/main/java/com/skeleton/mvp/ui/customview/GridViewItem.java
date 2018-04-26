package com.skeleton.mvp.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Custom view to make width and height of items equal
 */
public class GridViewItem extends LinearLayout {

    /**
     * Constructor
     *
     * @param context context
     */
    public GridViewItem(final Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context context
     * @param attrs   attribute set
     */
    public GridViewItem(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context  context
     * @param attrs    attribute set
     * @param defStyle style
     */
    public GridViewItem(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        // This is the key that will make the height equivalent to its width
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}