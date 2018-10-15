package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 交接班班次及人员
 */
public class ShiftLog_Entity extends Base_Entity {

    /**
     * result : [{"duty_person_id":"6123","successor_orgname":"办公室","succession_shift_name":"前夜班","succession_team_name":"2值","shift_log_id":1002,"successor_id":"5559","duty_orgname":"供热技术部","successor_name":"孙虹","duty_team_name":"1值","duty_shift_name":"白班","duty_person_name":"赵鹏"}]
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
         * duty_person_id : 6123
         * successor_orgname : 办公室
         * succession_shift_name : 前夜班
         * succession_team_name : 2值
         * shift_log_id : 1002
         * successor_id : 5559
         * duty_orgname : 供热技术部
         * successor_name : 孙虹
         * duty_team_name : 1值
         * duty_shift_name : 白班
         * duty_person_name : 赵鹏
         */

        private String duty_person_id;
        private String successor_orgname;
        private String succession_shift_name;
        private String succession_team_name;
        private int shift_log_id;
        private String successor_id;
        private String duty_orgname;
        private String successor_name;
        private String duty_team_name;
        private String duty_shift_name;
        private String duty_person_name;

        public String getDuty_person_id() {
            return duty_person_id;
        }

        public void setDuty_person_id(String duty_person_id) {
            this.duty_person_id = duty_person_id;
        }

        public String getSuccessor_orgname() {
            return successor_orgname;
        }

        public void setSuccessor_orgname(String successor_orgname) {
            this.successor_orgname = successor_orgname;
        }

        public String getSuccession_shift_name() {
            return succession_shift_name;
        }

        public void setSuccession_shift_name(String succession_shift_name) {
            this.succession_shift_name = succession_shift_name;
        }

        public String getSuccession_team_name() {
            return succession_team_name;
        }

        public void setSuccession_team_name(String succession_team_name) {
            this.succession_team_name = succession_team_name;
        }

        public int getShift_log_id() {
            return shift_log_id;
        }

        public void setShift_log_id(int shift_log_id) {
            this.shift_log_id = shift_log_id;
        }

        public String getSuccessor_id() {
            return successor_id;
        }

        public void setSuccessor_id(String successor_id) {
            this.successor_id = successor_id;
        }

        public String getDuty_orgname() {
            return duty_orgname;
        }

        public void setDuty_orgname(String duty_orgname) {
            this.duty_orgname = duty_orgname;
        }

        public String getSuccessor_name() {
            return successor_name;
        }

        public void setSuccessor_name(String successor_name) {
            this.successor_name = successor_name;
        }

        public String getDuty_team_name() {
            return duty_team_name;
        }

        public void setDuty_team_name(String duty_team_name) {
            this.duty_team_name = duty_team_name;
        }

        public String getDuty_shift_name() {
            return duty_shift_name;
        }

        public void setDuty_shift_name(String duty_shift_name) {
            this.duty_shift_name = duty_shift_name;
        }

        public String getDuty_person_name() {
            return duty_person_name;
        }

        public void setDuty_person_name(String duty_person_name) {
            this.duty_person_name = duty_person_name;
        }
    }
}
