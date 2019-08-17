package com.example.zhuangguang.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;

import com.example.zhuangguang.common.base.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 庄光
 * @time 2019/1/30  23:10
 * @desc ${工具类}
 */
public class Utils {

    public static String getAssetFileData(Context context ,String fileName){

        StringBuffer stringBuffer = new StringBuffer();

        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine())!=null){
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
    /**
     * 验证邮箱
     *
     * @param email email字符串
     * @return 如果是邮件地址 返回 true
     */
    public static boolean isEmail(String email){
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;

    }
    /**
     * 判断是否为纯数字
     *
     * @param str str
     * @return boolean
     */
    public static boolean isNumber(String str){
        Pattern compile = Pattern.compile("[0-9]*");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }
    /**
     * 获取APP包名
     *
     * @return String
     */
    public static String getPackageName(){
        return BaseApplication.getApplication().getPackageName();
    }
    /**
     * 获取APP 版本名称
     *
     * @return String
     */
    public static String getVersionName(){
        PackageManager packageManager = BaseApplication.getApplication().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }
    /**
     * 获取APP 版本号
     *
     * @return Integer
     */
    public static Integer getVersionCode(){
        PackageManager packageManager = BaseApplication.getApplication().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
