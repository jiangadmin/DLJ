package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.jiang.dlj.R;

/**
 * @author: jiangadmin
 * @date: 2018/9/17
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡回路线
 */
public class Tour_Route_Activity extends Base_Activity {
    private static final String TAG = "Tour_Route_Activity";

    TabLayout tour_route_type;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Tour_Route_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tour_route);
        setBack(true);
        setTitle("巡检路线");

        initview();

    }

    private void initview() {

    }
}
