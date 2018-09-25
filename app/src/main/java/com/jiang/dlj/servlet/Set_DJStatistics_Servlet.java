package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.MyApp;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Base_Entity;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/21
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 总结提交
 */
public class Set_DJStatistics_Servlet extends AsyncTask<String, Integer, Set_DJStatistics_Servlet.Entity> {
    private static final String TAG = "Set_DJStatistics_Servle";

    Activity activity;

    public Set_DJStatistics_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("guids", strings[0]);
        map.put("summary", strings[1]);

        String res = HttpUtil.doGet(Const.URL + "appDJSubmitSummaryt.cpeam", map);

        Entity entity;

        if (TextUtils.isEmpty(res)) {
            entity = new Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, Entity.class);
            } catch (Exception e) {
                entity = new Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }


    @Override
    protected void onPostExecute(Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        Base_Dialog dialog = new Base_Dialog(activity);
        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult().isState()) {
                    dialog.setTitle("提交成功");
                    dialog.setOk("好", view -> MyApp.finishActivity());
                } else {
                    dialog.setTitle("提交失败");
                    dialog.setOk("再试试", null);
                }
                break;

            default:
                dialog.setTitle("抱歉");
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);
                break;
        }
    }

    class Entity extends Base_Entity {

        /**
         * result : {"state":true}
         * errorcode : 1000
         */

        private ResultBean result;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public  class ResultBean {
            /**
             * state : true
             */

            private boolean state;

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }
        }
    }
}
