package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jiang.dlj.R;
import com.jiang.dlj.adapter.DJLine_Adapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.DJLine_Entity;
import com.jiang.dlj.servlet.Get_DJLine_Servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/9/17
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡回路线
 */
public class Tour_Route_Activity extends Base_Activity implements SwipeRefreshLayout.OnRefreshListener, TabLayout.OnTabSelectedListener, AdapterView.OnItemClickListener {
    private static final String TAG = "Tour_Route_Activity";

    SwipeRefreshLayout sr;
    TabLayout tour_route_type;
    ListView listView;
    LinearLayout view_null;

    int nowtype = 0;

    DJLine_Adapter djLine_adapter;

    List<DJLine_Entity.ResultBean> resultBeans = new ArrayList<>();

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

        djLine_adapter = new DJLine_Adapter(this);
        djLine_adapter.setListData(resultBeans);
        listView.setAdapter(djLine_adapter);

        new Get_DJLine_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(nowtype));

    }

    private void initview() {
        sr = findViewById(R.id.tour_route_sr);
        tour_route_type = findViewById(R.id.tour_route_type);
        listView = findViewById(R.id.tour_route_list);
        view_null = findViewById(R.id.view_null);

        sr.setOnRefreshListener(this);

        tour_route_type.addOnTabSelectedListener(this);

    }

    @Override
    public void onRefresh() {
        view_null.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        resultBeans.clear();
        djLine_adapter.notifyDataSetChanged();

        new Get_DJLine_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(nowtype));

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        sr.setRefreshing(true);
        nowtype = tab.getPosition();
        onRefresh();

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void CallBack(DJLine_Entity entity) {
        sr.setRefreshing(false);
        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult() != null && entity.getResult().size() > 0) {
                    resultBeans.clear();
                    resultBeans.addAll(entity.getResult());
                    djLine_adapter.setListData(entity.getResult());

                    djLine_adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener(this);
                } else {
                    view_null.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                break;
            default:
                view_null.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                Base_Dialog dialog = new Base_Dialog(this);
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("确定", null);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DJIdPos_Activity.start(this,resultBeans.get(position).getGuids());
    }
}
