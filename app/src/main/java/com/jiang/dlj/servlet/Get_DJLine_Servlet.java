package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.activity.routing.Tour_Route_Activity;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJLine_Entity;
import com.jiang.dlj.entity.Save_Key;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;
import com.jiang.dlj.utils.SaveUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/9/18
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线
 */
public class Get_DJLine_Servlet extends AsyncTask<String, Integer, DJLine_Entity> {
    private static final String TAG = "Get_DJLine_Servlet";

    Activity activity;

    public Get_DJLine_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJLine_Entity doInBackground(String... strings) {
        Map map = new HashMap();

        map.put("userid", SaveUtils.getString(Save_Key.UserId));//用户ID
        map.put("state", strings[0]);//状态 0 未巡检，1巡检中， 2完成

        String res = HttpUtil.doGet(Const.URL + "appDJLine.cpeam", map);

        DJLine_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new DJLine_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJLine_Entity.class);
            } catch (Exception e) {
                entity = new DJLine_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(DJLine_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        if (activity instanceof Tour_Route_Activity) {
            ((Tour_Route_Activity) activity).CallBack(entity);
        }

    }
}
