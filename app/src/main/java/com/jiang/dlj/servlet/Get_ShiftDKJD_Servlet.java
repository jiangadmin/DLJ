package com.jiang.dlj.servlet;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.ShiftDKJD_Entity;
import com.jiang.dlj.entity.ShiftJBJC_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 对口交底查询
 */

public class Get_ShiftDKJD_Servlet extends AsyncTask<String, Integer, ShiftDKJD_Entity> {
    private static final String TAG = "Get_ShiftDKJD_Servlet";

    @Override
    protected ShiftDKJD_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("shift_log_id",strings[0]);
        String res = HttpUtil.doGet(Const.URL + "appGetShiftDKJD.cpeam",map);

        ShiftDKJD_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new ShiftDKJD_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, ShiftDKJD_Entity.class);
            } catch (Exception e) {
                entity = new ShiftDKJD_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(ShiftDKJD_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
    }
}
