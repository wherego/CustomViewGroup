package com.xylitol679.demo.customviewgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private Button mLeftBtn, mTopBtn, mRightBtn, mBootomBtn;
    private CustomViewGroup mRootViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootViewGroup = (CustomViewGroup) findViewById(R.id.multi_view);
        mLeftBtn = (Button) findViewById(R.id.btn_left);
        mTopBtn = (Button) findViewById(R.id.btn_top);
        mRightBtn = (Button) findViewById(R.id.btn_right);
        mBootomBtn = (Button) findViewById(R.id.btn_bottom);

        mLeftBtn.setOnClickListener(this);
        mTopBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mBootomBtn.setOnClickListener(this);

//        mLeftBtn.setOnTouchListener(this);
//        mTopBtn.setOnTouchListener(this);
//        mRightBtn.setOnTouchListener(this);
//        mBootomBtn.setOnTouchListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                mRootViewGroup.startMove(mRootViewGroup.getScrollX(), mRootViewGroup.getScrollY(), 100, 0);
//                mRootViewGroup.invalidate();
                break;
            case R.id.btn_top:
                mRootViewGroup.startMove(mRootViewGroup.getScrollX(), mRootViewGroup.getScrollY(), 0, 100);
//                mRootViewGroup.postInvalidate();
                break;
            case R.id.btn_right:
                mRootViewGroup.startMove(mRootViewGroup.getScrollX(), mRootViewGroup.getScrollY(), -100, 0);
//                mRootViewGroup.postInvalidate();
                break;
            case R.id.btn_bottom:
                mRootViewGroup.startMove(mRootViewGroup.getScrollX(), mRootViewGroup.getScrollY(), 0, -100);
//                mRootViewGroup.postInvalidate();
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
                mRootViewGroup.scrollBy(100, 0);
                break;
            case R.id.btn_top:
                mRootViewGroup.scrollBy(0, 100);
                break;
            case R.id.btn_right:
                mRootViewGroup.scrollBy(-100, 0);
                break;
            case R.id.btn_bottom:
                mRootViewGroup.scrollBy(0, -100);
                break;
            default:
                break;
        }

        return false;
    }

}
