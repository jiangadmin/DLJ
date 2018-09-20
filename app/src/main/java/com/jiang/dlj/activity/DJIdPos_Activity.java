package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线的位置
 */
public class DJIdPos_Activity extends Base_Activity {
    private static final String TAG = "DJIdPos_Activity";

    public static void start(Context context) {
        Intent intent= new Intent();
        intent.setClass(context,DJIdPos_Activity.class);
    }
}
