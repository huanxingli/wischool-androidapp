package cn.wischool.wsapp.wischoolandroidapp.api;

//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

import cn.wischool.wsapp.wischoolandroidapp.infobean.UserInfo;

/**
 * Created by sunyuhong on 15/10/2.
 */
public class ApiClient {

//    private static AsyncHttpClient client = null ;

    public static String versionName="1.0";

    public static UserInfo userInfo = null;

//    public static void init(Context context)
//    {
//        if(client==null)
//        {
//            try {
////                ApiClient.versionName= UiHelper.getInstance().getAppVersionName(context);
//                client=new AsyncHttpClient();
////                client.setUserAgent("wischoole app "+ versionName +" ("+ android.os.Build.BRAND+";"+android.os.Build.MODEL+";android "+android.os.Build.VERSION.RELEASE+")");
//
////                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
////                trustStore.load(null, null);
////                SSLSocketFactoryEx  sf = new SSLSocketFactoryEx(trustStore);
////                sf.stetHostnameVerifier(SSLSockeFactoryEx.ALLOW_ALL_HOSTNAME_VERIFIER);
////                client.setSSLSocketFactory(sf);
//            }
//            catch (Exception e) {
//
//            }
//        }
//    }
//
//    public static void getUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.get(url, params, responseHandler);
//    }
//
//    public static void postUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.post(url, params, responseHandler);
//    }

}
