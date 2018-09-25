package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 根据位置获取测项详情
 */
public class DJGetChk_Entity extends Base_Entity {


    /**
     * result : [{"djchk_name":"变频柜本体温度（℃）和振幅","djchk_id":"991CA945F090459C92A8E333F212D5D6","dj_method":"1","chkDetails":[{"line_id":40878,"remark":"测项备注","dj_reslut":"999","state":"2","param_name":"仪抄温度","max_val":90,"exlevel_id":"1","param_id":1850855,"min_val":30,"taskdetail_guid":"0A7C38B4E10742F989E7DE2E8458D6C2","standord_val":60,"djchk_id":"991CA945F090459C92A8E333F212D5D6","unit_code":"℃"},{"max_val":120,"line_id":40878,"min_val":101,"param_id":1850856,"taskdetail_guid":"A4F1CC3CF57345BA9BBE63771AC8FC4E","state":"0","standord_val":111,"param_name":"仪抄振幅","djchk_id":"991CA945F090459C92A8E333F212D5D6","unit_code":"Pa"}]},{"djchk_name":"UPS工作是否正常","djchk_id":"E19F366D7C9C4D468D1CF6ED00513AFE","dj_method":"2","chkDetails":[{"taskdetail_guid":"DC48FFF6D2284611B748A949A91C08F1","state":"0","option":[{"item_id":40398,"name_tx":"正常","almlevel_cd":"0"},{"item_id":40399,"name_tx":"不正常","almlevel_cd":"1"}]}]}]
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
         * djchk_name : 变频柜本体温度（℃）和振幅
         * djchk_id : 991CA945F090459C92A8E333F212D5D6
         * dj_method : 1
         * chkDetails : [{"line_id":40878,"remark":"测项备注","dj_reslut":"999","state":"2","param_name":"仪抄温度","max_val":90,"exlevel_id":"1","param_id":1850855,"min_val":30,"taskdetail_guid":"0A7C38B4E10742F989E7DE2E8458D6C2","standord_val":60,"djchk_id":"991CA945F090459C92A8E333F212D5D6","unit_code":"℃"},{"max_val":120,"line_id":40878,"min_val":101,"param_id":1850856,"taskdetail_guid":"A4F1CC3CF57345BA9BBE63771AC8FC4E","state":"0","standord_val":111,"param_name":"仪抄振幅","djchk_id":"991CA945F090459C92A8E333F212D5D6","unit_code":"Pa"}]
         */

        private String djchk_name;
        private String djchk_id;
        private int dj_method;
        private List<ChkDetailsBean> chkDetails;

        public String getDjchk_name() {
            return djchk_name;
        }

        public void setDjchk_name(String djchk_name) {
            this.djchk_name = djchk_name;
        }

        public String getDjchk_id() {
            return djchk_id;
        }

        public void setDjchk_id(String djchk_id) {
            this.djchk_id = djchk_id;
        }

        public int getDj_method() {
            return dj_method;
        }

        public void setDj_method(int dj_method) {
            this.dj_method = dj_method;
        }

        public List<ChkDetailsBean> getChkDetails() {
            return chkDetails;
        }

        public void setChkDetails(List<ChkDetailsBean> chkDetails) {
            this.chkDetails = chkDetails;
        }

        public static class ChkDetailsBean {
            /**
             * line_id : 40878
             * remark : 测项备注
             * dj_reslut : 999
             * state : 2
             * param_name : 仪抄温度
             * max_val : 90
             * exlevel_id : 1
             * param_id : 1850855
             * min_val : 30
             * taskdetail_guid : 0A7C38B4E10742F989E7DE2E8458D6C2
             * standord_val : 60
             * djchk_id : 991CA945F090459C92A8E333F212D5D6
             * unit_code : ℃
             */

            private int line_id;
            private String remark;
            private String dj_reslut;
            private int state;
            private int run_state;
            private int option_cd;
            private String param_name;
            private double max_val;
            private String exlevel_id;
            private int param_id;
            private double min_val;
            private String taskdetail_guid;
            private int standord_val;
            private String djchk_id;
            private String unit_code;

            public int getOption_cd() {
                return option_cd;
            }

            public void setOption_cd(int option_cd) {
                this.option_cd = option_cd;
            }

            private List<OptionBean> option;

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            public int getLine_id() {
                return line_id;
            }

            public void setLine_id(int line_id) {
                this.line_id = line_id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getDj_reslut() {
                return dj_reslut;
            }

            public void setDj_reslut(String dj_reslut) {
                this.dj_reslut = dj_reslut;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getParam_name() {
                return param_name;
            }

            public void setParam_name(String param_name) {
                this.param_name = param_name;
            }

            public double getMax_val() {
                return max_val;
            }

            public void setMax_val(double max_val) {
                this.max_val = max_val;
            }

            public String getExlevel_id() {
                return exlevel_id;
            }

            public void setExlevel_id(String exlevel_id) {
                this.exlevel_id = exlevel_id;
            }

            public int getParam_id() {
                return param_id;
            }

            public void setParam_id(int param_id) {
                this.param_id = param_id;
            }

            public double getMin_val() {
                return min_val;
            }

            public void setMin_val(double min_val) {
                this.min_val = min_val;
            }

            public String getTaskdetail_guid() {
                return taskdetail_guid;
            }

            public void setTaskdetail_guid(String taskdetail_guid) {
                this.taskdetail_guid = taskdetail_guid;
            }

            public int getStandord_val() {
                return standord_val;
            }

            public void setStandord_val(int standord_val) {
                this.standord_val = standord_val;
            }

            public String getDjchk_id() {
                return djchk_id;
            }

            public void setDjchk_id(String djchk_id) {
                this.djchk_id = djchk_id;
            }

            public String getUnit_code() {
                return unit_code;
            }

            public void setUnit_code(String unit_code) {
                this.unit_code = unit_code;
            }

            public int getRun_state() {
                return run_state;
            }

            public void setRun_state(int run_state) {
                this.run_state = run_state;
            }

            public static class OptionBean {
                private int item_id;
                private String name_tx;
                private int almlevel_cd;

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public String getName_tx() {
                    return name_tx;
                }

                public void setName_tx(String name_tx) {
                    this.name_tx = name_tx;
                }

                public int getAlmlevel_cd() {
                    return almlevel_cd;
                }

                public void setAlmlevel_cd(int almlevel_cd) {
                    this.almlevel_cd = almlevel_cd;
                }
            }
        }
    }
}
