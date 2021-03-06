package cn.wischool.wsapp.wischoolandroidapp.model.db.business;

import android.content.Context;

import cn.wischool.wsapp.wischoolandroidapp.common.SharedPreferencesHelper;
import cn.wischool.wsapp.wischoolandroidapp.model.db.DatabaseHelper;

/**
 * Created by Administrator on 2015/10/26.
 */
public abstract class BaseService {
    protected Context context;

    public BaseService(Context context){
        this.context = context;
    }

    //获取访问数据库接口
    public DatabaseHelper databaseHelper(){
        return DatabaseHelper.getInstance(this.context, SharedPreferencesHelper.getUserId(context));
    }

    /**
     *
     * 获取当前账号
     * @return
     */
    public String getCurrentUserId(){
        return SharedPreferencesHelper.getUserId(context);
    }
}
