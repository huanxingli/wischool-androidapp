package cn.wischool.wsapp.wischoolandroidapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.ui.taskshow_fragment.TaskListClassifyFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.taskshow_fragment.TaskListFragment;
import cn.wischool.wsapp.wischoolandroidapp.ui.taskshow_fragment.TaskListSelectFragment;

/**
 * Created by Administrator on 2015/10/28.
 */
//@ContentView(R.layout.activity_search)
public class TaskSearchActivity extends FragmentActivity implements View.OnClickListener{

    private RadioButton mRadTaskList;
    private RadioButton mRadTaskClassify;
    private RadioButton mRadTaskSelect;
    private EditText editTextSearch;

    private ImageView imagePublish;

    private Fragment taskListFrg;
    private Fragment taskClassifyFrg;
    private Fragment taskSelectFrg;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        initView();
        initEvent();
        select(0);
    }



    private void initView(){
        mRadTaskList = (RadioButton)findViewById(R.id.radio_tasklist);
        mRadTaskClassify = (RadioButton)findViewById(R.id.radio_classify);
        mRadTaskSelect = (RadioButton)findViewById(R.id.radio_select);
        editTextSearch = (EditText) findViewById(R.id.search_edt);
        imagePublish = (ImageView) findViewById(R.id.search_imageView2);
    }

    private void initEvent(){
        mRadTaskList.setOnClickListener(this);
        mRadTaskClassify.setOnClickListener(this);
        mRadTaskSelect.setOnClickListener(this);

        imagePublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PublishActivity.class);
                startActivity(i);
            }
        });

    }

    private void select(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                if (taskListFrg==null){
                    taskListFrg = new TaskListFragment();
                    transaction.add(R.id.id_content,taskListFrg);
                }else{
                    transaction.show(taskListFrg);
                }
                break;
            case 1:
                if (taskClassifyFrg==null){
                    taskClassifyFrg = new TaskListClassifyFragment();
                    transaction.add(R.id.id_content,taskClassifyFrg);
                }else{
                    transaction.show(taskClassifyFrg);
                }
                break;
            case 2:
                if (taskSelectFrg==null){
                    taskSelectFrg = new TaskListSelectFragment();
                    transaction.add(R.id.id_content,taskSelectFrg);
                }else{
                    transaction.show(taskSelectFrg);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (taskListFrg != null)
        {
            transaction.hide(taskListFrg);
        }
        if (taskClassifyFrg != null)
        {
            transaction.hide(taskClassifyFrg);
        }
        if (taskSelectFrg != null)
        {
            transaction.hide(taskSelectFrg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_tasklist:
                select(0);
                break;
            case R.id.radio_classify:
                select(1);
                break;
            case R.id.radio_select:
                select(2);
                break;
        }
    }
}
