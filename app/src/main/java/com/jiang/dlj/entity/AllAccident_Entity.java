package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 预想库
 */
public class AllAccident_Entity extends Base_Entity {

    /**
     * result : [{"id":10014,"username":"管理员","update_time":"2018-09-28","reason":"事故原因内容","pre_theme_content":"设备房外墙维修脚手架搭设不规范，要求严格执行脚手架搭设规范要求，使用前做好脚手架验收挂牌工作。","updator":"1200119390","pre_theme_accident":"设备房外墙维修脚手架搭设不规范，要求严格执行脚手架搭设规范要求，abc使用前做好脚手架验收挂牌工作。","skill_name":"锅炉","skill_id":18976,"solution":"现场安全员徐志韡已与施工方负责人进行交流，并责令其整改，现脚手架已搭设规范，并做好了脚手架验收挂牌工作。"}]
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
         * id : 10014
         * username : 管理员
         * update_time : 2018-09-28
         * reason : 事故原因内容
         * pre_theme_content : 设备房外墙维修脚手架搭设不规范，要求严格执行脚手架搭设规范要求，使用前做好脚手架验收挂牌工作。
         * updator : 1200119390
         * pre_theme_accident : 设备房外墙维修脚手架搭设不规范，要求严格执行脚手架搭设规范要求，abc使用前做好脚手架验收挂牌工作。
         * skill_name : 锅炉
         * skill_id : 18976
         * solution : 现场安全员徐志韡已与施工方负责人进行交流，并责令其整改，现脚手架已搭设规范，并做好了脚手架验收挂牌工作。
         */

        private int id;
        private String username;
        private String update_time;
        private String reason;
        private String pre_theme_content;
        private String updator;
        private String pre_theme_accident;
        private String skill_name;
        private int skill_id;
        private String solution;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPre_theme_content() {
            return pre_theme_content;
        }

        public void setPre_theme_content(String pre_theme_content) {
            this.pre_theme_content = pre_theme_content;
        }

        public String getUpdator() {
            return updator;
        }

        public void setUpdator(String updator) {
            this.updator = updator;
        }

        public String getPre_theme_accident() {
            return pre_theme_accident;
        }

        public void setPre_theme_accident(String pre_theme_accident) {
            this.pre_theme_accident = pre_theme_accident;
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }

        public int getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(int skill_id) {
            this.skill_id = skill_id;
        }

        public String getSolution() {
            return solution;
        }

        public void setSolution(String solution) {
            this.solution = solution;
        }
    }
}
