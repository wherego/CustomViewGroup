package com.xylitol679.demo.customviewgroup;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tm on 2016/1/4.
 */
public class CustomDragLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    private Point mAutoBackOriginPos = new Point();

    public CustomDragLayout(Context context) {
        this(context, null);
    }

    public CustomDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelperCallBack());
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("tom", "-->onInterceptTouchEvent");

        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("tom", "-->onTouchEvent");

        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d("tom", "-->pointerId: " + pointerId);

            if (child == mDragView || child == mAutoBackView) {
                return true;
            } else {
                return false;
            }

        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("tom", "-->left: " + left);

            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - child.getWidth() - getPaddingRight();

            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d("tom", "-->top: " + top);

            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();

            final int newTop = Math.min(Math.max(top, topBound), bottomBound);

            return newTop;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.d("tom", "-->onViewReleased");

            if (releasedChild == mAutoBackView) {
                mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                invalidate();
            }

        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.d("tom", "-->onEdgeDragStarted");

            mViewDragHelper.captureChildView(mEdgeTrackerView, pointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }
    }
}
