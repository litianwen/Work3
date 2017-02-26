package com.example.administrator.lesson19_xutilsdemo;

/**
 * Created by Administrator on 2016/11/16.
 */

public class ImageVerify {


    /**
     * showapi_res_body : {"img_path":"http://app1.showapi.com/images/temp/20161116/0b1c2713-0b73-45e9-8be5-a976505fcc60.jpg","img_path_https":"https://app1.showapi.com/images/temp/20161116/0b1c2713-0b73-45e9-8be5-a976505fcc60.jpg","ret_code":0,"text":"列宽茧香"}
     * showapi_res_code : 0
     * showapi_res_error :
     */

    private ShowapiResBodyBean showapi_res_body;
    private int showapi_res_code;
    private String showapi_res_error;

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

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

    public static class ShowapiResBodyBean {
        /**
         * img_path : http://app1.showapi.com/images/temp/20161116/0b1c2713-0b73-45e9-8be5-a976505fcc60.jpg
         * img_path_https : https://app1.showapi.com/images/temp/20161116/0b1c2713-0b73-45e9-8be5-a976505fcc60.jpg
         * ret_code : 0
         * text : 列宽茧香
         */

        private String img_path;
        private String img_path_https;
        private int ret_code;
        private String text;

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getImg_path_https() {
            return img_path_https;
        }

        public void setImg_path_https(String img_path_https) {
            this.img_path_https = img_path_https;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
