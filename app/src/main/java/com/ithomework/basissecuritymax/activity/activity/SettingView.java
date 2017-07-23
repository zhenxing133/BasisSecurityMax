package com.ithomework.basissecuritymax.activity.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/23.
 */

public class SettingView extends LinearLayout {

    private TextView tv_title;
    private TextView tv_des;
    private CheckBox checkBox;
    private String des_on;
    private String des_off;

    public SettingView(Context context) {
        super(context);

    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        String title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.ithomework.basissecuritymax", "title");
        des_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.ithomework.basissecuritymax", "des_on");
        des_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.ithomework.basissecuritymax", "des_off");
    }


    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        View view = View.inflate(getContext(), R.layout.view_setting, this);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

    }

    //设置标题
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    //设置内容
    public void setDes(String des) {
        tv_des.setText(des);
    }

    //设置选中
    public void setCheck(boolean isCheck) {
        checkBox.setChecked(isCheck);
        if (isCheck) {
            tv_des.setText(des_on);
        } else {
            tv_des.setText(des_off);
        }
    }

    //设置状态
    public boolean isCheck() {
        return checkBox.isChecked();
    }



}
