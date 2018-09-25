package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线的位置信息
 */
public class DJIdPosByGuids_Entity extends Base_Entity {


    /**
     * result : [{"id_cd":"sadf","idpos_id":"118029","state":"0","idpos_name":"sadf","line_id":40878}]
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
         * id_cd : sadf
         * idpos_id : 118029
         * state : 0
         * idpos_name : sadf
         * line_id : 40878
         */

        private String id_cd;
        private String idpos_id;
        private int state;
        private String idpos_name;
        private int line_id;

        public String getId_cd() {
            return id_cd;
        }

        public void setId_cd(String id_cd) {
            this.id_cd = id_cd;
        }

        public String getIdpos_id() {
            return idpos_id;
        }

        public void setIdpos_id(String idpos_id) {
            this.idpos_id = idpos_id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getIdpos_name() {
            return idpos_name;
        }

        public void setIdpos_name(String idpos_name) {
            this.idpos_name = idpos_name;
        }

        public int getLine_id() {
            return line_id;
        }

        public void setLine_id(int line_id) {
            this.line_id = line_id;
        }
    }
}
