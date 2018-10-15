package com.jiang.dlj.entity;

import java.util.List;

/**
 * @author: jiangadmin
 * @date: 2018/10/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 交班检查查询
 */
public class ShiftJBJC_Entity extends Base_Entity {

    /**
     * result : [{"guid":"B9687B6DB029450C88D4338CA283086A","jbjc_content":"1、集控巡检值班员","jbjc_situation_id":"c864bf250f5343ff317f7b59afd9627b","shift_log_id":10020,"jbjc_sort_num":1},{"guid":"152443AB1DE6411DA635BF1B018C9273","jbjc_content":"1-1按巡检路线对相关设备进行仔细认真的检查，发现设备异常及时汇报；","jbjc_situation_id":"fa889ea208da5cefb0ca36f5cd147010","shift_log_id":10020,"jbjc_sort_num":2},{"guid":"FA8573F9BB554FE7ADECF297DF10A01B","jbjc_content":"1-3检查各机组对讲机和充电器、测振仪齐备完好；","jbjc_situation_id":"28a40d35414a081e8f63d9038d5947cc","shift_log_id":10020,"jbjc_sort_num":3},{"guid":"3BE5FAA241444F91948467A878D9D5B5","jbjc_content":"1-4检查集控工具柜干净、整洁，柜内工具摆放整齐并定置放置；","jbjc_situation_id":"3cdef3a153095e6aadf36e115b6f326e","shift_log_id":10020,"jbjc_sort_num":4},{"guid":"FC72D5C1106748D2B1464B547C868AAE","jbjc_content":"1-5阀门扳手、标示牌齐备、完好并定置放置；","jbjc_situation_id":"957bf0a5dfb83e0627f1c9ab7e85d809","shift_log_id":10020,"jbjc_sort_num":5},{"guid":"7B4F7BD964294A75ABE3E5A0FBE925FC","jbjc_content":"1-6各工器具柜内物件摆放整齐并定置放置；","jbjc_situation_id":"d5ec1a71de3383cd03a0a18405d40951","jbjc_is_check":1,"shift_log_id":10020,"jbjc_sort_num":6},{"guid":"44E573837BA74AC281762AC7269477B3","jbjc_content":"2、集控副值班员","jbjc_situation_id":"478502dd70fea00b8cf8fe7959db06ff","shift_log_id":10020,"jbjc_sort_num":7},{"guid":"A4F5B51B77A24FADAE63E3CECF413EDC","jbjc_content":"2-1按巡检路线进行现场检查，机组有重大缺陷时，会同有关人员到就地实际查看设备情况；","jbjc_situation_id":"9ea0ca8ce05de13a7aef81924393be5e","shift_log_id":10020,"jbjc_sort_num":8},{"guid":"B452271A03D64D91BDF8EA1B36A76BA7","jbjc_content":"2-2查看交接班日志，掌握机组运行情况及存在的问题；","jbjc_situation_id":"22b1a9e90f88e0213bb9ea6da7d30655","shift_log_id":10020,"jbjc_sort_num":9},{"guid":"49E283D528844CFB83BA2BF084A21E4A","jbjc_content":"3、集控主值班员","jbjc_situation_id":"59cea710595bd852e5d1e2548cbf7c14","shift_log_id":10020,"jbjc_sort_num":10},{"guid":"3096EC7A3ED04C87B5ED5C928B3522F0","jbjc_content":"3-1查看本机组DCS、NCS、DEH画面；","jbjc_situation_id":"1b55eeb577e3415b2c4d8815435c87ca","shift_log_id":10020,"jbjc_sort_num":11},{"guid":"C3293D3ED1E147718CFC3A7A5366E092","jbjc_content":"3-2掌握机组热力、电气系统运行方式及参数、查看自动和保护状态、了解设备检修及工作票执行情况；","jbjc_situation_id":"e8ab5810647bba636b187935e5679271","shift_log_id":10020,"jbjc_sort_num":12},{"guid":"B5E019338CF64651941917E3F712BA84","jbjc_content":"3-3查看交接班日志、掌握机组运行情况及存在的问题，做好本机组班前会汇报准备；","jbjc_situation_id":"11cf6c84e2c7ff9be5e541248a5dcb80","shift_log_id":10020,"jbjc_sort_num":13}]
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
         * guid : B9687B6DB029450C88D4338CA283086A
         * jbjc_content : 1、集控巡检值班员
         * jbjc_situation_id : c864bf250f5343ff317f7b59afd9627b
         * shift_log_id : 10020
         * jbjc_sort_num : 1
         * jbjc_is_check : 1
         */

        private String guid;
        private String jbjc_content;
        private String jbjc_situation_id;
        private int shift_log_id;
        private int jbjc_sort_num;
        private int jbjc_is_check;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getJbjc_content() {
            return jbjc_content;
        }

        public void setJbjc_content(String jbjc_content) {
            this.jbjc_content = jbjc_content;
        }

        public String getJbjc_situation_id() {
            return jbjc_situation_id;
        }

        public void setJbjc_situation_id(String jbjc_situation_id) {
            this.jbjc_situation_id = jbjc_situation_id;
        }

        public int getShift_log_id() {
            return shift_log_id;
        }

        public void setShift_log_id(int shift_log_id) {
            this.shift_log_id = shift_log_id;
        }

        public int getJbjc_sort_num() {
            return jbjc_sort_num;
        }

        public void setJbjc_sort_num(int jbjc_sort_num) {
            this.jbjc_sort_num = jbjc_sort_num;
        }

        public int getJbjc_is_check() {
            return jbjc_is_check;
        }

        public void setJbjc_is_check(int jbjc_is_check) {
            this.jbjc_is_check = jbjc_is_check;
        }
    }
}
