package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJIdPosByGuids_Entity;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线的位置信息
 */
public class Get_DJIdPosByGuids_Servlet extends AsyncTask<String, Integer, DJIdPosByGuids_Entity> {
    private static final String TAG = "Get_DJIdPosByGuids_Serv";

    Activity activity;

    public Get_DJIdPosByGuids_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJIdPosByGuids_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("guids", strings[0]);

        String res = HttpUtil.doGet(Const.URL + "appDJIdPosByGuids.cpeam", map);

        DJIdPosByGuids_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new DJIdPosByGuids_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJIdPosByGuids_Entity.class);
            } catch (Exception e) {
                entity = new DJIdPosByGuids_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }


    @Override
    protected void onPostExecute(DJIdPosByGuids_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();


    }
}
