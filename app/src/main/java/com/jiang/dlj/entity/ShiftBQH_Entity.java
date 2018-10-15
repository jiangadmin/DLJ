package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 班前会
 */
public class ShiftBQH_Entity extends Base_Entity {

    /**
     * result : [{"guid":"0F1C88B054D5488D86D101676C8D7164","bqh_content":"1、通报本班次人员出勤情况，宣读安全时刻，对交接班室内LED显示屏上显示的 \u201c交班记录\u201d进行朗读；","bqh_is_check":"","bqh_situation_id":"ebea23a47aba971378013d3fed9b7d2f","shift_log_id":"10020","bqh_sort_num":1},{"guid":"AE7FC1C631254F1387D307E5F888B8F5","bqh_content":"2、集控主值班员（主值）进行接班检查汇报，明确提出存在的问题及异常，对不安全事项要作出安全提示，并说明能否正常接班；","bqh_is_check":"","bqh_situation_id":"bf221069cef566d82979a2c9b8fa2957","shift_log_id":"10020","bqh_sort_num":2},{"guid":"3D22C67DC960431CA0FC458F8EC36C06","bqh_content":"3、班前会工作布置应包括以下几个方面：","bqh_is_check":"","bqh_situation_id":"514d59753f0803780c29710958bc5cf4","shift_log_id":"10020","bqh_sort_num":3},{"guid":"3CBD77E3CA1F4C2682A5737E4F76BA05","bqh_content":"3-1本班的主要操作、工作票及注意事项；","bqh_is_check":"","bqh_situation_id":"92635cae6fc375521d842f03ae3bc596","shift_log_id":"10020","bqh_sort_num":4},{"guid":"EA2123A60AE24A82AA8289AF9D183E21","bqh_content":"3-2本班的定期工作；","bqh_is_check":"","bqh_situation_id":"1e6fc46cfab5d1c0a24b7e363d6259ec","shift_log_id":"10020","bqh_sort_num":5},{"guid":"BA57C85D192546E79B0DEF52B0DF5EDD","bqh_content":"3-3学习下发的规定和技术措施；","bqh_is_check":"","bqh_situation_id":"cbde93442a1154031ac230754e776071","shift_log_id":"10020","bqh_sort_num":6},{"guid":"15E9BA967E7B402CB4CD6A31BE40EDEF","bqh_content":"3-4针对设备缺陷、隐患做好事故预想；","bqh_is_check":"","bqh_situation_id":"835dd31a6e38f653dfafe2d400e6423e","shift_log_id":"10020","bqh_sort_num":7},{"guid":"2C575872B8E04F2B97F37A41968D085A","bqh_content":"3-5领导安排的工作；","bqh_is_check":"","bqh_situation_id":"588db917c0b871b77dd46387fe8410b7","shift_log_id":"10020","bqh_sort_num":8},{"guid":"A4F94D0CB4754FD1B6A7AC02F28506D2","bqh_content":"3-6根据人员情况分配工作；","bqh_is_check":"","bqh_situation_id":"dc78b9493a7fa7be68471f0bf721e11e","shift_log_id":"10020","bqh_sort_num":9}]
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
         * guid : 0F1C88B054D5488D86D101676C8D7164
         * bqh_content : 1、通报本班次人员出勤情况，宣读安全时刻，对交接班室内LED显示屏上显示的 “交班记录”进行朗读；
         * bqh_is_check :
         * bqh_situation_id : ebea23a47aba971378013d3fed9b7d2f
         * shift_log_id : 10020
         * bqh_sort_num : 1
         */

        private String guid;
        private String bqh_content;
        private String bqh_is_check;
        private String bqh_situation_id;
        private String shift_log_id;
        private int bqh_sort_num;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getBqh_content() {
            return bqh_content;
        }

        public void setBqh_content(String bqh_content) {
            this.bqh_content = bqh_content;
        }

        public String getBqh_is_check() {
            return bqh_is_check;
        }

        public void setBqh_is_check(String bqh_is_check) {
            this.bqh_is_check = bqh_is_check;
        }

        public String getBqh_situation_id() {
            return bqh_situation_id;
        }

        public void setBqh_situation_id(String bqh_situation_id) {
            this.bqh_situation_id = bqh_situation_id;
        }

        public String getShift_log_id() {
            return shift_log_id;
        }

        public void setShift_log_id(String shift_log_id) {
            this.shift_log_id = shift_log_id;
        }

        public int getBqh_sort_num() {
            return bqh_sort_num;
        }

        public void setBqh_sort_num(int bqh_sort_num) {
            this.bqh_sort_num = bqh_sort_num;
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
