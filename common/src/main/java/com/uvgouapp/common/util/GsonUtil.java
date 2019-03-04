package com.uvgouapp.common.util;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  解析工具类
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    private GsonUtil() {
    }

    /**
     * @param object 任意对象
     * @return 转成json
     */
    public static String GsonToString(Object object) {
        String gsonString = null;
        try {
            if (gson != null && object != null) {
                gsonString = gson.toJson(object);
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gsonString;
    }

    /**
     * @param gsonString 字符串
     * @param cls
     * @return 转成bean
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        try {
            if (gson != null && !TextUtils.isEmpty(gsonString)) {
                t = gson.fromJson(gsonString, cls);
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString 需要解析的字符串
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        try {
            if (gson != null && !TextUtils.isEmpty(gsonString)) {
                list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
                }.getType());
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json 需要解析的字符串
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> JsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            if (!TextUtils.isEmpty(json)) {
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (final JsonElement elem : array) {
                    list.add(gson.fromJson(elem, cls));
                }
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param gsonString 需要解析的字符串
     * @return 转成list中有map
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        try {
            if (gson != null && !TextUtils.isEmpty(gsonString)) {
                list = gson.fromJson(gsonString,
                        new TypeToken<List<Map<String, T>>>() {
                        }.getType());
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString 需要解析的字符串
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        try {
            if (gson != null && !TextUtils.isEmpty(gsonString)) {
                map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
                }.getType());
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            ToastUtils.showShort("数据异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
