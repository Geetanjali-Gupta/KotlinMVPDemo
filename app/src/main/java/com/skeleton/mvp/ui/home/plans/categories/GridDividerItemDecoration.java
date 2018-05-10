package com.skeleton.mvp.ui.home.plans.categories;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skeleton.mvp.R;

/**
 * Adds interior dividers to a RecyclerView with a GridLayoutManager.
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {android.R.attr.listDivider};
    private static final int SPACING = 10;

    private Drawable mDivider;
    private int mInsets;

    /**
     * Constructor
     *
     * @param context context of calling class
     */
    GridDividerItemDecoration(final Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

        mInsets = context.getResources().getDimensionPixelSize(R.dimen.spacing_xsmall);
    }

    @Override
    public void onDrawOver(final Canvas c, final RecyclerView parent, final RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }


    /**
     * Draw dividers at each expected grid interval
     *
     * @param c      canvas
     * @param parent parent View
     */
    private void drawVertical(final Canvas c, final RecyclerView parent) {
        if (parent.getChildCount() == 0) {
            return;
        }

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mInsets;
            final int right = child.getRight() + params.rightMargin + mInsets;
            final int top = child.getBottom() + params.bottomMargin + mInsets;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    /**
     * Draw dividers to the right of each child view
     *
     * @param c      canvas
     * @param parent parent View
     */
    private void drawHorizontal(final Canvas c, final RecyclerView parent) {
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + params.rightMargin + mInsets;
            final int right = left + mDivider.getIntrinsicWidth();
            final int top = child.getTop() - params.topMargin - mInsets;
            final int bottom = child.getBottom() + params.bottomMargin + mInsets;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent, final RecyclerView.State state) {
        //We can supply forced insets for each item view here in the Rect
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}