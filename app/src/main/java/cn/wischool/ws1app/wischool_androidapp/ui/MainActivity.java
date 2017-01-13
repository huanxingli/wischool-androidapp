package cn.wischool.ws1app.wischool_androidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.wischool.ws1app.wischool_androidapp.AppManager;
import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment.CarFragment;
import cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment.HomeFragment;
import cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment.MeFragment;
import cn.wischool.ws1app.wischool_androidapp.ui.tab_fragment.ShequFragment;


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
    private ShequFragment shequFragment;

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
                changeFragment(new HomeFragment(),true);
                break;
            case R.id.tab_shequ:
                changeFragment(new ShequFragment(),true);
                break;
            case R.id.tab_car:
                changeFragment(new CarFragment(),true);
                break;
            case R.id.tab_me:
                changeFragment(new MeFragment(),true);
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
}
