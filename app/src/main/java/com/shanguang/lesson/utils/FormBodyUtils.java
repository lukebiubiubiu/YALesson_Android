package com.shanguang.lesson.utils;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @Auth Luke
 * @Date 2016/11/30
 * @Time 16:07
 **/

public class FormBodyUtils {

    public static FormBody.Builder setFormContent(FormBody.Builder builder,String...strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                builder.add(strings[i],strings[i+1]);
            }
        }
        return builder;
    }
    public static Request.Builder setFormContent(Request.Builder builder, String...strings){
        for (int i = 0; i < strings.length; i++) {
            if (i%2==0){
                builder.addHeader(strings[i],strings[i+1]);
            }
        }
        return builder;
    }
}
