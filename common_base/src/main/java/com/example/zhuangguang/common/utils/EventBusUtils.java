package com.example.zhuangguang.common.utils;

import com.example.zhuangguang.common.bean.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 庄光
 * @time 2019/1/20  16:24
 * @desc ${EventBusUtils}
 */
public class EventBusUtils {
    /**
     * 注册事件
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 解除事件
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送普通事件
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }
    //...
}
