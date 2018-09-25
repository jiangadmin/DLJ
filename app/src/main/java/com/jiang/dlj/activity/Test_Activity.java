package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiang.dlj.R;
import com.jiang.dlj.adapter.Test_Adapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Choose_Run_State_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.DJGetChk_Entity;
import com.jiang.dlj.servlet.Set_DJSubmit_Servlet;
import com.jiang.dlj.utils.LogUtil;
import com.jiang.dlj.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 测项
 */

public class Test_Activity extends Base_Activity implements View.OnClickListener {
    private static final String TAG = "Test_Activity";

    private static String Guids;

    private static DJGetChk_Entity.ResultBean bean;

    Test_Adapter test_adapter;
    ListViewForScrollView listViewForScrollView;
    Button submit;
    TextView run_state;
    EditText message;

    public static void start(Context context, String guids, DJGetChk_Entity.ResultBean resultBean) {
        Intent intent = new Intent();
        intent.setClass(context, Test_Activity.class);
        Guids = guids;
        bean = resultBean;
        context.startActivity(intent);
    }

    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("测项");
        setBack(true);

        initview();
    }

    private void initview() {
        title = findViewById(R.id.test_title);
        run_state = findViewById(R.id.run_state);
        listViewForScrollView = findViewById(R.id.test_list);
        message = findViewById(R.id.test_message);
        submit = findViewById(R.id.submit);

        title.setText(bean.getDjchk_name());
        test_adapter = new Test_Adapter(this);
        test_adapter.setBeans(bean.getChkDetails());
        listViewForScrollView.setAdapter(test_adapter);

        submit.setOnClickListener(this);
        if (bean.getChkDetails().get(0) != null) {
            switch (bean.getChkDetails().get(0).getRun_state()) {
                //运行
                case 0:
                    run_state.setText("运行");
                    break;
                //检修
                case 1:
                    run_state.setText("检修");
                    break;
                //停运
                case 2:
                    run_state.setText("停运");
                    break;
            }

            if (bean.getChkDetails().get(0).getRemark() != null) {
                message.setText(bean.getChkDetails().get(0).getRemark());
            }
        }

        run_state.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.run_state:
                new Choose_Run_State_Dialog(this, run_state);
                break;
            case R.id.submit:
                String remark = message.getText().toString().trim();
                if (TextUtils.isEmpty(remark)) {
                    Base_Dialog dialog = new Base_Dialog(this);
                    dialog.setMessage("不写备注直接提交吗？");
                    dialog.setOk("少废话", view1 -> {
                        Commit("无");
                    });
                    dialog.setEsc("再想想", null);
                } else {
                    Commit(remark);
                }

                break;
        }
    }

    /**
     * 提交数据
     *
     * @param remark
     */
    public void Commit(String remark) {

        Loading.show(this, "提交中");
        List<Map> maps = new ArrayList<>();

        for (DJGetChk_Entity.ResultBean.ChkDetailsBean detailsBean : bean.getChkDetails()) {
            Map map = new HashMap();
            map.put("taskdetail_guid", detailsBean.getTaskdetail_guid());
            map.put("dj_result", bean.getDj_method() == 1 ? detailsBean.getDj_reslut() : detailsBean.getState());

            maps.add(map);
        }

        String s = new Gson().toJson(maps);
        Set_DJSubmit_Servlet.Info info = new Set_DJSubmit_Servlet.Info();
        info.setGuids(Guids);
        info.setDjchk_id(bean.getDjchk_id());
        info.setResultDetails(s);
        info.setRemark(remark);
        switch (run_state.getText().toString()) {
            case "运行":
                info.setRun_state("0");
                break;
            case "检修":
                info.setRun_state("1");
                break;
            case "停运":
                info.setRun_state("2");
                break;
        }

        new Set_DJSubmit_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, info);

    }
}
