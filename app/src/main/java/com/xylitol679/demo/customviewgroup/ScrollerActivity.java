package com.xylitol679.demo.customviewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ScrollerActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private Button mLeftBtn, mTopBtn, mRightBtn, mBootomBtn, mResetBtn, mGunBtn;
    private CustomScrollLayout mCustomScrollLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

        mCustomScrollLayout = (CustomScrollLayout) findViewById(R.id.scroll_layout);
        mLeftBtn = (Button) findViewById(R.id.btn_left);
        mTopBtn = (Button) findViewById(R.id.btn_top);
        mRightBtn = (Button) findViewById(R.id.btn_right);
        mBootomBtn = (Button) findViewById(R.id.btn_bottom);
        mResetBtn = (Button) findViewById(R.id.btn_reset);
        mGunBtn = (Button) findViewById(R.id.btn_gun);

        mLeftBtn.setOnClickListener(this);
        mTopBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mBootomBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
        mGunBtn.setOnClickListener(this);

//        mLeftBtn.setOnTouchListener(this);
//        mTopBtn.setOnTouchListener(this);
//        mRightBtn.setOnTouchListener(this);
//        mBootomBtn.setOnTouchListener(this);
//        mResetBtn.setOnTouchListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                mCustomScrollLayout.startMove(mCustomScrollLayout.getScrollX(), mCustomScrollLayout.getScrollY(), 100, 0);
                break;
            case R.id.btn_top:
                mCustomScrollLayout.startMove(mCustomScrollLayout.getScrollX(), mCustomScrollLayout.getScrollY(), 0, 100);
                break;
            case R.id.btn_right:
                mCustomScrollLayout.startMove(mCustomScrollLayout.getScrollX(), mCustomScrollLayout.getScrollY(), -100, 0);
                break;
            case R.id.btn_bottom:
                mCustomScrollLayout.startMove(mCustomScrollLayout.getScrollX(), mCustomScrollLayout.getScrollY(), 0, -100);
                break;
            case R.id.btn_reset:
                mCustomScrollLayout.scrollTo(0, 0);
                break;
            case R.id.btn_gun:
                startActivity(new Intent(ScrollerActivity.this, ViewDragHelperActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("tom", "-->onTouch ");
        switch (v.getId()) {
            case R.id.btn_left:
                mCustomScrollLayout.scrollBy(100, 0);
                break;
            case R.id.btn_top:
                mCustomScrollLayout.scrollBy(0, 100);
                break;
            case R.id.btn_right:
                mCustomScrollLayout.scrollBy(-100, 0);
                break;
            case R.id.btn_bottom:
                mCustomScrollLayout.scrollBy(0, -100);
                break;
            default:
                break;
        }

        return false;
    }

}
