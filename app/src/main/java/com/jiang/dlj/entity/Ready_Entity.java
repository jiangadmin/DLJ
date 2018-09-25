package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/21
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检准备
 */
public class Ready_Entity extends Base_Entity {


    /**
     * result : [{"ready_name":"准备1","ready_no":"NO1"},{"ready_name":"准备2","ready_no":"NO2"},{"ready_name":"准备3","ready_no":"NO3"}]
     * errorcode : 1000
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ready_name : 准备1
         * ready_no : NO1
         */

        private String ready_name;
        private String ready_no;

        public String getReady_name() {
            return ready_name;
        }

        public void setReady_name(String ready_name) {
            this.ready_name = ready_name;
        }

        public String getReady_no() {
            return ready_no;
        }

        public void setReady_no(String ready_no) {
            this.ready_no = ready_no;
        }
    }
}
