package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.utils.ToolUtils;

/**
 * @author: jiangadmin
 * @date: 2018/9/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 设置
 */
public class About_Activity extends Base_Activity {
    private static final String TAG = "About_Activity";


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, About_Activity.class);
        context.startActivity(intent);
    }

    TextView ver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setBack(true);
        setTitle("关于");

        initview();
    }

    private void initview() {
        ver = findViewById(R.id.about_ver);
        ver.setText("V:" + ToolUtils.getVersionName());
    }


}
