package com.example.zhuangguang.common.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.example.zhuangguang.common.base.BaseApplication;
import com.example.zhuangguang.common.utils.DateUtils;
import com.example.zhuangguang.common.utils.FileUtils;
import com.example.zhuangguang.common.utils.ZipUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author 庄光
 * @time 2018/12/27  22:23
 * @desc ${ UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.}
 */
@SuppressLint("StaticFieldLeak")
public class CrashHandlerManage implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandlerManage";
    private static CrashHandlerManage INSTANCE;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> info = new HashMap<>();// 用来存储设备信息和异常信息

    private CrashHandlerManage() {
    }

    public synchronized static CrashHandlerManage getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new CrashHandlerManage();
        }
        return INSTANCE;
    }

    public void init(Context context){
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
       if(!handlerException(ex) && mDefaultHandler != null) {
           //   // 如果自定义的没有处理则让系统默认的异常处理器来处理
           mDefaultHandler.uncaughtException(thread,ex);

       }else {
           try {
               Thread.sleep(3000);// 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           ((BaseApplication) mContext.getApplicationContext()).exitApp();
       }
    }

    public boolean handlerException(Throwable ex){
        if(ex == null) {
            return false;
        }
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }



    private void collectDeviceInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if(pi != null) {
                String versionName =  pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                info.put("versionName", versionName);
                info.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Logger.e("获取设置信息失败");
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                info.put(field.getName(),field.get("").toString());
                Logger.e(field.getName() + ":" + field.get(""));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for ( Map.Entry<String ,String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\r\n");

        }
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        //循环着把所有的异常信息写入writer中
        while (cause != null){
            cause.printStackTrace(pw);
             cause = cause.getCause();
        }
        pw.close();
        String result = writer.toString();
        sb.append(result);
        //保存文件
        String fileName = "crash-" + DateUtils.getCurrentDateStr() + "-" + DateUtils.getCurrentTimeStamp() + ".log";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            try {
                File file = new File(FileUtils.getAppCrashPath(), fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 上传日志文件
     */
    public static void uploadCrashFiles() {
        final File outFile = FileUtils.getAppCrashPath();
        LinkedList<File> files = FileUtils.listLinkedFiles(FileUtils.getAppCrashPath().getPath());
        if (files == null || files.size() == 0) {
            return;
        }
        try {
            ZipUtils.zipFiles(files,outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(outFile.exists()) {
            return;
        }
        //TODO  做上传操作



    }
}
