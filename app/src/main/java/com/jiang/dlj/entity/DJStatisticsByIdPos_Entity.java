package com.jiang.dlj.entity;

/**
 * @author: jiangyao
 * @date: 2018/9/25
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:根据位置获取巡检结果统计
 */
public class DJStatisticsByIdPos_Entity extends Base_Entity {

    /**
     * result : {"unCompleteNum":2,"totolNum":3,"completeNum":1}
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
         * unCompleteNum : 2
         * totolNum : 3
         * completeNum : 1
         */

        private int unCompleteNum;
        private int totolNum;
        private int completeNum;

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
