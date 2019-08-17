package com.example.zhuangguang.common.net.callback;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.net.NetConfig;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * @author 庄光
 * @time 2018/12/22  18:22
 * @desc ${TODD}
 */
public abstract class OnResultStringCallBack extends OnResultCallBack<String> {
    @Override
    public String onHandleResponse(ResponseBody response) throws Exception {
        return transform(new String(response.bytes()));
    }

    private String transform(String response) {
        Logger.e(JSON.toJSONString(response));
        try {
            JSONObject jsonObject = new JSONObject(response);
            code = jsonObject.optInt(NetConfig.Code.CODE);
            msg = jsonObject.optString(NetConfig.Code.MSG);
            success = jsonObject.optBoolean(NetConfig.Code.SUCCESS);
            dataStr = jsonObject.opt(NetConfig.Code.MODEL).toString();
            dataResponse = dataStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataResponse;
    }
}
