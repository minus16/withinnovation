package com.test.withinnovation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.withinnovation.R;
import com.test.withinnovation.view.base.BaseActivity;

public class ImageDetailActivity extends BaseActivity {

    private String mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = getIntent().getStringExtra("data");

        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView iv = findViewById(R.id.detail_iv);
        Glide.with(this).load(mData).into(iv);
    }
}
