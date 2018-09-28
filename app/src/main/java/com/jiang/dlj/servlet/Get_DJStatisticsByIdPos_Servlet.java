package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.activity.routing.Inspect_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJStatisticsByIdPos_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/25
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:根据位置获取巡检结果统计
 */
public class Get_DJStatisticsByIdPos_Servlet extends AsyncTask<String, Integer, DJStatisticsByIdPos_Entity> {
    private static final String TAG = "Get_DJStatisticsByIdPos";

    Activity activity;

    public Get_DJStatisticsByIdPos_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJStatisticsByIdPos_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("guids", strings[0]);
        map.put("idpos_id", strings[1]);

        String res = HttpUtil.doGet(Const.URL + "appDJStatisticsByIdPos.cpeam", map);

        DJStatisticsByIdPos_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new DJStatisticsByIdPos_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJStatisticsByIdPos_Entity.class);
            } catch (Exception e) {
                entity = new DJStatisticsByIdPos_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(DJStatisticsByIdPos_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (activity instanceof Inspect_Activity){
                    ((Inspect_Activity) activity).CallBack(entity.getResult());
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
