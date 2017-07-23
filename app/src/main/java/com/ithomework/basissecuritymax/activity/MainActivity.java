package com.ithomework.basissecuritymax.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ithomework.basissecuritymax.R;
import com.ithomework.basissecuritymax.activity.activity.SettingActivity;
import com.ithomework.basissecuritymax.activity.adapter.GridAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.gridview)
    GridView gridview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        gridview.setAdapter(new GridAdapter(getApplicationContext()));
        setGrid();
    }

    private void setGrid() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 8:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);

                        break;
                }
            }
        });
    }


}
