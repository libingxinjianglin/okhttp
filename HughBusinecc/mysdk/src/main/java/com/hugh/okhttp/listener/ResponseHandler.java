package com.hugh.okhttp.listener;

/**
 * @author hugh
 * @function 将数据转化到主线程中
 */

public class ResponseHandler {

    public ResponseListener mListener;
    //表示的是一个实体的类比如Gson解析的实体类
    public Class<?> mClass;

    public ResponseHandler(ResponseListener mListener){
        this.mListener = mListener;
    }

    public ResponseHandler(ResponseListener mListener,Class<?> mClass){
        this.mListener = mListener;
        this.mClass = mClass;
    }

}
