package cn.wischool.ws1app.wischool_androidapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class AppManager {
	
	private static Stack<Activity> activityStack;
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}

	private static AppManager instance;


	private AppManager(){}

	public int getActivityStackSize()
	{
        if(activityStack==null){
           return 0;
        }
		return activityStack.size();

	}
	/**
	 * 单一实例
	 */
	public static AppManager getAppManager(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);

		Log.i("AppManager","addActivity ActivityStackSize:"+getActivityStackSize());
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	/**
     * 获取前一个Activity（当前Activity前一个Activity）
     */
    public Activity beforeActivity(){
        Activity activity=activityStack.get(activityStack.size()-2);
        return activity;
    }
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
			activity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			activity=null;
			Log.i("AppManager","finishActivity ActivityStackSize:"+getActivityStackSize());
		}
	}

	/**
	 * 结束指定数量的Activity
	 */
	public void finishManyActivity(int count){
		for (int i = 0; i < count; i++){
			Activity activity=activityStack.pop();;
			activity.finish();
			activity=null;
           
	    }
	}
	
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			
			System.exit(0);
		} catch (Exception e) {	}
	}
}