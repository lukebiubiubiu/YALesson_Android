package com.shanguang.lesson.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地信息存储
 * @author liuwei
 * @date 2015年12月15日 上午11:38:41 
 *
 */
public class SharedPreferencesUtil {
    //多存
    public static void saveConfig(Context context, String kid, String userid, String token) {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = context.getSharedPreferences("userconf", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("kid", kid);
        editor.putString("userid",userid);
        editor.putString("token",token);
        //提交当前数据
        editor.commit();
    }
    public static String getKid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userconf", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        return sharedPreferences.getString("kid", "");
    }

    public static String getUserid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userconf", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        return sharedPreferences.getString("userid", "");
    }
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userconf", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        return sharedPreferences.getString("token", "");
    }
    //单存
    public static void saveTimeid(Context context, String timeid) {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = context.getSharedPreferences("timeidconf", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("timeid", timeid);
        //提交当前数据
        editor.commit();
    }
    public static String getTimeid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("timeidconf", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        return sharedPreferences.getString("timeid", "");
    }


}
