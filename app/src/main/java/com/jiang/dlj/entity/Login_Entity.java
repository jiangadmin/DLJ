package com.jiang.dlj.entity;

/**
 * @author: jiangadmin
 * @date: 2018/9/19
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 登录
 */
public class Login_Entity extends Base_Entity {

    /**
     * result : {"loginState":true,"username":"管理员","userid":"1200119390"}
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
         * loginState : true
         * username : 管理员
         * userid : 1200119390
         */

        private boolean loginState;
        private String username;
        private String userid;

        public boolean isLoginState() {
            return loginState;
        }

        public void setLoginState(boolean loginState) {
            this.loginState = loginState;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
