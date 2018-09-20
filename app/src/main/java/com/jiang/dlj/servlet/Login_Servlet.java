package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.MyApp;
import com.jiang.dlj.activity.Main_Activity;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.Login_Entity;
import com.jiang.dlj.entity.Save_Key;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.LogUtil;
import com.jiang.dlj.utils.SaveUtils;
import com.jiang.dlj.utils.SystemPropertiesProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangadmin
 * @date: 2018/9/17
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:  登录
 */
public class Login_Servlet extends AsyncTask<String, Integer, Login_Entity> {
    private static final String TAG = "Login_Servlet";

    Activity activity;

    public Login_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Login_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("name", strings[0]);
        map.put("pass", strings[1]);
        map.put("clientid", SystemPropertiesProxy.getString(MyApp.getInstance(), "ro.serialno"));

        String res = HttpUtil.doGet(Const.URL + "login.cpeam", map);

        LogUtil.e(TAG, res);
        Login_Entity entity;
        if (TextUtils.isEmpty(res)) {
            entity = new Login_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败！");
        } else {
            try {
                entity = new Gson().fromJson(res, Login_Entity.class);
            } catch (Exception e) {
                entity = new Login_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(Login_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
        switch (entity.getErrorcode()) {
            case 1000:
                if (!TextUtils.isEmpty(entity.getResult().getUserid())){
                    SaveUtils.setString(Save_Key.UserId,entity.getResult().getUserid());
                    Main_Activity.start(activity);
                    MyApp.finishActivity();
                }
                break;
            default:
                Base_Dialog dialog = new Base_Dialog(activity);
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);

                break;
        }
    }
}
