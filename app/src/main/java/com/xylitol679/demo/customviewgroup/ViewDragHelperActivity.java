package com.xylitol679.demo.customviewgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ViewDragHelperActivity extends AppCompatActivity {

    private CustomDragLayout mCustomDragLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdraghelper);

        mCustomDragLayout = (CustomDragLayout) findViewById(R.id.drag_layout);
    }

}
