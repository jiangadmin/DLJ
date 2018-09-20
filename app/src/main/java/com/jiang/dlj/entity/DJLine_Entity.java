package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/20
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 巡检路线
 */
public class DJLine_Entity extends Base_Entity {

    /**
     * result : [{"endtime":"2016-11-22 01:00:00","linename_tx":"主控室巡检_电度表6时21时0时","line_id":"40792","begintime":"2016-11-22 00:00:00","state":"0","guids":"0bac9b28-fed9-475b-a0bb-fb5ff26f989f"},{"endtime":"2016-11-02 20:45:00","linename_tx":"交接班电量路线","line_id":"41271","begintime":"2016-11-02 20:15:00","state":"0","guids":"a34da6d8-1fd6-4864-aab7-f16e82da2559"}]
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
         * endtime : 2016-11-22 01:00:00
         * linename_tx : 主控室巡检_电度表6时21时0时
         * line_id : 40792
         * begintime : 2016-11-22 00:00:00
         * state : 0
         * guids : 0bac9b28-fed9-475b-a0bb-fb5ff26f989f
         */

        private String endtime;
        private String linename_tx;
        private String line_id;
        private String begintime;
        private String state;
        private String guids;

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getLinename_tx() {
            return linename_tx;
        }

        public void setLinename_tx(String linename_tx) {
            this.linename_tx = linename_tx;
        }

        public String getLine_id() {
            return line_id;
        }

        public void setLine_id(String line_id) {
            this.line_id = line_id;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getGuids() {
            return guids;
        }

        public void setGuids(String guids) {
            this.guids = guids;
        }
    }
}
