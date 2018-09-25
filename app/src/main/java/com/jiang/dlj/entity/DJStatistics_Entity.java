package com.jiang.dlj.entity;

/**
 * @author: jiangyao
 * @date: 2018/9/21
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检结果统计
 */
public class DJStatistics_Entity extends Base_Entity {


    /**
     * result : {"defectNum":1,"normalNum":0,"alarmNum":1,"unCompleteNum":3,"totolNum":4,"completeNum":1}
     * errorcode : 1000
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * defectNum : 1
         * normalNum : 0
         * alarmNum : 1
         * unCompleteNum : 3
         * totolNum : 4
         * completeNum : 1
         */

        private int defectNum;
        private int normalNum;
        private int alarmNum;
        private int unCompleteNum;
        private int totolNum;
        private int completeNum;

        public int getDefectNum() {
            return defectNum;
        }

        public void setDefectNum(int defectNum) {
            this.defectNum = defectNum;
        }

        public int getNormalNum() {
            return normalNum;
        }

        public void setNormalNum(int normalNum) {
            this.normalNum = normalNum;
        }

        public int getAlarmNum() {
            return alarmNum;
        }

        public void setAlarmNum(int alarmNum) {
            this.alarmNum = alarmNum;
        }

        public int getUnCompleteNum() {
            return unCompleteNum;
        }

        public void setUnCompleteNum(int unCompleteNum) {
            this.unCompleteNum = unCompleteNum;
        }

        public int getTotolNum() {
            return totolNum;
        }

        public void setTotolNum(int totolNum) {
            this.totolNum = totolNum;
        }

        public int getCompleteNum() {
            return completeNum;
        }

        public void setCompleteNum(int completeNum) {
            this.completeNum = completeNum;
        }
    }
}
