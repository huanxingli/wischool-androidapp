package cn.wischool.wsapp.wischoolandroidapp.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import cn.wischool.wsapp.wischoolandroidapp.R;

/**
 * Created by Administrator on 2016/2/20.
 */
public class MorePopWindow extends PopupWindow {
    private View conentView;

    public MorePopWindow(final Activity context){
        LayoutInflater inflater =(LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_more_dialog,null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        //设置popupWindow的View
        this.setContentView(conentView);
        //设置popupWindow弹出窗体的宽
        this.setWidth(w / 2);
        //设置popupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //刷新状态
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        //点back键和其它地方使其消失，设置了这个才能触发OnDismisslistener,设置其它控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        LinearLayout addTaskLayout = (LinearLayout) conentView
                .findViewById(R.id.add_task_layout);
        LinearLayout teamMemberLayout = (LinearLayout) conentView
                .findViewById(R.id.team_member_layout);
        addTaskLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MorePopWindow.this.dismiss();
            }
        });

        teamMemberLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MorePopWindow.this.dismiss();
            }
        });
    }

    public void showPopupWindow(View parent){
        if (!this.isShowing()){
            //以下拉方式显示popupWindow
            this.showAsDropDown(parent,parent.getLayoutParams().width/2,18);
        }else{
            this.dismiss();
        }
    }
}
