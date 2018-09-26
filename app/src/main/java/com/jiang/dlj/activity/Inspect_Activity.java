package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.adapter.Inspect_Adapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.DJGetChk_Entity;
import com.jiang.dlj.entity.DJIdPosByGuids_Entity;
import com.jiang.dlj.entity.DJStatisticsByIdPos_Entity;
import com.jiang.dlj.servlet.Get_DJGetChk_Servlet;
import com.jiang.dlj.servlet.Get_DJStatisticsByIdPos_Servlet;

/**
 * @author: jiangyao
 * @date: 2018/9/25
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 检查项
 */
public class Inspect_Activity extends Base_Activity implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {
    private static final String TAG = "Inspect_Activity";

    private static String Guids;

    private static DJIdPosByGuids_Entity.ResultBean resultBean;

    public static void start(Context context, String guids, DJIdPosByGuids_Entity.ResultBean bean) {
        Intent intent = new Intent();
        intent.setClass(context, Inspect_Activity.class);
        Guids = guids;
        resultBean = bean;
        context.startActivity(intent);
    }

    TextView  total, complete, surplus;
    SwipeRefreshLayout sr;
    ListView list;
    LinearLayout view_null;

    Inspect_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect);

        setTitle(resultBean.getIdpos_name());
        setBack(true);

        initview();

    }

    private void initview() {

        total = findViewById(R.id.inspect_total);
        complete = findViewById(R.id.inspect_complete);
        surplus = findViewById(R.id.inspect_surplus);
        sr = findViewById(R.id.inspect_sr);
        list = findViewById(R.id.inspect_list);
        view_null = findViewById(R.id.view_null);

        sr.setOnRefreshListener(this);

        sr.setRefreshing(true);
        adapter = new Inspect_Adapter(this);
        list.setAdapter(adapter);
        list.setOnScrollListener(this);

    }

    @Override
    protected void onResume() {
        onRefresh();
        super.onResume();
    }

    @Override
    public void onRefresh() {
        //查询检测项列表
        new Get_DJGetChk_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Guids, resultBean.getIdpos_id());

        //查询统计
        new Get_DJStatisticsByIdPos_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Guids, resultBean.getIdpos_id());

    }

    /**
     * 数据返回
     *
     * @param bean
     */
    public void CallBack(DJStatisticsByIdPos_Entity.ResultBean bean) {
        if (bean != null) {
            total.setText("总数：" + bean.getTotolNum());
            complete.setText("已完成：" + bean.getCompleteNum());
            surplus.setText("剩余：" + bean.getUnCompleteNum());
        }
    }

    /**
     * 数据返回
     *
     * @param entity
     */
    public void Callback(DJGetChk_Entity entity) {
        sr.setRefreshing(false);

        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult() != null && entity.getResult().size() > 0) {
                    view_null.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);

                    adapter.setBeans(entity.getResult());
                    adapter.notifyDataSetChanged();

                    list.setOnItemClickListener((adapterView, view, i, l) -> {
                        Test_Activity.start(Inspect_Activity.this, Guids, entity.getResult().get(i));
                    });


                } else {
                    view_null.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);
                }
                break;
            default:

                view_null.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);

                Base_Dialog dialog = new Base_Dialog(this);
                dialog.setTitle("抱歉");
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);
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
}
