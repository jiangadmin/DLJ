package com.jiang.dlj.entity;

/**
 * @author: jiangadmin
 * @date: 2018/9/17
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 公共实体
 */
public class Base_Entity {

    /**
     * result : {"loginState":true,"username":"管理员","userid":"1200119390"}
     * errormsg : 调用成功
     * errorcode : 1000
     */

    private String errormsg;
    private int errorcode;

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

}
