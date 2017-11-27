import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.hugh.okhttp.httpclient.CommontClient;
import com.hugh.okhttp.listener.ResponseHandler;
import com.hugh.okhttp.listener.ResponseListener;
import com.hugh.okhttp.request.CommonRequest;
import com.hugh.okhttp.response.CommonCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class TestActivity extends AndroidTestCase{
    private static final String TAG = "TestActivity";
   public void text(){
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
