package drawable.tencent.com.hughbusinecc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hugh.okhttp.httpclient.CommontClient;
import com.hugh.okhttp.https.HttpsUtils;
import com.hugh.okhttp.listener.ResponseHandler;
import com.hugh.okhttp.listener.ResponseListener;
import com.hugh.okhttp.request.CommonRequest;
import com.hugh.okhttp.response.CommonCallBack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import Fragment.BaseFragment;
import Fragment.HomeFragment;
import Fragment.MessageFragment;
import Fragment.SettingFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout mHomeLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mSettingLayout;

    private TextView mHomeLabel;
    private TextView mHomeImage;

    private TextView mMessageLabel;
    private TextView mMessageImage;

    private TextView mSettingLabel;
    private TextView mSettingImage;

    private FragmentManager mManager;
    private BaseFragment mHomeFragemnt,mMessageFragment,mSettingFragment;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        mHomeLayout = (RelativeLayout)findViewById(R.id.activity_home_view);
        mHomeLayout.setOnClickListener(this);

        mMessageLayout = (RelativeLayout)findViewById(R.id.activity_message_view);
        mMessageLayout.setOnClickListener(this);

        mSettingLayout = (RelativeLayout)findViewById(R.id.activity_setting_view);
        mSettingLayout.setOnClickListener(this);

        mHomeLabel = (TextView)findViewById(R.id.home_label_view);
        mHomeImage = (TextView)findViewById(R.id.home_image_view);

        mMessageLabel = (TextView)findViewById(R.id.message_label_view);
        mMessageImage = (TextView)findViewById(R.id.message_image_view);

        mSettingLabel = (TextView)findViewById(R.id.setting_label_view);
        mSettingImage = (TextView)findViewById(R.id.setting_image_view);

        mManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction beginTransaction = mManager.beginTransaction();
        switch(view.getId()){
            case R.id.activity_home_view:
                hideFragment(beginTransaction,mMessageFragment);
                hideFragment(beginTransaction,mSettingFragment);

                CommontClient.setRequest(CommonRequest.createGetRequest("https://www.imooc.com",null), new CommonCallBack(new ResponseHandler(new ResponseListener() {
                    @Override
                    public void onSuccess(Object object) {
                        Log.e(TAG, "onSuccess: "+ object.toString());
                    }

                    @Override
                    public void onFailer(Object object) {
                        Log.e(TAG, "onFailer: "+object.toString() );
                    }
                })));

//                new Thread(){
//                    @Override
//                    public void run() {
//                        OkHttpClient.Builder mBulid = new OkHttpClient.Builder();
//                        mBulid.connectTimeout(200, TimeUnit.SECONDS);
//                        mBulid.hostnameVerifier(new HostnameVerifier() {
//                            @Override
//                            public boolean verify(String s, SSLSession sslSession) {
//                                return true;
//                            }
//                        });
//                      //  mBulid.sslSocketFactory(HttpsUtils.getSslSocketFactory());
//                        OkHttpClient build = mBulid.build();
//                        Request mRequest = new Request.Builder().url("https://www.imooc.com").get().build();
//                        Call call = build.newCall(mRequest);
//                        call.enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//                                Log.e(TAG, "onResponse: "+response.toString() );
//                            }
//                        });
//                    }
//                }.start();


                if(mHomeFragemnt == null){
                    mHomeFragemnt = new HomeFragment();
                    beginTransaction.replace(R.id.content_layout,mHomeFragemnt);
                }else{
                    beginTransaction.add(R.id.content_layout,mHomeFragemnt);
                }
                break;
            case R.id.activity_message_view:
                hideFragment(beginTransaction,mHomeFragemnt);
                hideFragment(beginTransaction,mSettingFragment);

                if(mMessageFragment == null){
                    mMessageFragment = new MessageFragment();
                    beginTransaction.replace(R.id.content_layout,mMessageFragment);
                }else{
                    beginTransaction.add(R.id.content_layout,mMessageFragment);
                }
                break;
            case R.id.activity_setting_view:
                hideFragment(beginTransaction,mMessageFragment);
                hideFragment(beginTransaction,mHomeFragemnt);

                if(mSettingFragment == null){
                   mSettingFragment = new SettingFragment();
                    beginTransaction.replace(R.id.content_layout,mSettingFragment);
                }else{
                    beginTransaction.add(R.id.content_layout,mSettingFragment);
                }
                break;
            default:
                break;
        }
        beginTransaction.commit();
    }

    private void hideFragment(FragmentTransaction beginTransaction,BaseFragment mFragment) {
        if(mFragment != null)
            beginTransaction.remove(mFragment);
    }
}
