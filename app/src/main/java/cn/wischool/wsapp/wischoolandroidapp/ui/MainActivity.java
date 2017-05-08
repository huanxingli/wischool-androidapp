package cn.wischool.wsapp.wischoolandroidapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.wischool.wsapp.wischoolandroidapp.AppManager;
import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.common.UiHelper;
import cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment.CarFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment.HomeFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment.MeFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.tab_fragment.CommunityFragment;

/**
 * Created by lihuanxing on 16/5/29.
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup group;
    private RadioButton homeRadio;
    private RadioButton shequRadio;
    private RadioButton carRadio;
    private RadioButton meRadio;

    private FragmentManager fragmentManager;

    private CarFragment carFragment;
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private CommunityFragment shequFragment;

    private long exitTime = 0;  //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        //默认首页被选中
        homeRadio.setChecked(true);
        group.setOnCheckedChangeListener(this);
        changeFragment(new HomeFragment(),false);
        AppManager.getAppManager().addActivity(this);
    }

    //初始化控件
    private void initView() {
        homeFragment = new HomeFragment();
        shequFragment = new CommunityFragment();
        carFragment = new CarFragment();
        meFragment = new MeFragment();
        group = (RadioGroup) findViewById(R.id.main_tabs);
        homeRadio = (RadioButton) findViewById(R.id.tab_home);
        shequRadio = (RadioButton) findViewById(R.id.tab_shequ);
        carRadio = (RadioButton) findViewById(R.id.tab_car);
        meRadio = (RadioButton) findViewById(R.id.tab_me);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.tab_home:
                changeFragment(homeFragment,true);
                break;
            case R.id.tab_shequ:
                changeFragment(shequFragment,true);
                break;
            case R.id.tab_car:
                changeFragment(carFragment,true);
                break;
            case R.id.tab_me:
                changeFragment(meFragment,true);
                break;
        }
    }

    public void changeFragment(Fragment fragment,boolean isFirst){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.tab_frame,fragment);
        if (!isFirst){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UiHelper.getInstance().toastMessage(this, "再按一次返回桌面");
                exitTime = System.currentTimeMillis();
            }else{
                AppManager.getAppManager().finishActivity(this);
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==9999){
            if(data!=null){
                String QRCode=data.getStringExtra("QRCode");
                if(QRCode!=null) {
                    homeFragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }
}
