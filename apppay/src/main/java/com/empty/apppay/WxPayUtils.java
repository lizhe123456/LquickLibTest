package com.empty.apppay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;


/**
 * 作者:linzheng 日期:2017/2/9 功能:微信支付具体实现逻辑
 */

class WxPayUtils {


    /**
     * 判断当前手机是否安装微信
     * @param context
     * @return
     */
    static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (TextUtils.equals(pn,"com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


}
