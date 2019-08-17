package com.example.zhuangguang.common.net.callback;

import com.example.zhuangguang.common.net.NetConfig;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

import okhttp3.ResponseBody;

/**
 * @author 庄光
 * @time 2018/12/22  18:20
 * @desc ${TODD}
 */
public abstract class OnResultObjectCallBack <T> extends OnResultCallBack<T>{
    @Override
    public T onHandleResponse(ResponseBody response) throws Exception {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return transform(new String(response.bytes()), entityClass);
    }
    public T transform(String response, final Class classOfT) throws ClassCastException {
        Logger.e(response);
        try {
            JSONObject jsonObject = new JSONObject(response);

            code = jsonObject.optInt(NetConfig.Code.CODE);
            msg = jsonObject.optString(NetConfig.Code.MSG);
            success = jsonObject.optBoolean(NetConfig.Code.SUCCESS);
            dataStr = jsonObject.opt(NetConfig.Code.MODEL).toString();
            dataResponse = (T) new Gson().fromJson(dataStr, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataResponse;
    }
}
