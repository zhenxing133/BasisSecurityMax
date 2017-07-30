package com.ithomework.basissecuritymax.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ithomework.basissecuritymax.R;
import com.ithomework.basissecuritymax.activity.MainActivity;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ActivityOne extends BaseActivitye {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    @Override
    public void preActivity() {
        Intent intent = new Intent(ActivityOne.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
        startActivity(intent);
        finish();
    }
}
