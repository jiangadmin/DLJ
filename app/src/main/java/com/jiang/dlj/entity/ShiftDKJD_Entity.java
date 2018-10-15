package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 对口交底
 */
public class ShiftDKJD_Entity extends Base_Entity {

    /**
     * result : [{"guid":"859DB0C59D83402DBBB27695519E230E","dkjd_situation_id":"88774a420bb91aaf51fe8cd7fb07ed51","shift_log_id":"10021","dkjd_is_check":"","dkjd_content":"对口交底内容2","dkjd_sort_num":3},{"guid":"5D8CA47A72314B6FACAB82FBE77EA39E","dkjd_situation_id":"22431cfd227c17989c175de0b26a0215","shift_log_id":"10021","dkjd_is_check":"","dkjd_content":"对口交底内容1","dkjd_sort_num":5}]
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
         * guid : 859DB0C59D83402DBBB27695519E230E
         * dkjd_situation_id : 88774a420bb91aaf51fe8cd7fb07ed51
         * shift_log_id : 10021
         * dkjd_is_check :
         * dkjd_content : 对口交底内容2
         * dkjd_sort_num : 3
         */

        private String guid;
        private String dkjd_situation_id;
        private String shift_log_id;
        private String dkjd_is_check;
        private String dkjd_content;
        private int dkjd_sort_num;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getDkjd_situation_id() {
            return dkjd_situation_id;
        }

        public void setDkjd_situation_id(String dkjd_situation_id) {
            this.dkjd_situation_id = dkjd_situation_id;
        }

        public String getShift_log_id() {
            return shift_log_id;
        }

        public void setShift_log_id(String shift_log_id) {
            this.shift_log_id = shift_log_id;
        }

        public String getDkjd_is_check() {
            return dkjd_is_check;
        }

        public void setDkjd_is_check(String dkjd_is_check) {
            this.dkjd_is_check = dkjd_is_check;
        }

        public String getDkjd_content() {
            return dkjd_content;
        }

        public void setDkjd_content(String dkjd_content) {
            this.dkjd_content = dkjd_content;
        }

        public int getDkjd_sort_num() {
            return dkjd_sort_num;
        }

        public void setDkjd_sort_num(int dkjd_sort_num) {
            this.dkjd_sort_num = dkjd_sort_num;
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
