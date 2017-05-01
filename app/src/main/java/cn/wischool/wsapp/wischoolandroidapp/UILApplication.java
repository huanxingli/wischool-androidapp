package cn.wischool.wsapp.wischoolandroidapp;

/**
 * Created by Administrator on 2015/12/26.
 */
import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.support.multidex.MultiDexApplication;

import cn.smssdk.SMSSDK;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILApplication extends MultiDexApplication {
    //从短信SDK应用后台注册得到的APPKEY
    private static final String APPKEY = "1bce5ecde56cc";
    //从短信SDK应用后台注册得到的APPSECRET
    private static final String APPSECRET = "b7acbdf63449d5cbdf48808b6567cd37";

    private static UILApplication mApplication = null;

    public static UILApplication getInstance() {
        return mApplication;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {

        super.onCreate();
        SMSSDK.initSDK(this, APPKEY, APPSECRET,false);
        mApplication = this;
    }
}
