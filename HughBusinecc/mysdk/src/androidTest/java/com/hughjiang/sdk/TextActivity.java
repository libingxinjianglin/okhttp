package com.hughjiang.sdk;

import android.test.AndroidTestCase;
import android.util.Log;

import com.hugh.okhttp.httpclient.CommontClient;
import com.hugh.okhttp.listener.ResponseHandler;
import com.hugh.okhttp.listener.ResponseListener;
import com.hugh.okhttp.request.CommonRequest;
import com.hugh.okhttp.response.CommonCallBack;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class TextActivity extends AndroidTestCase {
    private static final String TAG = "TextActivity";
    public void testAPP(){
        CommontClient.setRequest(CommonRequest.createGetRequest("https://www.imooc.com",null),new CommonCallBack(new ResponseHandler(new ResponseListener() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailer(Object object) {
                Log.e(TAG, "onFailer: "+object.toString() );
            }
        })));
    }
}
