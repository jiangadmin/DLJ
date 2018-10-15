package com.jiang.dlj.servlet;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Base_Entity;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.ShiftLog_Entity;
import com.jiang.dlj.utils.HttpUtil;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:交接班班次及人员查询
 */
public class Get_ShiftLog_Servlet extends AsyncTask<String, Integer, ShiftLog_Entity> {
    private static final String TAG = "Get_ShiftLog_Servlet";

    @Override
    protected ShiftLog_Entity doInBackground(String... strings) {
        String res = HttpUtil.doGet(Const.URL + "appGetShiftLog.cpeam");

        ShiftLog_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new ShiftLog_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, ShiftLog_Entity.class);
            } catch (Exception e) {
                entity = new ShiftLog_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(ShiftLog_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
    }
}
