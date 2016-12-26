package cn.wischool.ws1app.wischool_androidapp.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import cn.wischool.ws1app.wischool_androidapp.widget.ProgressHUD;

/**
 * Created by sunyuhong on 15/10/1.
 */
public class UiHelper {

    private static UiHelper instance;
    private ProgressHUD dialog;

    /**
     * 单一实例
     */
    public static UiHelper getInstance() {
        if (instance == null) {
            instance = new UiHelper();
        }
        return instance;
    }

    public void toastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    //提示框
    public void dialogMessage(Context context,String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("提示框","点击了确定");
            }
        });
        builder.show();

    }

    public void showProgressDialog( Context context,  String msg,  boolean indeterminate,  boolean cancelable,  DialogInterface.OnCancelListener cancelListener) {

        closeProgressDialog();
        dialog = ProgressHUD.show(context, msg, indeterminate, cancelable,
                cancelListener);


    }

    public void closeProgressDialog(){
        if (dialog!=null){
            dialog.cancel();
        }

    }

    public boolean isOpenProgressDialog() {

        return dialog.isShowing();

    }


    public String getAppVersionName(Context context) {
        String versionName = "1.0.0";
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();

        }

        return versionName;
    }
}
