package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiang.dlj.R;
import com.jiang.dlj.servlet.Get_DJIdPosByGuids_Servlet;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线的位置
 */
public class DJIdPos_Activity extends Base_Activity {
    private static final String TAG = "DJIdPos_Activity";

    static  String Guids;
    public static void start(Context context,String guids) {
        Intent intent = new Intent();
        intent.setClass(context, DJIdPos_Activity.class);
        Guids = guids;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djidpos);
        setTitle("路线信息");
        setBack(true);

        new Get_DJIdPosByGuids_Servlet(this).execute(Guids);

    }
}
