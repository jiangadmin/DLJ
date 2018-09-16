package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.dlj.R;

/**
 * @author: jiangadmin
 * @date: 2018/9/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 设置
 */
public class Setting_Activity extends Base_Activity {
    private static final String TAG = "Setting_Activity";

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Setting_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}