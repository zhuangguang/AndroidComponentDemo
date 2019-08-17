package com.example.zhuangguang.common.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.NetConfig;
import com.example.zhuangguang.common.utils.FileUtils;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.common.widget.NumberProgressBar;
import com.example.zhuangguang.common.widget.dialog.AppDialog;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxFileCallBack;

import java.io.File;


/**
 * @author 庄光
 * @time 2019/2/12  22:15
 * @desc ${更新下载APK}
 */
@SuppressLint("StaticFieldLeak")
public class UpdateManager {
    private static final int DOWNLOADING = 0x00;
    private static final int DOWNLOAD_SUCCESS = 0x02;
    private static final int DOWNLOAD_FAILED = 0x03;
    private static final int DOWNLOAD_CANCEL = 0x04;
    private Context mContext;
    private NumberProgressBar progressBar;
    private AppDialog progressDialog;
    private static UpdateManager manager;
    private File tempFile;//下载后存文件路径
    private UpdateManager(Context context) {
        this.mContext = context;
        View progressView = View.inflate(mContext, R.layout.update_progress_layout, null);
        progressBar = progressView.findViewById(R.id.number_progress);
        progressBar.setProgress(0);
        progressDialog = new AppDialog(mContext);
        progressDialog.setTitle("版本更新中···")
                .addDialogView(progressView)
                .show();
    }
    public static synchronized UpdateManager getInstance(Context context){
        if(manager == null) {
            manager = new UpdateManager(context);
        }
        return manager;
    }

    public void download(String url){
        if(TextUtils.isEmpty(url)) {
            ToastUtils.showToast(mContext, "请设置下载Url");
            return;
        }
        progressDialog.show();
        HttpUtils.getInstance()
                .setBaseUrl(NetConfig.Url.MY_SERVICE_URL)
                .downloadFile(url, new RxFileCallBack("temp.apk") {
                    @Override
                    public void onNext(Object tag, File file) {
                        tempFile = file;
                        updateHandler.sendEmptyMessage(DOWNLOAD_SUCCESS);
                    }

                    @Override
                    public void onProgress(Object tag, float progress, long downloaded, long total) {
                        Message message = updateHandler.obtainMessage();
                        message.what = DOWNLOADING;
                        message.obj = (int) progress;
                        updateHandler.sendMessage(message);

                    }

                    @Override
                    public void onError(Object tag, Throwable e) {
                        updateHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {
                        updateHandler.sendEmptyMessage(DOWNLOAD_CANCEL);
                    }
                });
    }
    @SuppressLint("HandlerLeak")
    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DOWNLOADING:
                    progressBar.setProgress((Integer) msg.obj);
                    break;
                case DOWNLOAD_SUCCESS:
                    progressDialog.dismiss();
                    ToastUtils.showToast(mContext, "下载完成");
                    installApk();
                    break;
                case DOWNLOAD_FAILED:
                    progressDialog.dismiss();
                    ToastUtils.showToast(mContext, "下载失败");
                    break;
                case DOWNLOAD_CANCEL:
                    progressDialog.dismiss();
                    ToastUtils.showToast(mContext, "已取消下载");
                    break;
                default:
                    break;
            }
        }
    };

    private void installApk(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uriForFile = FileUtils.getUriForFile(mContext, tempFile);
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0需要临时授权文件
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        mContext.startActivity(intent);
    }
}
