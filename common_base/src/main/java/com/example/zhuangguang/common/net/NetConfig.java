package com.example.zhuangguang.common.net;

import com.example.zhuangguang.common.constants.Constants;

/**
 * @author 庄光
 * @time 2018/12/22  17:13
 * @desc ${网络请求URL
 */
public class NetConfig {
    /**
     * 响应的返回key
     */
    public class Code {
        public static final String SUCCESS = "success";
        public static final String MSG = "errorMsg";
        public static final String CODE = "errorCode";
        public static final String MODEL = "data";

    }
    /**
     * H5界面
     */
    public class Html{}

    /**
     * 网络请求Url
     */
    public static class Url{
        //服务器IP
        public static String MY_SERVICE_URL = "http://sdk.xiaoyuyu.com.cn";

        interface BaseUrl{
            String SERVER_DEVELOP = "http://www.wanandroid.com";
            String SERVER_TEST = "";
            String SERVER_PRODUCTION = "";
        }

        static String getBaseUrl(){
            switch (Constants.SERVER_TYPE){
                case Constants.ServerType.SERVER_DEVELOP:
                    return BaseUrl.SERVER_DEVELOP;
                case Constants.ServerType.SERVER_TEST:
                    return BaseUrl.SERVER_TEST;
                case Constants.ServerType.SERVER_PRODUCTION:
                    return BaseUrl.SERVER_PRODUCTION;
            }
            return BaseUrl.SERVER_PRODUCTION;
        }
    }




}
