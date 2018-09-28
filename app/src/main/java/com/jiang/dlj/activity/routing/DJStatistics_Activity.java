package com.jiang.dlj.activity.routing;

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

import com.jiang.dlj.R;
import com.jiang.dlj.activity.Base_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.DJStatistics_Entity;
import com.jiang.dlj.servlet.Get_DJStatistics_Servlet;
import com.jiang.dlj.servlet.Set_DJStatistics_Servlet;

/**
 * @author: jiangyao
 * @date: 2018/9/21
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检总结
 */
public class DJStatistics_Activity extends Base_Activity implements View.OnClickListener {
    private static final String TAG = "DJStatistics_Activity";

    private static String Title, Guids;

    TextView title, totol, uncomplete, normal, alarm, defect;

    EditText message;

    Button submit;

    public static void start(Context context, String guids, String title) {
        Intent intent = new Intent();
        Title = title;
        Guids = guids;
        intent.setClass(context, DJStatistics_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_djstatistics);

        setTitle("巡检总结");
        setBack(true);

        initview();


    }

    /**
     * 数据返回
     *
     * @param bean
     */
    public void CallBack(DJStatistics_Entity.ResultBean bean) {
        totol.setText(String.valueOf(bean.getTotolNum()));
        uncomplete.setText(String.valueOf(bean.getUnCompleteNum()));
        normal.setText(String.valueOf(bean.getNormalNum()));
        alarm.setText(String.valueOf(bean.getAlarmNum()));
        defect.setText(String.valueOf(bean.getDefectNum()));

    }

    private void initview() {

        title = findViewById(R.id.djidpos_title);
        totol = findViewById(R.id.totolnum);
        uncomplete = findViewById(R.id.uncompletenum);
        normal = findViewById(R.id.normalnum);
        alarm = findViewById(R.id.alarnum);
        defect = findViewById(R.id.defectnum);
        message = findViewById(R.id.message);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);

        title.setText(Title);

        new Get_DJStatistics_Servlet(this).execute(Guids);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (TextUtils.isEmpty(message.getText().toString())) {
                    Base_Dialog dialog = new Base_Dialog(this);
                    dialog.setTitle("警告");
                    dialog.setMessage("确认不写总结并提交吗？");
                    dialog.setOk("少废话", view1 ->
                            new Set_DJStatistics_Servlet(DJStatistics_Activity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Guids, "null"));
                    dialog.setEsc("再想想", null);
                }
                break;
        }
    }
}
