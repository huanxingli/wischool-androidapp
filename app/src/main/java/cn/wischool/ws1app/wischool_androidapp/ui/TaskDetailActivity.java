package cn.wischool.ws1app.wischool_androidapp.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.wischool.ws1app.wischool_androidapp.R;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;
import cn.wischool.ws1app.wischool_androidapp.widget.MorePopWindow;
import roboguice.inject.ContentView;

/**
 * Created by Administrator on 2016/2/20.
 */
@ContentView(R.layout.activity_detail)
public class TaskDetailActivity extends BaseFragmentActivity implements View.OnClickListener{

    private TextView more;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);//去掉导航
        actionBar.setDisplayShowTitleEnabled(false);//去掉标题
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
        );

        View titleView = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        more = (TextView) titleView.findViewById(R.id.textView_more);
        back = (ImageButton) titleView.findViewById(R.id.imageButton_back);
        more.setOnClickListener(this);
        back.setOnClickListener(this);
        actionBar.setCustomView(titleView, lp);
        actionBar.show();
    }

    @Override
    public void finishCurrentActivity() {
        super.finishCurrentActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_more:
                MorePopWindow morePopWindow = new MorePopWindow(TaskDetailActivity.this);
                morePopWindow.showPopupWindow(more);
                break;
            case R.id.imageButton_back:
                UiHelper.getInstance().toastMessage(TaskDetailActivity.this,"你点击了返回");
                finishCurrentActivity();
                break;
        }

    }


}
