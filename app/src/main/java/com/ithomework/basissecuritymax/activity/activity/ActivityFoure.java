package com.ithomework.basissecuritymax.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ActivityFoure extends BaseActivitye {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foure);
    }

    @Override
    public void preActivity() {
        Intent intent = new Intent(ActivityFoure.this, ActivityThree.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void nextActivity() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("first", true);
        edit.commit();
        Intent intent = new Intent(ActivityFoure.this, LostFindActivity.class);
        startActivity(intent);
        finish();
    }
}
