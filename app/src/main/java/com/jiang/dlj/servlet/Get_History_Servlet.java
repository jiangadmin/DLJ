package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.activity.routing.Test_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.History_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/9/27
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检历史值
 */
public class Get_History_Servlet extends AsyncTask<String, Integer, History_Entity> {
    private static final String TAG = "Get_History_Servlet";

    Activity activity;

    public Get_History_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected History_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("taskdetail_guid", strings[0]);
        map.put("query_date", new Date(System.currentTimeMillis()).toString());

        String res = HttpUtil.doGet(Const.URL + "appDJHisData.cpeam", map);
        History_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new History_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, History_Entity.class);
            } catch (Exception e) {
                entity = new History_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(History_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (activity instanceof Test_Activity){

                }
                break;
            default:
                Base_Dialog dialog = new Base_Dialog(activity);
                dialog.setTitle("抱歉");
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);
                break;
        }
    }
}
