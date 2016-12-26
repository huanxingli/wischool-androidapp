package cn.wischool.ws1app.wischool_androidapp.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import cn.wischool.ws1app.wischool_androidapp.infobean.UserInfo;

/**
 * Created by Administrator on 2015/10/20.
 */
public class GsonHelper {

    //将json转化为实体对象
    public static <T> T parseObject(String json,Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, TypeToken.get(clazz).getType());
    }

    //只需要json数据的某一个位置转化为实体
    public static <T> T parseObject(String key,String json,Class<T> clazz){
        JSONObject object;
        try{
            object = new JSONObject(json);
            String newJson = null;
            if (object.has(key)){
                newJson = object.getString(key);
                return parseObject(newJson,clazz);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int parseInt(String key,String json){
        JSONObject object;
        try {
            object = new JSONObject(json);
            if (object.has(key)){
                return object.getInt(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String parseString(String key,String json){
        JSONObject object;
        try {
            object = new JSONObject(json);
            if (object.has(key)){
                return object.getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
