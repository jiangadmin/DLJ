package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Base_Entity;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/9/18
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线
 */
public class Get_DJLine_Servlet extends AsyncTask<String, Integer, Base_Entity> {
    private static final String TAG = "Get_DJLine_Servlet";

    Activity activity;

    public Get_DJLine_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Base_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("userid", strings[0]);//用户ID
        map.put("state", strings[1]);//状态 0 未巡检，1巡检中， 2完成

        String res = HttpUtil.doGet(Const.URL + "appDJLine.cpeam", map);

        Base_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new Base_Entity();
        } else {
            try {
                entity = new Gson().fromJson(res, Base_Entity.class);
            } catch (Exception e) {
                entity = new Base_Entity();
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }


    @Override
    protected void onPostExecute(Base_Entity base_entity) {
        super.onPostExecute(base_entity);
        Loading.dismiss();


    }
}
