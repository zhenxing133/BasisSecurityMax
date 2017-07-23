package com.ithomework.basissecuritymax.activity.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/23.
 */

public class SettingActivity extends AppCompatActivity {

    private SettingView setting_view;
    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        setting_view = (SettingView) findViewById(R.id.setting_view);
        update();

    }

    private void update() {
        if (sp.getBoolean("update", true)) {
            setting_view.setDes("打开提示更新");
            setting_view.setCheck(true);
        } else {
            setting_view.setDes("关闭提示更新");
            setting_view.setCheck(false);
        }

        setting_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sp.edit();
                if (setting_view.isCheck()) {
                    setting_view.setCheck(false);
                    edit.putBoolean("update", false);
                } else {
                    setting_view.setCheck(true);
                    edit.putBoolean("update", true);
                }
                edit.commit();
            }


        });
    }
}
