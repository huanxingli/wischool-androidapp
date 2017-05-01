package cn.wischool.wsapp.wischoolandroidapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.wischool.wsapp.wischoolandroidapp.R;
import cn.wischool.wsapp.wischoolandroidapp.widget.alertDialog.MyAlertDialog;
import cn.wischool.wsapp.wischoolandroidapp.widget.alertDialog.MyAlertDialogInterface;
import cn.wischool.wsapp.wischoolandroidapp.widget.PublishItem;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by lihuanxing on 2016/4/20.
 */
@ContentView(R.layout.activity_publishtask)
public class PublishActivity extends BaseFragmentActivity implements View.OnClickListener {

    @InjectView(R.id.publish_money)
    private TextView tvMoney;

    @InjectView(R.id.publish_type)
    private TextView tvType;

    @InjectView(R.id.publish_image)
    private ImageView image;

    @InjectView(R.id.view_title)
    private PublishItem customerTitle;

    @InjectView(R.id.view_time)
    private PublishItem customerTime;

    @InjectView(R.id.view_location)
    private PublishItem customerLocation;

    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    private void setListener(){
        tvMoney.setOnClickListener(this);
        customerTitle.setOnClickListener(this);
        customerLocation.setOnClickListener(this);
        customerTime.setOnClickListener(this);
        tvType.setOnClickListener(this);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x124){ //输入金额的
                tvMoney.setText(msg.arg1 + "");
            }else if (msg.what == 0x123){ //修改是单人任务还是多人任务
                if (msg.arg1 == 1){
                    tvType.setText("单人任务");
                    Picasso.with(getApplicationContext()).load(R.drawable.publish_single).into(image);
                }else {
                    tvType.setText("多人任务");
                    Picasso.with(getApplicationContext()).load(R.drawable.publish_double).into(image);
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publish_money:
                EditText editText = new EditText(PublishActivity.this);
                editText.setKeyListener(new DigitsKeyListener(false,true));
                MyAlertDialog.buildAlertDialog(PublishActivity.this,"请输入金额",android.R.drawable.ic_dialog_info,editText,
                        new MyAlertDialogInterface(PublishActivity.this,MyAlertDialogInterface.mPositiveListener,
                                MyAlertDialogInterface.mNegativeListener){
                            @Override
                            public void onMyPositive(String info) {
                                if (TextUtils.isEmpty(info) || "".equals(info)){//判断输入的内容是否非空
                                    return;
                                }
                                Message m = Message.obtain();
                                m.arg1 = Integer.parseInt(info);//获取金额数
                                m.what = 0x124;
                                handler.sendMessage(m);
                            }
                            @Override
                            public void onMyNegative() {
                            }
                        });
                break;
            case R.id.view_title:
                EditText editText1 = new EditText(PublishActivity.this);
                MyAlertDialog.buildAlertDialog(PublishActivity.this,"请输入任务标题",android.R.drawable.ic_dialog_info,editText1,
                        new MyAlertDialogInterface(PublishActivity.this,MyAlertDialogInterface.mPositiveListener,
                                MyAlertDialogInterface.mNegativeListener){
                            @Override
                            public void onMyPositive(String info) {
                                if (TextUtils.isEmpty(info) || "".equals(info)){//判断输入的内容是否非空
                                    return;
                                }
                                customerTitle.setText(info);
                            }
                            @Override
                            public void onMyNegative() {
                            }
                        });
                break;
            case R.id.view_location:
                EditText editText2 = new EditText(PublishActivity.this);
                MyAlertDialog.buildAlertDialog(PublishActivity.this,"请输入任务地点范围",android.R.drawable.ic_dialog_info,editText2,
                        new MyAlertDialogInterface(PublishActivity.this,MyAlertDialogInterface.mPositiveListener,
                                MyAlertDialogInterface.mNegativeListener){
                            @Override
                            public void onMyPositive(String info) {
                                if (TextUtils.isEmpty(info) || "".equals(info)){//判断输入的内容是否非空
                                    return;
                                }
                                customerLocation.setText(info);
                            }
                            @Override
                            public void onMyNegative() {
                            }
                        });
                break;
            case R.id.view_time:
                EditText editText3 = new EditText(PublishActivity.this);
                MyAlertDialog.buildAlertDialog(PublishActivity.this,"请输入任务时间",android.R.drawable.ic_dialog_info,editText3,
                        new MyAlertDialogInterface(PublishActivity.this,MyAlertDialogInterface.mPositiveListener,
                                MyAlertDialogInterface.mNegativeListener){
                            @Override
                            public void onMyPositive(String info) {
                                if (TextUtils.isEmpty(info) || "".equals(info)){//判断输入的内容是否非空
                                    return;
                                }
                                customerTime.setText(info);
                            }
                            @Override
                            public void onMyNegative() {
                            }
                        });
                break;
            case R.id.publish_type:
                popup = new PopupMenu(PublishActivity.this,tvType);
                //将R.menu.popup_menu菜单资源加载到popup的菜单中去
                getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
                // 为popup菜单的菜单项单击事件绑定事件监听器
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.single:  //单人任务
                                Message message = Message.obtain();
                                message.arg1 = 1;
                                message.what = 0x123;
                                handler.sendMessage(message);
                                popup.dismiss();
                                break;
                            case R.id.multi: //多人任务
                                Message message2 = Message.obtain();
                                message2.arg1 = 2;
                                message2.what = 0x123;
                                handler.sendMessage(message2);
                                popup.dismiss();
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                break;

        }
    }
}
