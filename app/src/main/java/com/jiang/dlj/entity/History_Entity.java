package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/9/27
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 历史值
 */
public class History_Entity extends Base_Entity {

    /**
     * result : [{"result_tx":"111","start_tm":"2018-09-20 02:00:00"},{"result_tx":"222","start_tm":"2018-09-20 10:00:00"}]
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
         * result_tx : 111
         * start_tm : 2018-09-20 02:00:00
         */

        private String result_tx;
        private String start_tm;

        public String getResult_tx() {
            return result_tx;
        }

        public void setResult_tx(String result_tx) {
            this.result_tx = result_tx;
        }

        public String getStart_tm() {
            return start_tm;
        }

        public void setStart_tm(String start_tm) {
            this.start_tm = start_tm;
        }
    }
}
