package com.shanguang.lesson.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/*
 * 提示类
 */
public class ToastUtils {
    static  boolean DEBUG = true;

    /**
     * 提示String短
     */
    public static void shortToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 提示Int短
     */
    public static void shortToast(Context mContext, int msg) {
        Toast.makeText(mContext, mContext.getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    /**
     * 提示String长
     */
    public static void longToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 提示Int长
     */
    public static void longToast(Context mContext, int msg) {
        Toast.makeText(mContext, mContext.getResources().getString(msg), Toast.LENGTH_LONG).show();
    }

    /**
     * 显示Log.e()
     */
    public static void Log_e(String context, String text) {
        if (DEBUG) {
            Log.e(context, text);
        }
    }
    /**
     * 显示Log.i()
     */
    public static void Log_i(String context, String text) {
        if (DEBUG) {
            Log.i(context, text);
        }
    }
    /**
     * 显示System.out.println()
     */
    public static void system_out(String text) {
        if (DEBUG) {
            System.out.println(text);
        }
    }
}
