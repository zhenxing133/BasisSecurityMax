package com.ithomework.basissecuritymax.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.ithomework.basissecuritymax.R;
import com.ithomework.basissecuritymax.activity.activity.LostFindActivity;
import com.ithomework.basissecuritymax.activity.activity.SettingActivity;
import com.ithomework.basissecuritymax.activity.adapter.GridAdapter;
import com.ithomework.basissecuritymax.activity.utils.MD5Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.gridview)
    GridView gridview;
    private AlertDialog dialog;
    private EditText pwd;
    private EditText confirm_pwd;
    private SharedPreferences sp;
    private AlertDialog enter_dialog;
    private EditText enter_password;
    private Button btn_show_dismiss;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        gridview.setAdapter(new GridAdapter(getApplicationContext()));
        setGrid();
    }

    private void setGrid() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (TextUtils.isEmpty(sp.getString("pwd", ""))) {
                            showSetPasswordDialog();
                        } else {
                            setEnterPassword();
                        }

                        break;
                    case 8:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);

                        break;
                }
            }
        });
    }

    //弹出设置密码框
    private void showSetPasswordDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //设置对话框不能消失
        builder.setCancelable(false);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_setpassword, null);
        pwd = (EditText) view.findViewById(R.id.pwd);
        confirm_pwd = (EditText) view.findViewById(R.id.confirm_pwd);
        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

        //确定
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = pwd.getText().toString().trim();
                String confirm_password = confirm_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password)) {
                    Toast.makeText(getApplication(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(confirm_password)) {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("pwd", MD5Utils.digestPassWord(password));//MD5加密
                    edit.commit();
                    dialog.dismiss();
                    Toast.makeText(getApplication(),"密码设置成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(),"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    //输入密码框
    public void setEnterPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //设置对话框不能消失
        builder.setCancelable(false);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_enterpassword, null);
        enter_password = (EditText) view.findViewById(R.id.enter_password);
        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_show_dismiss = (Button) view.findViewById(R.id.btn_show_dismiss);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enter_pwd = enter_password.getText().toString().trim();

                if (TextUtils.isEmpty(enter_pwd)) {
                    Toast.makeText(getApplication(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                String sp_pwd = sp.getString("pwd", "");
                if (MD5Utils.digestPassWord(enter_pwd).equals(sp_pwd)) {
                    Toast.makeText(getApplication(),"登入成功",Toast.LENGTH_SHORT).show();
                    //跳转页面
                    Intent intent = new Intent(MainActivity.this, LostFindActivity.class);
                    startActivity(intent);
                    finish();
                    enter_dialog.dismiss();

                } else {
                    Toast.makeText(getApplication(),"密码输入错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_dialog.dismiss();
            }
        });
        builder.setView(view);
        enter_dialog = builder.create();
        enter_dialog.show();
        count = 0;
        btn_show_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //隐藏显示密码
                if (count %2 == 0) {
                    //显示密码
                    enter_password.setInputType(1);
                    btn_show_dismiss.setText("隐藏");
                }else{
                    //隐藏密码
                    enter_password.setInputType(129);
                    btn_show_dismiss.setText("显示");
                }
                //累加操作
                count++;
            }
        });
    }


}
