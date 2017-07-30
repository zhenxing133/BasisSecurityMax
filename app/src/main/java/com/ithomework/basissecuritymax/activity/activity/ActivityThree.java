package com.ithomework.basissecuritymax.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ActivityThree extends BaseActivitye {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }

    @Override
    public void preActivity() {
        Intent intent = new Intent(ActivityThree.this, ActivityTwo.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(ActivityThree.this, ActivityFoure.class);
        startActivity(intent);
        finish();
    }
}
