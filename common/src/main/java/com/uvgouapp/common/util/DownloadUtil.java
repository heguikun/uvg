package com.uvgouapp.common.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description: 跳到应用市场下载类
 */
public class DownloadUtil {

    public static void startUpdate(Activity activity, String downloadURL) {
        if (isAvilible(activity, "com.tencent.android.qqdownloader")) {
            launchAppDetail(activity.getApplicationContext(), "com.uvgouapp", "com.tencent.android.qqdownloader");
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(downloadURL);
            intent.setData(content_url);
            if (activity.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                activity.startActivity(Intent.createChooser(intent, "请选择浏览器"));
            }
        }
    }

    /**
     * * 启动到app详情界面
     * * @param appPkg   App的包名
     * * @param marketPkg
     * *    应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 判断市场是否存在的方法
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    //    包名
    //    com.tencent.android.qqdownloader 腾讯应用宝
    //    com.qihoo.appstore 360手机助手
    //    com.baidu.appsearch 百度手机助手
    //    com.xiaomi.market 小米应用商店
    //    com.huawei.appmarket 华为应用商店
    //    com.wandoujia.phoenix2 豌豆荚
    //    com.dragon.android.pandaspace 91手机助手
    //    com.hiapk.marketpho 安智应用商店
    //    com.yingyonghui.market 应用汇
    //    com.tencent.qqpimsecure QQ手机管家
    //    com.mappn.gfan 机锋应用市场
    //    com.pp.assistant PP手机助手
    //    com.oppo.market OPPO应用商店
    //    cn.goapk.market GO市场
    //    zte.com.market 中兴应用商店
    //    com.yulong.android.coolmart 宇龙Coolpad应用商店
    //    com.lenovo.leos.appstore 联想应用商店
    //    com.coolapk.market cool市场
}
