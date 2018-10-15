package com.jiang.dlj.servlet;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.ShiftBQH_Entity;
import com.jiang.dlj.entity.ShiftDKJD_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 班前会查询
 */

public class Get_ShiftBQH_Servlet extends AsyncTask<String, Integer, ShiftBQH_Entity> {
    private static final String TAG = "Get_ShiftBQH_Servlet";

    @Override
    protected ShiftBQH_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("shift_log_id",strings[0]);
        String res = HttpUtil.doGet(Const.URL + "appGetShiftBQH.cpeam",map);

        ShiftBQH_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new ShiftBQH_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, ShiftBQH_Entity.class);
            } catch (Exception e) {
                entity = new ShiftBQH_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(ShiftBQH_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
    }
}
