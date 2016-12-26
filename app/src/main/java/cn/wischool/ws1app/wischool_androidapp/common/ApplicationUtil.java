package cn.wischool.ws1app.wischool_androidapp.common;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import cn.wischool.ws1app.wischool_androidapp.UILApplication;

/**
 * @author Vance
 */
public class ApplicationUtil {

    // 用于获取屏幕宽高
    private static DisplayMetrics displayMetrics = new DisplayMetrics();


    /**
     * 返回屏幕密度
     */
    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 返回屏幕密度
     */
    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 返回屏幕高(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 返回屏幕宽(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }



    /**
     * 返回屏幕宽(px)
     */
    public static int getScreenWidth(Activity activity) {
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 返回屏幕高(px)
     */
    public static int getScreenHeight(Activity activity) {
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity(UILApplication.getInstance()) + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity(UILApplication.getInstance()) + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}