package cn.wischool.wsapp.wischoolandroidapp.widget.alertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/4/21.
 */
public class MyAlertDialog  {

    public static void buildAlertDialog(Context context, String title, int d, EditText editText,
                                        MyAlertDialogInterface mdi){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setIcon(d)
                .setView(editText)
                .setPositiveButton("确定",mdi.positiveListener(editText))
                .setNegativeButton("取消",mdi.negativeListener())
                .create()
                .show();
    }
}
