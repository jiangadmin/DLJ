package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 班后会
 */
public class ShiftBHH_Entity extends Base_Entity {


    /**
     * result : [{"guid":"37576A66D83343CBB1FD7920DC2C4BA8","bhh_content":"1、通报本班次人员出勤情况，宣读安全时刻，对交接班室内LED显示屏上显示的 \u201c交班记录\u201d进行朗读；","bhh_sort_num":1,"bhh_situation_id":"ebea23a47aba971378013d3fed9b7d2f","shift_log_id":"10020","bhh_is_check":""},{"guid":"2823EF078C0B4BEE84D365458D68EF4A","bhh_content":"2、集控主值班员（主值）进行接班检查汇报，明确提出存在的问题及异常，对不安全事项要作出安全提示，并说明能否正常接班；","bhh_sort_num":2,"bhh_situation_id":"bf221069cef566d82979a2c9b8fa2957","shift_log_id":"10020","bhh_is_check":""},{"guid":"8C9F4FBCCBA249779251F7D0788BDD6E","bhh_content":"3、班前会工作布置应包括以下几个方面：","bhh_sort_num":3,"bhh_situation_id":"514d59753f0803780c29710958bc5cf4","shift_log_id":"10020","bhh_is_check":""},{"guid":"4B384AC399AF47A188E4C1B1D4D986E5","bhh_content":"3-1本班的主要操作、工作票及注意事项；","bhh_sort_num":4,"bhh_situation_id":"92635cae6fc375521d842f03ae3bc596","shift_log_id":"10020","bhh_is_check":""},{"guid":"7572E03ADC6B48FBAEF142B5FFECCC15","bhh_content":"3-2本班的定期工作；","bhh_sort_num":5,"bhh_situation_id":"1e6fc46cfab5d1c0a24b7e363d6259ec","shift_log_id":"10020","bhh_is_check":""},{"guid":"5FC0805E68F645ABB7F951A27C2B67A9","bhh_content":"3-3学习下发的规定和技术措施；","bhh_sort_num":6,"bhh_situation_id":"cbde93442a1154031ac230754e776071","shift_log_id":"10020","bhh_is_check":""},{"guid":"9F6D54F9217240C58E80B7DAC824B90D","bhh_content":"3-4针对设备缺陷、隐患做好事故预想；","bhh_sort_num":7,"bhh_situation_id":"835dd31a6e38f653dfafe2d400e6423e","shift_log_id":"10020","bhh_is_check":""},{"guid":"3C6F55045FA5437394E514C1F09B4FB1","bhh_content":"3-5领导安排的工作；","bhh_sort_num":8,"bhh_situation_id":"588db917c0b871b77dd46387fe8410b7","shift_log_id":"10020","bhh_is_check":""},{"guid":"9A46651F12484F5A838B56CFBD14C9F9","bhh_content":"3-6根据人员情况分配工作；","bhh_sort_num":9,"bhh_situation_id":"dc78b9493a7fa7be68471f0bf721e11e","shift_log_id":"10020","bhh_is_check":""}]
     * files : [{"fileId":"d3503488e7d3ca8f3eb391c052e449d3","fileName":"artifacts.wav"},{"fileId":"a620404de38955942f9bb4f92715f7aa","fileName":"artifacts.wav"}]
     * errorcode : 1000
     */

    private List<ResultBean> result;
    private List<FilesBean> files;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public static class ResultBean {
        /**
         * guid : 37576A66D83343CBB1FD7920DC2C4BA8
         * bhh_content : 1、通报本班次人员出勤情况，宣读安全时刻，对交接班室内LED显示屏上显示的 “交班记录”进行朗读；
         * bhh_sort_num : 1
         * bhh_situation_id : ebea23a47aba971378013d3fed9b7d2f
         * shift_log_id : 10020
         * bhh_is_check :
         */

        private String guid;
        private String bhh_content;
        private int bhh_sort_num;
        private String bhh_situation_id;
        private String shift_log_id;
        private String bhh_is_check;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getBhh_content() {
            return bhh_content;
        }

        public void setBhh_content(String bhh_content) {
            this.bhh_content = bhh_content;
        }

        public int getBhh_sort_num() {
            return bhh_sort_num;
        }

        public void setBhh_sort_num(int bhh_sort_num) {
            this.bhh_sort_num = bhh_sort_num;
        }

        public String getBhh_situation_id() {
            return bhh_situation_id;
        }

        public void setBhh_situation_id(String bhh_situation_id) {
            this.bhh_situation_id = bhh_situation_id;
        }

        public String getShift_log_id() {
            return shift_log_id;
        }

        public void setShift_log_id(String shift_log_id) {
            this.shift_log_id = shift_log_id;
        }

        public String getBhh_is_check() {
            return bhh_is_check;
        }

        public void setBhh_is_check(String bhh_is_check) {
            this.bhh_is_check = bhh_is_check;
        }
    }

    public static class FilesBean {
        /**
         * fileId : d3503488e7d3ca8f3eb391c052e449d3
         * fileName : artifacts.wav
         */

        private String fileId;
        private String fileName;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
