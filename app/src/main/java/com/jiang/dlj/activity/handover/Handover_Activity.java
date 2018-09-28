package com.jiang.dlj.activity.handover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.dlj.activity.Base_Activity;

/**
 * @author: jiangadmin
 * @date: 2018/9/28
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 交接班
 */
public class Handover_Activity extends Base_Activity {
    private static final String TAG = "Handover_Activity";

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Handover_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
