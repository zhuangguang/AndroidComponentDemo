package com.example.zhuangguang.common.bean;

import com.example.zhuangguang.common.base.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 庄光
 * @time 2019/1/30  22:39
 * @desc ${TODD}
 */
@Getter
@Setter
public class AppInfo extends BaseBean {
    private int id;
    private int versionCode;
    private String versionName;
    private String describe;
    private String path;

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", describe='" + describe + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
