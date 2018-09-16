package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jiang.dlj.R;

/**
 * @author: jiangadmin
 * @date: 2018/9/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */
public class Demo_Activity extends Base_Activity {
    private static final String TAG = "Demo_Activity";

    static String title;

    TextView textView;

    public static void start(Context context,String s) {
        Intent intent = new Intent();
        intent.setClass(context,Demo_Activity.class);
        title = s;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        textView = findViewById(R.id.title);

        textView.setText(title);

    }
}
