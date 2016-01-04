package com.xylitol679.demo.customviewgroup;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by tm on 2015/12/30.
 */
public class CustomScrollLayout extends ViewGroup {

    private int w = 500;
    private int h = 500;
    private Scroller mScroller;
    private float mLastX;
    private float mLastY;

    public CustomScrollLayout(Context context) {
        this(context, null);
    }

    public CustomScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mScroller = new Scroller(context);

        LinearLayout layout1 = new LinearLayout(getContext());
        layout1.setBackgroundColor(Color.YELLOW);

        LinearLayout layout2 = new LinearLayout(getContext());
        layout2.setBackgroundColor(Color.RED);

        LinearLayout layout3 = new LinearLayout(getContext());
        layout3.setBackgroundColor(Color.BLUE);

        addView(layout1);
        addView(layout2);
        addView(layout3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(w, h);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = 0;
        int top = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(left, top, left + w, top + h);

            left += w;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d("tom", "-->onInterceptTouchEvent ");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("tom", "-->ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("tom", "-->ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("tom", "-->ACTION_UP ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("tom", "-->ACTION_CANCEL ");
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("tom", "-->onTouchEvent ");
        final float x = event.getX();
        final float y = event.getY();
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("tom", "-->ACTION_DOWN ");
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("tom", "-->ACTION_MOVE ");
                scrollBy((int) (mLastX - x), (int) (mLastY - y));
                break;
            case MotionEvent.ACTION_UP:
                Log.d("tom", "-->ACTION_UP ");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("tom", "-->ACTION_CANCEL ");
                break;
            default:
                break;
        }

//        return super.onTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {

        }
    }

    public void startMove(int startX, int startY, int dx, int dy) {
        startMove(startX, startY, dx, dy, 400);
    }

    public void startMove(int startX, int startY, int dx, int dy, int duration) {
        mScroller.startScroll(startX, startY, dx, dy, duration);
        postInvalidate();
    }
}
