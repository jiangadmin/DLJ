package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jiang.dlj.R;
import com.jiang.dlj.adapter.DJIdPos_Adapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.DJIdPosByGuids_Entity;
import com.jiang.dlj.scan.Scan_Activity;
import com.jiang.dlj.servlet.Get_DJIdPos_Servlet;
import com.jiang.dlj.servlet.Get_Ready_Servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线的位置
 */
public class DJIdPos_Activity extends Base_Activity implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, AbsListView.OnScrollListener {
    private static final String TAG = "DJIdPos_Activity";

    static String Guids, Title;
    static int nowtype;

    SwipeRefreshLayout sr;

    ListView line_list;

    LinearLayout view_null;

    DJIdPos_Adapter adapter;

    FloatingActionButton scan;

    List<DJIdPosByGuids_Entity.ResultBean> resultBeanList = new ArrayList<>();

    public static void start(Context context, String guids, String title, int type) {
        Intent intent = new Intent();
        intent.setClass(context, DJIdPos_Activity.class);
        Guids = guids;
        Title = title;
        nowtype = type;
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djidpos);
        setTitle(Title);
        setBack(true);
        setMenu("总结");

        initview();

        onRefresh();

        //待检
        if (nowtype == 0) {
            //巡检准备
            new Get_Ready_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Guids);
        }
    }

    private void initview() {
        sr = findViewById(R.id.line_sr);
        line_list = findViewById(R.id.line_list);
        view_null = findViewById(R.id.view_null);
        scan = findViewById(R.id.djidpos_scan);

        sr.setRefreshing(true);
        sr.setOnRefreshListener(this);
        scan.setOnClickListener(this);

        adapter = new DJIdPos_Adapter(this);
        line_list.setAdapter(adapter);
        line_list.setOnItemClickListener(this);
        line_list.setOnScrollListener(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case 139:
                Scan_QR();
                break;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 数据返回
     *
     * @param entity
     */
    public void CallBack(DJIdPosByGuids_Entity entity) {
        sr.setRefreshing(false);
        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult() != null && entity.getResult().size() > 0) {
                    view_null.setVisibility(View.GONE);
                    line_list.setVisibility(View.VISIBLE);

                    resultBeanList.clear();
                    resultBeanList.addAll(entity.getResult());

                    adapter.setListData(resultBeanList);
                    adapter.notifyDataSetChanged();

                } else {

                    view_null.setVisibility(View.VISIBLE);
                    line_list.setVisibility(View.GONE);

                    resultBeanList.clear();

                    adapter.setListData(resultBeanList);
                    adapter.notifyDataSetChanged();

                }
                break;
            default:
                view_null.setVisibility(View.VISIBLE);
                line_list.setVisibility(View.GONE);
                Base_Dialog dialog = new Base_Dialog(this);
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Inspect_Activity.start(this, Guids, resultBeanList.get(i));

    }

    @Override
    public void onRefresh() {
        resultBeanList.clear();
        adapter.setListData(resultBeanList);
        adapter.notifyDataSetChanged();
        new Get_DJIdPos_Servlet(this).execute(Guids);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                DJStatistics_Activity.start(this, Guids, Title);
                break;
            case R.id.djidpos_scan:
                Scan_QR();
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        // 当不滚动时
        if (i == SCROLL_STATE_IDLE) {
            //判断顶部
            if (absListView.getChildCount() > 0 && absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop()) {
                sr.setEnabled(true);
            } else {
                sr.setEnabled(false);
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    /**
     * 启动激光扫码
     */
    public void Scan_QR() {
        Base_Dialog dialog = new Base_Dialog(this);
        dialog.setMessage("左右橘黄色按钮可快速启动激光扫码，请勿直接照射眼睛，调试中，暂未开放");
        dialog.setOk("好", null);
        dialog.setEsc("先瞅瞅", view -> Scan_Activity.start(DJIdPos_Activity.this));
    }
}
