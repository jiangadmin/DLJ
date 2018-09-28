package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.activity.routing.Inspect_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJGetChk_Entity;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 根据位置获取测项详情
 */
public class Get_DJGetChk_Servlet extends AsyncTask<String, Integer, DJGetChk_Entity> {
    private static final String TAG = "Get_DJGetChk_Servlet";

    Activity activity;

    public Get_DJGetChk_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJGetChk_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("guids", strings[0]);
        map.put("idpos_id", strings[1]);

        String res = HttpUtil.doGet(Const.URL + "appDJGetChkByIdpos.cpeam", map);

        DJGetChk_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new DJGetChk_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJGetChk_Entity.class);
            } catch (Exception e) {
                entity = new DJGetChk_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;

    }

    @Override
    protected void onPostExecute(DJGetChk_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (activity instanceof Inspect_Activity) {
                    ((Inspect_Activity) activity).Callback(entity);
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
