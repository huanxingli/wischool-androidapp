package cn.wischool.wsapp.wischoolandroidapp.common;

/**
 * Created by lishaohui on 15/4/28.
 */
public enum SharedPreferencesLifecycle {
    forever,
    appStart,
    dataBaseVersionUpgrade,
    appVersionUpgrade,
    readOnce
}
