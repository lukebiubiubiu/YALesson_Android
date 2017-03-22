package com.shanguang.lesson.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;


import com.shanguang.lesson.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SingleOkHttpUtils {
    private volatile static SingleOkHttpUtils mInstance = null;
    private static String TAG = "SingleOkHttpUtils";
    private OkHttpClient mOkHttpClient;
    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

    public static SingleOkHttpUtils setInit(OkHttpClient ok) {
        if (mInstance == null) {
            synchronized (SingleOkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SingleOkHttpUtils(ok);
                }
            }
        }
        return mInstance;
    }

    /**
     * 构造器
     **/

    public SingleOkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    public static FormBody.Builder getRequestBody(String... strings) {
        FormBody.Builder formBody = new FormBody.Builder();
        return FormBodyUtils.setFormContent(formBody, strings);
    }

    /**
     * okhttp的同步调用方法
     **/
    public static String okSyncUrl(Activity context, String url) throws IOException {
        String body = "";
        Request request = new Request.Builder().addHeader("Connection", "close").addHeader("Content-Length", "0").url(url).build();
        Response response = mInstance.getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            errorCode(response, context);
        }
        response.close();
        return body;
    }

    /**
     * okhttp的同步调用方法
     **/
    public static String okSyncPost(Activity context, String url, String... strings) throws IOException {
        String body = "";
        RequestBody formBody = null;
        formBody = getRequestBody(strings).build();
        Request request = new Request.Builder().addHeader("Connection", "close").addHeader("Content-Length", "0").url(url).post(formBody).build();
        Response response = mInstance.getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            errorCode(response, context);
        }
        response.close();
        return body;
    }

    /**
     * 提交图片
     **/
    public static String okSyncPostPic(Activity context, String url, String str1, String str2, String str3, String str4, String str5,
                                       String str6) throws IOException {
        String body = "";
        //File files= new File("/storage/sdcard0/dgj/dgj_1479698061778.jpg");
//        Bitmap bitmap = BitmapFactory.decodeFile(str2);
        Bitmap bitmap = BitmapUtil.getSmallBitmap(str2);
        byte[] datas = bitmap2Bytes(bitmap);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(str1, "imag.jpg",
                        RequestBody.create(MediaType.parse("image/png"), datas))
                .addFormDataPart(str3, str4)
                .addFormDataPart(str5, str6)
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = mInstance.getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            errorCode(response, context);
        }
        response.close();
        return body;
    }

    private static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
        return baos.toByteArray();
    }

    /**
     * 适配头标题请求
     **/
    public static String doheader(String header, String token, String json,
                                  String uls, Activity activity) throws IOException {
        String body = "";
        RequestBody formBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().addHeader(header, token).addHeader("Connection", "close").addHeader("Content-Length", "0").url(uls)
                .post(formBody).build();
        Response response = mInstance.getOkHttpClient().newCall(
                request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
            response.body().close();
        } else {
            errorCode(response, activity);
        }
        return body;
    }

    /**
     * 适配大管家各种请求
     **/
    public static String okHttpJson(String header, String token, String json,
                                    String uls, Activity activity) throws IOException {
        return doheader(header, token, json, uls, activity);
    }

    /**
     * 适配json传发但是没有header，
     **/
    public static String doJson(String str1, String json, String token, String tokee, String uls,
                                Activity activity) throws IOException {
        String body = "";
        RequestBody formBody = new FormBody.Builder().add(str1, json).add(token, tokee).build();
        Request request = new Request.Builder().url(uls).post(formBody).build();
        Response response = mInstance.getOkHttpClient().newCall(
                request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            errorCode(response, activity);
        }
        return body;
    }

    public static String doHttps(String header, String token, String json,
                                 SSLSocketFactory sslSocketFactory, String url) throws IOException {
        String body = "";
        OkHttpClient okHttpClient = null;
        okHttpClient = new OkHttpClient.Builder().sslSocketFactory(
                sslSocketFactory).build();
        // okHttpClient.setSslSocketFactory(sslSocketFactory);
        RequestBody formBody = FormBody.create(JSON, json);
        Request request = new Request.Builder().url(url)
                .addHeader(header, token).post(formBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
            response.body().close();
        } else {
            throw new IOException("Unexpected code " + response);
        }
        return body;
    }

    public static String doJson(String str1, String json, String uls,
                                Activity activity) throws IOException {
        String body = "";
        RequestBody formBody = new FormBody.Builder().add(str1, json).build();
        Request request = new Request.Builder().url(uls).post(formBody).build();
        Response response = mInstance.getOkHttpClient().newCall(
                request).execute();
        if (response.isSuccessful()) {
            body = response.body().string();
        } else {
            errorCode(response, activity);
        }
        return body;
    }

    public static void errorCode(Response response, final Activity context)
            throws IOException {
        Log.e(TAG, "response.code() ：" + response.code());
        switch (response.code()) {
            case 404:
                if (context != null) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.shortToast(context,
                                    R.string.message_server_unavailable);
                        }
                    });
                }
                break;
            case 500:
                if (context != null) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.shortToast(context,
                                    R.string.unrecoverable_error);
                        }
                    });
                }
                break;
            case 1111:
                /** 单点登录, */

                break;
            default:
                throw new IOException("Unexpected code " + response);
        }
    }


    public static void httpException(Exception e, final Activity activity) {
        if (e instanceof UnknownHostException) {
            Log.e("UnknownHostException", e.getStackTrace() + "");
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.shortToast(activity,
                                R.string.message_server_unavailable);
                    }
                });
            }

        } else if (e instanceof ConnectException) {
            Log.e("ConnectException", e.getStackTrace() + "");
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils
                                .shortToast(activity, R.string.request_outtime);
                    }
                });
            }

        } else if (e instanceof SocketTimeoutException) {
            Log.e("SocketTimeoutException", e.getStackTrace() + "");
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.shortToast(activity,
                                R.string.socket_exception_error);
                    }
                });
            }

        } else if (e instanceof SocketException) {
            Log.e("SocketException", e.getStackTrace() + "");
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.shortToast(activity,
                                R.string.socket_exception);
                    }
                });
            }

        } else if (e instanceof IOException) {
            Log.e("IOException", e.getStackTrace() + "");
        } else {
            Log.e("Exception", e.getStackTrace() + "");
        }
    }


}
