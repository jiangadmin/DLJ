package com.jiang.dlj.entity;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */
public class DJPerson_Entity extends Base_Entity {


    /**
     * result : {"remark":"该路线不存在或者已经有执行人了，请重新确认","state":false}
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
         * remark : 该路线不存在或者已经有执行人了，请重新确认
         * state : false
         */

        private String remark;
        private boolean state;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
