package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.jiang.dlj.R;

/**
 * @author: jiangadmin
 * @date: 2018/9/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 设置
 */
public class Setting_Activity extends Base_Activity implements View.OnClickListener {
    private static final String TAG = "Setting_Activity";

    Button clear, input_eye, update_password, about;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Setting_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setBack(true);
        setTitle("设置");

        initview();
    }

    private void initview() {
        clear = findViewById(R.id.clear);
        input_eye = findViewById(R.id.input_eye);
        update_password = findViewById(R.id.update_password);
        about = findViewById(R.id.about);

        clear.setOnClickListener(this);
        input_eye.setOnClickListener(this);
        update_password.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                break;
            case R.id.input_eye:
                break;
            case R.id.update_password:
                break;
            case R.id.about:
                break;
        }
    }
}
