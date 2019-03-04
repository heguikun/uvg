package com.uvgouapp.common.util;

import android.Manifest;
import android.annotation.SuppressLint;

import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.DataOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  权限工具类
 */
public class PermissionsUtil {

    private PermissionsUtil() {
    }

    /**
     * @param context  上下文
     * @param listener 需要申请的权限   下面只是举例说明,具体的权限,具体申明.
     */
    @SuppressLint("CheckResult")
    public static void checkPermissions(AppCompatActivity context, Consumer<Boolean> listener) {
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)//多个权限用","隔开
                .subscribe(listener);
    }

    /**
     * @param context
     * @param listener 照相机权限
     */
    @SuppressLint("CheckResult")
    public static void checkOnlyCameraPermissions(@NonNull AppCompatActivity context, @NonNull Consumer<Boolean> listener) {
        new RxPermissions(context).request(Manifest.permission.CAMERA)
                .subscribe(listener);
    }

    @SuppressLint("CheckResult")
    public static void checkCameraPermissions(@NonNull AppCompatActivity context, @NonNull Consumer<Boolean> listener) {
        new RxPermissions(context)
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(listener);
    }

    /**
     * @param context 读写的权限
     */
    public static void checkFileReadWritePermissions(@NonNull AppCompatActivity context) {
        new RxPermissions(context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public static void checkFileReadWritePermissions(@NonNull AppCompatActivity context, @NonNull Consumer<Boolean> listener) {
        new RxPermissions(context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(listener);
    }

    /**
     * @param context
     * @param listener 定位的 权限
     */
    public static void checkLocationPermissions(AppCompatActivity context, Observer<Boolean> listener) {
        new RxPermissions(context)
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(listener);
    }

    /**
     * @param pkgPath
     * @return 手机是否root
     */
    public static boolean rootCommand(String pkgPath) {
        String commandStr = "chmod 777 " + pkgPath;
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(commandStr + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
        return true;
    }

}
