package com.jiang.dlj.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.dlj.MyApp;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.dialog.Loading;
import com.jiang.dlj.entity.Const;
import com.jiang.dlj.entity.Submit_Entity;
import com.jiang.dlj.utils.HttpUtil;
import com.jiang.dlj.utils.TabToast;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/25
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 提交巡检结果
 */
public class Set_DJSubmit_Servlet extends AsyncTask<Set_DJSubmit_Servlet.Info, Integer, Submit_Entity> {
    private static final String TAG = "Set_DJSubmit_Servlet";

    Activity activity;

    public Set_DJSubmit_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Submit_Entity doInBackground(Info... infos) {

        Info info = infos[0];
        Map map = new HashMap();

        map.put("guids", info.getGuids());
        map.put("djchk_id", info.getDjchk_id());
        map.put("resultDetails", info.getResultDetails());
        map.put("run_state", info.getRun_state());
        map.put("remark", info.getRemark());

        String res = HttpUtil.doGet(Const.URL + "appDJSubmitResult.cpeam", map);

        Submit_Entity entity;

        if (TextUtils.isEmpty(res)) {
            entity = new Submit_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, Submit_Entity.class);
            } catch (Exception e) {
                entity = new Submit_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(Submit_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();
        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult().isState()) {
                    MyApp.finishActivity();
                    TabToast.makeText("提交成功");
                } else {
                    Base_Dialog dialog = new Base_Dialog(activity);
                    dialog.setTitle("抱歉");
                    dialog.setMessage("提交失败");
                    dialog.setOk("朕已阅", null);
                }
                break;
            default:
                Base_Dialog dialog = new Base_Dialog(activity);
                dialog.setTitle("抱歉");
                dialog.setMessage(entity.getErrormsg());
                dialog.setOk("朕已阅", null);
                break;
        }
    }

    public static class Info {

        private String remark;    //	巡检备注
        private String guids;    //	巡检路线唯一标识
        private String djchk_id;//	巡检项标识
        private String run_state;//	巡检项标识
        private String resultDetails;//	巡检结果，以下字段都是该字段的子项 List<Map<String, String>>

        public String getGuids() {
            return guids;
        }

        public void setGuids(String guids) {
            this.guids = guids;
        }

        public String getDjchk_id() {
            return djchk_id;
        }

        public void setDjchk_id(String djchk_id) {
            this.djchk_id = djchk_id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getResultDetails() {
            return resultDetails;
        }

        public void setResultDetails(String resultDetails) {
            this.resultDetails = resultDetails;
        }

        public String getRun_state() {
            return run_state;
        }

        public void setRun_state(String run_state) {
            this.run_state = run_state;
        }
    }
}
