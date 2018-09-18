package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiang.dlj.MyApp;
import com.jiang.dlj.R;
import com.jiang.dlj.servlet.Login_Servlet;
import com.jiang.dlj.utils.TabToast;

/**
 * @author: jiangadmin
 * @date: 2018/9/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:登录
 */
public class Login_Activity extends Base_Activity implements View.OnClickListener {
    private static final String TAG = "Login_Activity";

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Login_Activity.class);
        context.startActivity(intent);
    }

    EditText username, password;

    TextView forgetpasswrod;

    CheckBox save_password;

    Button login_submit, login_eye;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initview();
    }

    private void initview() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        forgetpasswrod = findViewById(R.id.login_forget_password);
        save_password = findViewById(R.id.login_save);
        login_submit = findViewById(R.id.login_submit);
        login_eye = findViewById(R.id.login_eye);

        forgetpasswrod.setOnClickListener(this);
        login_submit.setOnClickListener(this);
        login_eye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_forget_password:
                TabToast.makeText("暂未开放");
                break;
            case R.id.login_submit:
//                new Login_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"admin","admin","admin","admin");
                Main_Activity.start(this);
                MyApp.finishActivity();
                break;
            case R.id.login_eye:
                TabToast.makeText("暂未开放");
                break;
        }
    }
}
