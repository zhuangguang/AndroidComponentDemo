package com.example.zhuangguang.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.adapter.BannerImgAdapter;
import com.example.zhuangguang.common.manage.BlurTransformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/1/13  22:15
 * @desc ${TODD}
 */
public class ImageUtils {

    /**
     * 加载网络图片
     *
     * @param url       url
     * @param imageView imageView
     * @param imageView transformation 转换器
     */
    public static void loadImage(ImageView imageView, String url){
        if(TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_loading_image)
                .error(new ColorDrawable(Color.WHITE))
                .fallback(new ColorDrawable(Color.RED)))
                .into(imageView);
    }
    /**
     * 加载圆形
     *
     * @param url       url
     * @param imageView imageView
     */
    public static void loadIamgeCircle(ImageView imageView, String url){
        if(TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.centerCropTransform()
                .placeholder(R.drawable.ic_loading_image)
                .error(new ColorDrawable(Color.WHITE))
                .fallback(new ColorDrawable(Color.RED)))
                .into(imageView);
    }

    /**
     * 加载高斯模糊图
     *
     * @param imageView imageView
     * @param url       url
     */
    public static void loadImageBlur(ImageView imageView, String url){
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.bitmapTransform(
                        new BlurTransformation(imageView.getContext())))
                .into(imageView);
    }

    /**
     * 加载圆形
     *
     * @param url       url
     * @param imageView imageView
     */
    public static void loadImageCircle(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform()
                        .placeholder(R.drawable.ic_loading_image)
                        .error(new ColorDrawable(Color.WHITE))
                        .fallback(new ColorDrawable(Color.RED)))
                .into(imageView);
    }

    /**
     * 加载只有一张图的Banner
     *
     * @param banner   banner
     * @param imgUrl   imgUrl
     * @param listener listener
     */
    public static void loadBanner(ConvenientBanner banner, List<String> imgUrl, OnItemClickListener listener) {
        banner.setPages(new BannerImgAdapter(), imgUrl)
                .setPageIndicator(new int[]{R.drawable.shape_item_index_white, R.drawable.shape_item_index_red})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(listener)
                .startTurning();
    }

    public static File compressImage(String filePath) {
        if(!FileUtils.isExistExternalStore()) {
            return null;
        }
        int quality = 100;
        Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
        int degree = readPictureDegree(filePath);//获取相片拍摄角度
        if (degree != 0) {//旋转照片角度，防止头像横着显示
            bm = rotateBitmap(bm, degree);
        }

        File outputFile = null;
        outputFile = new File(FileUtils.getTempPath(), "temp_" + DateUtils.getCurrentTimeStamp() + ".jpg");
        if(!outputFile.exists()) {
            outputFile.getParentFile().mkdir();
        }else {
            outputFile.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG,quality,out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }finally {
            bm.recycle();
        }
        return outputFile;

    }



    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只是解析图片边沿，获取
        BitmapFactory.decodeFile(filePath,options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        //计算缩放比
        options.inJustDecodeBounds =false;
        return BitmapFactory.decodeFile(filePath,options);

    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width  = options.outWidth;
        int inSampleSize = 1;
        if(height > reqHeight || width > reqHeight) {
            final int heightRatic = Math.round((float)height/(float)reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatic < widthRatio ? heightRatic : widthRatio;
        }
        return inSampleSize;

    }

    public static int readPictureDegree (String path){
        int degree = 0;

        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    private static Bitmap rotateBitmap(Bitmap bm, int degree) {
        if(bm != null) {
            Matrix m = new Matrix();
            m.preRotate(degree);
            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(),m,true);
            return bm;
        }
        return bm;
    }

}
