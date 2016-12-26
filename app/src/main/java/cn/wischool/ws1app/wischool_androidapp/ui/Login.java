package cn.wischool.ws1app.wischool_androidapp.ui;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

import java.util.Date;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.api.ApiClient;
import cn.wischool.ws1app.wischool_androidapp.common.GsonHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.common.StringUtils;
import cn.wischool.ws1app.wischool_androidapp.common.URLAddress;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by sunyuhong on 15/8/29.
 */
@ContentView(R.layout.activity_login)
public class Login extends BaseFragmentActivity{

    @InjectView(R.id.et_account_id)
    private EditText accountIdEditText;

    @InjectView(R.id.et_account_psw)
    private EditText passwordEditText;

    @InjectView(R.id.loginBtn)
    private Button loginBtn;

    @InjectView(R.id.imageRegister)
    private ImageView imageRegister;

    @InjectView(R.id.view_accountid)
    private View viewAccountId;

    @InjectView(R.id.view_password)
    private View viewPassword;

    @InjectView(R.id.textView_accountid)
    private TextView textViewAccountId;

    @InjectView(R.id.textView_password)
    private TextView textViewPassword;

    @InjectView(R.id.textView_tips)
    private TextView textViewTips;

//    private TextView loginTextView, forgotTextView;
    private String accountId;
    private String password;
//    static String str = "";

//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            System.out.println("-------handler()-------" + str);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        loginTextView = (TextView) findViewById(R.id.loginTextView);
//        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/PingFang Light.ttf");
//        loginTextView.setTypeface(typeFace);
//        accountId = String.valueOf(accountIdEditText.getText());
//        password = String.valueOf(passwordEditText.getText());
        setListener();

    }

    void setListener(){

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountId = String.valueOf(accountIdEditText.getText());
                password = String.valueOf(passwordEditText.getText());

                //输入判断
                if (StringUtils.isEmpty(accountId)){
                    textViewTips.setText("用户名不能为空");
                    accountIdEditText.requestFocus();
                    return;
                }else{
                    textViewTips.setText("");
                }

                if (StringUtils.isEmpty(password)){
                    textViewTips.setText("密码不能为空");
                    passwordEditText.requestFocus();
                    return;
                }else{
                    textViewTips.setText("");
                }

                UiHelper.getInstance().showProgressDialog(v.getContext(), "用户登录中....", false, false, null);

//                RequestParams params = new RequestParams();
//                params.put("username", accountId);
//                ApiClient.postUrl(URLAddress.CHECKUSERURL, params, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                        String str = new String(bytes);
//                        Log.i("返回json数据", str);
//                        //解析json数据
//                        final User user = GsonHelper.parseObject("user", str, User.class);
//                        final int state = GsonHelper.parseInt("state", str);
//                        final String registerTime = GsonHelper.parseString("regtime", str);
//
//                        if (user == null) {
//                            UiHelper.getInstance().toastMessage(Login.this, "服务器返回数据为空");
//                            return;
//                        }
//
//                        if (!user.veritf(Login.this, password)) {
//                            return;
//                        }
//
//                        SharedPreferencesHelper.putUserId(getApplicationContext(), accountId);
//                        SharedPreferencesHelper.putPassword(getApplicationContext(), password);
//                        SharedPreferencesHelper.putBoolean(getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", true);
//                        SharedPreferencesHelper.putDateTime(getApplicationContext(), SharedPreferencesLifecycle.appStart, "lastLoginTime", new Date());
//                        SharedPreferencesHelper.putString(getApplicationContext(),SharedPreferencesLifecycle.forever,"registerTime",registerTime);
//                        redirectToMain();
//
//                    }
//
//                    @Override
//                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                        Log.d("网络连接失败", throwable.getMessage());
//                        UiHelper.getInstance().dialogMessage(Login.this, "提示框", "登录失败,请重试");
//                        UiHelper.getInstance().closeProgressDialog();
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        UiHelper.getInstance().closeProgressDialog();
//                    }
//                });


                SharedPreferencesHelper.putUserId(getApplicationContext(), accountId);
                SharedPreferencesHelper.putPassword(getApplicationContext(), password);
                SharedPreferencesHelper.putBoolean(getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", true);
                SharedPreferencesHelper.putDateTime(getApplicationContext(), SharedPreferencesLifecycle.appStart, "lastLoginTime", new Date());
                redirectToMain();
            }
        });

        imageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(),RegisterActivity.class);
//                startActivity(intent);
//                onDestroy();
                redirectToRegister();
            }
        });

        //用户名输入框失去焦点判断用户名是否为空并获取该用户类信息
        accountIdEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!(hasFocus)){//失去焦点
                    accountIdEditText.setBackgroundResource(R.drawable.login_edt_gray);
                    viewAccountId.setBackgroundResource(R.color.white);
                    textViewAccountId.setTextColor(Color.GRAY);
                    accountId = String.valueOf(accountIdEditText.getText());
                    if (StringUtils.isEmpty(accountId)){
                        textViewTips.setText("用户名不能为空");
                    }else{
                        textViewTips.setText("");
                    }
                }else{  //获得焦点
                    accountIdEditText.setBackgroundResource(R.drawable.login_edt_red);
                    viewAccountId.setBackgroundResource(R.color.red);
                    textViewAccountId.setTextColor(Color.RED);
                }
            }
        });

        //密码输入框失去焦点判断密码是否为空
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!(hasFocus)){//失去焦点
                    passwordEditText.setBackgroundResource(R.drawable.login_edt_gray);
                    viewPassword.setBackgroundResource(R.color.white);
                    textViewPassword.setTextColor(Color.GRAY);
                    password = String.valueOf(passwordEditText.getText());
                    if (StringUtils.isEmpty(password)){
                        textViewTips.setText("密码不能为空");
                    }else{
                        textViewTips.setText("");
                    }
                }else{//获得焦点
                    passwordEditText.setBackgroundResource(R.drawable.login_edt_red);
                    viewPassword.setBackgroundResource(R.color.red);
                    textViewPassword.setTextColor(Color.RED);
                }
            }
        });
    }

    //跳转到首页
    private void redirectToMain() {
        //隐藏软键盘
        //imm.hideSoftInputFromWindow(Login.this.getCurrentFocus().getWindowToken(),0);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        onDestroy();
    }

    private void redirectToRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        onDestroy();

    }
}
