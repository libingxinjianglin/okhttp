package com.hugh.okhttp.httpclient;

import com.hugh.okhttp.https.HttpsUtils;
import com.hugh.okhttp.response.CommonCallBack;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author hugh
 * @funcion 创建一个okhttp的核心的类进行一些延迟的操作
 */

public class CommontClient {
    public static final int TIME = 30;
    public static OkHttpClient mOkHttpClient;

    static {

    }

    /**
     * 发送我们具体http/https的请求
     * @param request
     * @param callback
     * @return
     */
    public static Call setRequest(Request request, CommonCallBack callback){
        OkHttpClient.Builder mBulider = new OkHttpClient.Builder();
        mBulider.connectTimeout(TIME, TimeUnit.SECONDS);
        mBulider.writeTimeout(TIME, TimeUnit.SECONDS);
        mBulider.readTimeout(TIME, TimeUnit.SECONDS);

        //表示能够进行重定向默认的话也会进行重定向的
        mBulider.followRedirects(true);

        //支持Https协议
        mBulider.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        // mBulider.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        //生成Client对象
        mOkHttpClient = mBulider.build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
