package cn.wischool.ws1app.wischool_androidapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wischool.ws1app.wischool_androidapp.R;

/**
 * Created by Administrator on 2015/10/23.
 */
public class ProgressHUD extends Dialog {
    public ProgressHUD(Context context){
        super(context);
    }
    public ProgressHUD(Context context,int theme){
        super(context,theme);
    }

    private static ImageView imageView;

    /**
     * 创建一个dialog,并显示出来
     * @param context：上下文
     * @param message：要显示的内容
     * @param indeterminate：进度条是否有明确的时间
     * @param cancelable：是否可取消
     * @param cancelListener：关闭监听器
     * @return
     */
    public static ProgressHUD show(Context context,CharSequence message,boolean indeterminate,boolean cancelable,
                            OnCancelListener cancelListener){
        ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_bar_loading);
        if (message==null || message.length()==0){
            dialog.findViewById(R.id.textView_message).setVisibility(View.GONE);
        }else{
            TextView tv = (TextView)dialog.findViewById(R.id.textView_message);
            tv.setText(message);
        }
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.spinner);
        LinearInterpolator in = new LinearInterpolator();
        animation.setInterpolator(in);
        imageView = (ImageView)dialog.findViewById(R.id.spinnerImageView);
        imageView.setAnimation(animation);

        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity= Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        lp.dimAmount=0.5f;
        lp.alpha=1.0f;//增加透明度
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        return dialog;
    }
}
