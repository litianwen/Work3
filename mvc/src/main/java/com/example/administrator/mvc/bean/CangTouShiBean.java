package com.example.administrator.mvc.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CangTouShiBean {


    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * list : ["骂伊又不著，个个入地狱。马上黄金鞍，","骂雨愁妨走，个个爱吃肉。马上黄金鞍，","骂伊又不著，个个入地狱。马嘶闻水腥，","骂雨愁妨走，个个爱吃肉。马足不可绊，","骂伊又不著，个个入地狱。马鸣风萧萧，","骂伊又不著，个个入地狱。马足不可绊，"]
         */

        private int ret_code;
        private List<String> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
