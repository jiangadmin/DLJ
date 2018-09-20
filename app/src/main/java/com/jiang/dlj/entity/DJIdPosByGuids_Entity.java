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
     * result : [{"idpos_id":"1306","idpos_name":"(箱变)1#风机低压开关室","id_cd":"CX01XB003"},{"idpos_id":"1308","idpos_name":"(箱变)1#风机变压器室","id_cd":"CX01XB005"},{"idpos_id":"1304","idpos_name":"(箱变)1#风机一层平台","id_cd":"CX01XB001"}]
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
         * idpos_id : 1306
         * idpos_name : (箱变)1#风机低压开关室
         * id_cd : CX01XB003
         */

        private String idpos_id;
        private String idpos_name;
        private String id_cd;

        public String getIdpos_id() {
            return idpos_id;
        }

        public void setIdpos_id(String idpos_id) {
            this.idpos_id = idpos_id;
        }

        public String getIdpos_name() {
            return idpos_name;
        }

        public void setIdpos_name(String idpos_name) {
            this.idpos_name = idpos_name;
        }

        public String getId_cd() {
            return id_cd;
        }

        public void setId_cd(String id_cd) {
            this.id_cd = id_cd;
        }
    }
}
