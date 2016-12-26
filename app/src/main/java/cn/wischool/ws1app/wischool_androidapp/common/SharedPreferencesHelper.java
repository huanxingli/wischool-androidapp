package cn.wischool.ws1app.wischool_androidapp.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunyuhong on 15/10/1.
 */
public class SharedPreferencesHelper {

    public static void putAppStartTime(Context context, Date value) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("AppStartTime", df.format(value));
        edit.commit();
    }

    public static Boolean getBoolean(Context context,SharedPreferencesLifecycle lifecycle, String key) {
        if (getString(context,lifecycle,key,"否").equals("是"))
        {
            return true;
        }

        return false;
    }

    public static void putBoolean(Context context, SharedPreferencesLifecycle lifecycle,String key, Boolean value) {

        putString(context, lifecycle, key, value ? "是" : "否");

    }

    public static String getString(Context context,SharedPreferencesLifecycle lifecycle, String key, String defaultValue) {
        String userId=getUserId(context);

        String lifecycleKey=getLifeCycleKey(context,lifecycle);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String returnValue=preferences.getString(key+userId+lifecycleKey, defaultValue);

        //只读一次的，读完后，就清空
        if (lifecycle==SharedPreferencesLifecycle.readOnce)
        {
            putString(context,lifecycle,key,null);
        }

        return returnValue;
    }

    public static String getString(Context context,SharedPreferencesLifecycle lifecycle, String key) {
        return getString(context,lifecycle, key, null);
    }

    private static String getLifeCycleKey(Context context,SharedPreferencesLifecycle lifecycle)
    {
        if (lifecycle==SharedPreferencesLifecycle.appVersionUpgrade)
        {
//            return "appversion"+UiHelper.getInstance().getAppVersionName(context);
        }
        else if(lifecycle==SharedPreferencesLifecycle.dataBaseVersionUpgrade)
        {
//            return "databaseversion"+ DatabaseHelper.DATABASE_VERSION;
        }
        else if(lifecycle==SharedPreferencesLifecycle.appStart)
        {
//            return "appstart"+getAppStartTime(context);
        }
        else if(lifecycle==SharedPreferencesLifecycle.readOnce)
        {
            return "readOnce";
        }
        else if(lifecycle==SharedPreferencesLifecycle.forever) {
            return "forever";
        }
        return "";
    }

    public static void putString(Context context, SharedPreferencesLifecycle lifecycle,String key, String value) {

        String userId=getUserId(context);

        String lifecycleKey=getLifeCycleKey(context,lifecycle);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();

        edit.putString(key+userId+lifecycleKey, value);

        edit.commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("UserId", "");
    }

    public static void putUserId(Context context, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("UserId", value);
        edit.commit();
    }


    public static String getPassword(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("Password", "");
    }

    public static void putPassword(Context context, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("Password", value);
        edit.commit();
    }

    public static Date getDateTime(Context context,SharedPreferencesLifecycle lifecycle, String key) {

        if (StringUtils.isEmpty(getString(context,lifecycle,key)))
        {
            return null;

        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date=null;
        try {

            date = df.parse(getString(context,lifecycle,key));
        } catch (ParseException e) {
        }

        return date;
    }

    public static void putDateTime(Context context,SharedPreferencesLifecycle lifecycle, String key, Date value) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        putString(context, lifecycle, key, df.format(value));
    }

}
