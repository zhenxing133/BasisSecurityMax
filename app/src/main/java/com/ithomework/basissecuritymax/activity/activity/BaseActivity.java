package com.ithomework.basissecuritymax.activity.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/24.
 */

abstract class BaseActivitye extends AppCompatActivity {

    private GestureDetector detector;

    protected SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detector = new GestureDetector(getApplicationContext(), new GestrueListener());
        sp = getSharedPreferences("config", MODE_PRIVATE);
    }

    public void pre(View view) {
        preActivity();
    }



    public void next(View view) {
        nextActivity();
    }


    public abstract void preActivity() ;



    public abstract void nextActivity() ;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //true:屏蔽事件，拦截事件
            //return true;
            preActivity();
        }
        return super.onKeyDown(keyCode, event);
    }

    class GestrueListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //获取按下的x坐标
            float startX = e1.getRawX();
            //获取抬起的x坐标
            float endX = e2.getRawX();
            //获取按下的y坐标
            float startY = e1.getRawY();
            //获取抬起的y坐标
            float endY = e2.getRawY();
            //判断startY和endY之间的距离，如果大于50就不去滑动
            if (Math.abs((startY-endY)) > 150) {
                Toast.makeText(getApplicationContext(), "上下滑", Toast.LENGTH_SHORT).show();
                return false;
            }
            //下一步
            if ((startX-endX) > 100) {
                nextActivity();
            }
            //上一步
            if ((endX-startX) > 100) {
                preActivity();
            }
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
