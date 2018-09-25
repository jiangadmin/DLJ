package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.DJPerson_Entity;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 提交巡检路线执行人
 */
public class Set_DJPerson_Servlet extends AsyncTask<String, Integer, DJPerson_Entity> {
    private static final String TAG = "Set_DJPerson_Servlet";

    Activity activity;

    public Set_DJPerson_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DJPerson_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("userid", strings[0]);
        map.put("guids", strings[1]);

        String res = HttpUtil.doGet(Const.URL + "appDJPerson.cpeam", map);


        DJPerson_Entity entity;

        if (TextUtils.isEmpty(res)) {
            entity = new DJPerson_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, DJPerson_Entity.class);
            } catch (Exception e) {
                entity = new DJPerson_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(DJPerson_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
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
