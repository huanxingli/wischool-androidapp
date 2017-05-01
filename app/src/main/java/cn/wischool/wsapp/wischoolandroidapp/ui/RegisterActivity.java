package cn.wischool.wsapp.wischoolandroidapp.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
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

//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

//import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.SMSSDK;
import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.StringUtils;
import cn.wischool.wsapp.wischoolandroidapp.common.UiHelper;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2015/10/14.
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseFragmentActivity {

    @InjectView(R.id.et_phone)
    private EditText phoneEditText;

    @InjectView(R.id.et_code)
    private EditText codeEditText;

    @InjectView(R.id.btn_getcode)
    private Button getCodeButton;

    @InjectView(R.id.et_password)
    private EditText passwordEditText;

    @InjectView(R.id.et_confirmpsw)
    private EditText confirmPasswordEditText;

    @InjectView(R.id.btn_register)
    private Button registerBtn;

    @InjectView(R.id.image_close)
    private ImageView iamgeClose;

    @InjectView(R.id.view_phone)
    private View viewPhone;

    @InjectView(R.id.view_code)
    private View viewCode;

    @InjectView(R.id.view_register_psw)
    private View viewPassword;

    @InjectView(R.id.view_register_conpsw)
    private View viewConpsw;

    @InjectView(R.id.textView_register_phone)
    private TextView textViewPhone;

    @InjectView(R.id.textView_register_psw)
    private TextView textViewPassword;

    @InjectView(R.id.textView_register_conPsw)
    private TextView textViewConPsw;

    @InjectView(R.id.tv_service)
    private TextView service;

    //从短信SDK应用后台注册得到的APPKEY
    private static final String APPKEY = "1bcd1e322f82c";
    //从短信SDK应用后台注册得到的APPSECRET
    private static final String APPSECRET = "5b9f86c0ff7317e68d2f518ed885ad1e";

    private Uri SMS_INBOX = Uri.parse("content://sms/");

    private String phone;
    private String password;
    private String confirmPsw;
    private SmsObserver smsObserver;

    private Timer timer = null;
    private TimerTask timerTask = null;
    private int time = 0;

    public Handler smsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("回调操作");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        smsObserver = new SmsObserver(this, smsHandler);
        getContentResolver().registerContentObserver(SMS_INBOX, true,
                smsObserver);
//        service = (TextView) findViewById(R.id.tv_service);
//
        //初始化短信SDK
//        SMSSDK.initSDK(this, APPKEY, APPSECRET);
//
//        EventHandler eh=new EventHandler(){
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//
//                Message msg = new Message();
//                msg.arg1 = event;
//                msg.arg2 = result;
//                msg.obj = data;
//                handler.sendMessage(msg);
//            }
//
//        };
//        SMSSDK.registerEventHandler(eh);
//
        setListener();
    }

    public void setListener(){
        //获取手机验证码
        getCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phoneEditText.getText().toString();
                if (!StringUtils.isEmpty(phone)) {
                    Log.i("电话号码：", phone);
                    SMSSDK.getVerificationCode("86", phone);
                    //一分钟倒计时
                    getCodeButton.requestFocus();
                    time = 60;
                    startTime();
                    getCodeButton.setEnabled(false);

                } else {
                    UiHelper.getInstance().toastMessage(RegisterActivity.this, "电话号码不能为空");
                }
            }
        });


        /**
         * 注册按钮
         * 判断密码和确认密码一致后，提交验证码
         */
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                phone = String.valueOf(phoneEditText.getText());
//                password = String.valueOf(passwordEditText.getText());
//                confirmPsw = String.valueOf(confirmPasswordEditText.getText());
//                if (password.equals(confirmPsw)) {
//                    if (!TextUtils.isEmpty(codeEditText.getText().toString())) {
//                        //从手机提交验证码
//                        SMSSDK.submitVerificationCode("86", phone, codeEditText.getText().toString());
//                    } else {
//                        UiHelper.getInstance().toastMessage(RegisterActivity.this, "验证码不能为空");
//                    }
//                }
//
//
//            }
//        });
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                RequestParams params = new RequestParams();
//                phone = String.valueOf(phoneEditText.getText());
//                password = String.valueOf(passwordEditText.getText());
////                params.put("username", phone);
////                params.put("password", password);
//                Log.i("电话、密码",phone + "、" + password);
////                ApiClient.postUrl(URLAddress.REGISTERURL, params, new AsyncHttpResponseHandler() {
////                    @Override
////                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
////                        String str = new String(bytes);
////                        Log.i("返回的json数据", str);
////                    }
////
////                    @Override
////                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
////                        UiHelper.getInstance().dialogMessage(RegisterActivity.this, "提示框", "注册不成功");
////                    }
////                });
//
//            }
//        });

        //手机号码输入框失去焦点或者获取焦点
        phoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {//失去焦点
                    viewPhone.setBackgroundResource(R.color.white);
                    phoneEditText.setBackgroundResource(R.drawable.login_edt_gray);
                    textViewPhone.setTextColor(Color.GRAY);
                } else {//获得焦点
                    viewPhone.setBackgroundResource(R.color.red);
                    phoneEditText.setBackgroundResource(R.drawable.login_edt_red);
                    textViewPhone.setTextColor(Color.RED);
                }
            }
        });

        //验证码输入框焦点监听
        codeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    viewCode.setBackgroundResource(R.color.white);
                    codeEditText.setBackgroundResource(R.drawable.login_edt_gray);
                } else {
                    viewCode.setBackgroundResource(R.color.red);
                    codeEditText.setBackgroundResource(R.drawable.login_edt_red);
                }
            }
        });

        //密码框焦点监听
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    viewPassword.setBackgroundResource(R.color.white);
                    passwordEditText.setBackgroundResource(R.drawable.login_edt_gray);
                    textViewPassword.setTextColor(Color.GRAY);
                } else {
                    viewPassword.setBackgroundResource(R.color.red);
                    passwordEditText.setBackgroundResource(R.drawable.login_edt_red);
                    textViewPassword.setTextColor(Color.RED);
                }
            }
        });

        //确认密码框失去焦点或者获取焦点
        confirmPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {//失去焦点
                    password = String.valueOf(passwordEditText.getText());
                    confirmPasswordEditText.setBackgroundResource(R.drawable.login_edt_gray);
                    viewConpsw.setBackgroundResource(R.color.white);
                    textViewConPsw.setTextColor(Color.GRAY);
                    confirmPsw = String.valueOf(confirmPasswordEditText.getText());
                    if (!password.equals(confirmPsw)) {
                        UiHelper.getInstance().toastMessage(RegisterActivity.this, "密码与确认密码不一致");
                    }
                } else {//获取焦点
                    viewConpsw.setBackgroundResource(R.color.red);
                    confirmPasswordEditText.setBackgroundResource(R.drawable.login_edt_red);
                    textViewConPsw.setTextColor(Color.RED);
                }
            }
        });

        //关闭当前页面
        iamgeClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
                onDestroy();
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        /**
         * msg==0x123获取验证码60秒倒计时
         *
         * 提交验证码成功之后，向服务器提交注册数据
         */
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if (msg.what==0x123){
                getCodeButton.setText("还剩" + msg.arg1 + "秒");
                getCodeButton.setBackgroundResource(R.drawable.loginpress_btn);
                getCodeButton.setTextColor(Color.WHITE);
                if (msg.arg1==0){
                    stopTime();
                    getCodeButton.setEnabled(true);
                    getCodeButton.setBackgroundResource(R.drawable.login_btn);
                    getCodeButton.setTextColor(Color.GRAY);
                    getCodeButton.setText("获取验证码");
                }
            }
            else{
                super.handleMessage(msg);
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Log.i("成功:", "" + result);
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                        UiHelper.getInstance().toastMessage(RegisterActivity.this, "提交验证码成功");
//                        RequestParams params = new RequestParams();
//                        params.put("username", phone);
//                        params.put("password", password);
//                        ApiClient.postUrl(URLAddress.REGISTERURL, params, new AsyncHttpResponseHandler() {
//                            @Override
//                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                                String str = new String(bytes);
//                                Log.i("返回的json数据", str);
//                                //解析json数据
//                                final int status = GsonHelper.parseInt("status", str);
//
//                                if (status == 4004) {
//                                    UiHelper.getInstance().dialogMessage(RegisterActivity.this, "提示框", "该电话号码已经被注册，请重新注册！");
//                                    return;
//                                }
//
//                                final User user = GsonHelper.parseObject("user", str, User.class);
//                                final String registerTime = GsonHelper.parseString("regtime", str);
//
//                                SharedPreferencesHelper.putUserId(getApplicationContext(), phone);
//                                SharedPreferencesHelper.putPassword(getApplicationContext(), password);
//                                SharedPreferencesHelper.putBoolean(getApplicationContext(), SharedPreferencesLifecycle.forever, "hasLogin", true);
//                                SharedPreferencesHelper.putDateTime(getApplicationContext(), SharedPreferencesLifecycle.appStart, "lastLoginTime", new Date());
//                                SharedPreferencesHelper.putString(getApplicationContext(), SharedPreferencesLifecycle.forever, "registerTime", registerTime);
//
//                                redirectToMain();
//                            }
//
//                            @Override
//                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                                UiHelper.getInstance().dialogMessage(RegisterActivity.this, "提示框", "注册不成功");
//                            }
//                        });
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        UiHelper.getInstance().toastMessage(RegisterActivity.this, "验证码已经发送");
                    }
                } else {
                    try {
                        Log.e("event2", "失败" );
                        Throwable throwable = (Throwable) data;
                        throwable.printStackTrace();
                        JSONObject object = null;
                        object = new JSONObject(throwable.getMessage());
                        String des = object.optString("detail");//错误描述
                        System.out.println("错误描述" + des);
                        int status = object.optInt("status");//错误代码
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    };

    public void startTime(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message message = handler.obtainMessage();
                message.what = 0x123;
                message.arg1 = time;
                handler.sendMessage(message);
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopTime(){
        timer.cancel();
    }

    public void getSmsFromPhone() {
        ContentResolver cr = getContentResolver();
        String[] projection = new String[]{"_id", "address", "person",
                "body", "date", "type"};//"_id", "address", "person",, "date", "type
        Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc");
        if (null == cur) {
            return;
        }
        if (cur.moveToNext()) {
            String body = cur.getString(cur.getColumnIndex("body"));
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(body);
            String str = matcher.replaceAll("");
            codeEditText.setText(str);
//            System.out.println("验证码：" + str);
            cur.close();

        }
    }

    //跳转到首页
    private void redirectToMain() {
        //隐藏软键盘
        //imm.hideSoftInputFromWindow(Login.this.getCurrentFocus().getWindowToken(),0);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        onDestroy();
    }


    /**
     * 监听短信的数据库变化
     */
    class SmsObserver extends ContentObserver {

        public SmsObserver(Context context, Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            //每当有新短信到来时，使用我们获取短消息的方法
            getSmsFromPhone();
        }
    }
}
