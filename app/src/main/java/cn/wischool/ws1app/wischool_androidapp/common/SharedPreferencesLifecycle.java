package cn.wischool.ws1app.wischool_androidapp.common;

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
