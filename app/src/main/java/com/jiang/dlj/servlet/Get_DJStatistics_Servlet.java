package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.MyApp;
import com.jiang.dlj.activity.routing.DJStatistics_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJStatistics_Entity;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/21
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检结果统计
 */
public class Get_DJStatistics_Servlet extends AsyncTask<String, Integer, DJStatistics_Entity> {
    private static final String TAG = "Get_DJStatistics_Servle";

    Activity activity;

    public Get_DJStatistics_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJStatistics_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("guids", strings[0]);

        String res = HttpUtil.doGet(Const.URL + "appDJStatisticsData.cpeam", map);

        DJStatistics_Entity entity;

        if (TextUtils.isEmpty(res)) {
            entity = new DJStatistics_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJStatistics_Entity.class);
            } catch (Exception e) {
                entity = new DJStatistics_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(DJStatistics_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (activity instanceof DJStatistics_Activity){
                    ((DJStatistics_Activity) activity).CallBack(entity.getResult());
                }
                break;
            default:
                Base_Dialog dialog = new Base_Dialog(activity);
                dialog.setTitle("抱歉");
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("确认",view -> MyApp.finishActivity());
                break;
        }

    }
}
