package com.ithomework.basissecuritymax.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/23.
 */

public class LostFindActivity extends AppCompatActivity {
    private SharedPreferences sp ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        if (sp.getBoolean("first", true)) {
            Intent intent = new Intent(LostFindActivity.this, ActivityOne.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_lostfind);
        }

    }
}
