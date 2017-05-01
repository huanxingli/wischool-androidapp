package cn.wischool.wsapp.wischoolandroidapp.widget.alertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/4/21.
 */
public abstract class MyAlertDialogInterface {

    private Context context;

    public static DialogInterface.OnClickListener mPositiveListener = null;
    public static DialogInterface.OnClickListener mNegativeListener = null;

    public MyAlertDialogInterface(Context context, DialogInterface.OnClickListener mPosListen,
                                  DialogInterface.OnClickListener mNegListen){
        this.context = context;
        this.mPositiveListener = mPosListen;
        this.mNegativeListener = mNegListen;
    }

    public abstract void onMyPositive(String info);
    public abstract void onMyNegative();

    public DialogInterface.OnClickListener positiveListener(final EditText editText){
        mPositiveListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onMyPositive(editText.getText().toString());
            }
        };
        return mPositiveListener;
    }

    public DialogInterface.OnClickListener negativeListener(){
        mNegativeListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onMyNegative();
            }
        };
        return mNegativeListener;
    }


}
