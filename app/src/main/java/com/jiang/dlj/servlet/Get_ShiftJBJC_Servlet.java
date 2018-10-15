package com.jiang.dlj.servlet;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.ShiftJBJC_Entity;
import com.jiang.dlj.entity.ShiftLog_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 交班检查查询
 */

public class Get_ShiftJBJC_Servlet extends AsyncTask<String, Integer, ShiftJBJC_Entity> {
    private static final String TAG = "Get_ShiftJBJC_Servlet";

    @Override
    protected ShiftJBJC_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("shift_log_id",strings[0]);
        String res = HttpUtil.doGet(Const.URL + "appGetShiftJBJC.cpeam",map);

        ShiftJBJC_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new ShiftJBJC_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, ShiftJBJC_Entity.class);
            } catch (Exception e) {
                entity = new ShiftJBJC_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(ShiftJBJC_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
    }
}
