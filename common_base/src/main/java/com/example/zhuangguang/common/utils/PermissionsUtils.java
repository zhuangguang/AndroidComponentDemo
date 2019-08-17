package com.example.zhuangguang.common.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * @author 庄光
 * @time 2019/2/14  21:49
 * @desc ${6.0动态权限管理帮助类}
 */
public class PermissionsUtils {
    public static boolean checkPermission(Activity context ,String... permissions){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissionsList = new ArrayList<>();
            if(permissions != null && permissions.length != 0) {
                for (String permission : permissions) {
                    if(!isHavePermissions(context,permission)) {
                        permissionsList.add(permission);
                    }

                }
                if(permissionsList.size() > 0) {
                    // 遍历完后申请
                    applyPermissions(context, permissionsList);
                    return false;
                }

            }
        }
        return true;
    }

    private static void applyPermissions(Activity context, ArrayList<String> permissionsList) {
        if(!permissionsList.isEmpty()) {
            ActivityCompat.requestPermissions(context,permissionsList.toArray(new String[permissionsList.size()]),1);
        }
    }

    /**
     * 检查是否授权某权限
     */
    private static boolean isHavePermissions(Activity context, String permission) {
        return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
    }
}
