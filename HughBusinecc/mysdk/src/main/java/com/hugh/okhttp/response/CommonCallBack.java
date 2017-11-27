package com.hugh.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.hugh.okhttp.listener.ResponseHandler;
import com.hugh.okhttp.listener.ResponseListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author hugh
 * @function 对Response进行一个封装
 */

public class CommonCallBack implements Callback{
    //与服务器协商返回的字段
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义的异常
     */
    private static final int JSON_ERROT = -2;
    private static final int NETWORK_ERROR = -1;
    private static final int OTHER_ERROR = -3; //不知道的异常

    //网络得到数据之后通过handler进行转到主线程进行UI的刷新
    private Handler mHandler;

    //请求成功或者失败之后必然还要进行调用我们自己的事件监听，避免API改变后造成的一些异常
    private ResponseListener mListener;

    //得到数据之后我们进行转化成实体类比如Gson
    private Class<?> mClass;

    public CommonCallBack(ResponseHandler mHandler){
        this.mListener = mHandler.mListener;
        this.mClass = mHandler.mClass;
        this.mHandler = new Handler(Looper.getMainLooper());
    }
    @Override
    public void onFailure(Call call, IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 还没有进行处理异常日后进行补充
                 */
                mListener.onFailer(new Exception());
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(response);
            }
        });

    }

    private void handlerResponse(Response response) {
        if(response == null ){
            mListener.onFailer(new Exception());
            return ;
        }else{
            try {
                JSONObject mJson = new JSONObject(response.toString());
                //判断一下我们的json里面是不是有我们的那个key值
               if(mJson.has(RESULT_CODE)){
                   //从json中取出我们的响应码如果为0的话说明这个是正确的
                   if(mJson.getInt(RESULT_CODE) == RESULT_CODE_VALUE){
                        if(mClass == null){
                            mListener.onSuccess(response);
                        }else{
                            /**
                             * 这个地方进行Gson解析看情况进行设计
                             */
                        }
                   }else{
                        mListener.onFailer(new Exception());
                   }
               }
            }catch (Exception e){
                mListener.onFailer(new Exception());
            }
        }
    }
}
