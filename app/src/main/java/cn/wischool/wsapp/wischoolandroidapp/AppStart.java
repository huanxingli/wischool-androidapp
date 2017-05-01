package cn.wischool.wsapp.wischoolandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import java.util.Date;

import cn.wischool.wsapp.wischoolandroidapp.common.SharedPreferencesHelper;
import cn.wischool.wsapp.wischoolandroidapp.common.SharedPreferencesLifecycle;
import cn.wischool.wsapp.wischoolandroidapp.model.db.DatabaseHelper;
import cn.wischool.wsapp.wischoolandroidapp.ui.BaseFragmentActivity;
import cn.wischool.wsapp.wischoolandroidapp.ui.Login;
import cn.wischool.wsapp.wischoolandroidapp.ui.MainActivity;

/**
 * Created by sunyuhong on 15/8/31.
 */
public class AppStart extends BaseFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesHelper.putAppStartTime(this, new Date());

        final View view = View.inflate(this,R.layout.activity_start,null);
        setContentView(view);

        DatabaseHelper.getInstance(this,"wischool.db");

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });

        //ApiClient.init(this);

    }

    private void redirectTo(){
        boolean hasLogin= SharedPreferencesHelper.getBoolean(this, SharedPreferencesLifecycle.forever,"hasLogin");

        Intent intent ;
        if (hasLogin)
        {
            intent = new Intent(this, MainActivity.class);
//            intent.putExtra("startActivity",getIntent().getStringExtra("startActivity"));
        }
        else
        {
            intent = new Intent(this, Login.class);
        }
//        SharedPreferencesHelper.putString(getApplicationContext(), SharedPreferencesLifecycle.forever, "dbVersion", String.valueOf(DatabaseHelper.DATABASE_VERSION));
        startActivity(intent);
        finish();
    }
}
