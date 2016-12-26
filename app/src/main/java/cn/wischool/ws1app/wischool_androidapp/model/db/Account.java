package cn.wischool.ws1app.wischool_androidapp.model.db;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.wischool.ws1app.wischool_androidapp.common.StringUtils;
import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;

/**
 * Created by sunyuhong on 15/10/1.
 */

@DatabaseTable(tableName = "Info_Account")
public class Account {

    @DatabaseField(id = true)
    private String AccountId;

    @DatabaseField()
    private String Password;

    @DatabaseField()
    private String DeviceId;

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public boolean verify(Context context,String password) {
        if (this.getAccountId() == null) {
            UiHelper.getInstance().toastMessage(context, "您输入的账号不存在！");
            return false;
        }

        if (!this.getPassword().toLowerCase().equals(StringUtils.toMD5(password).toLowerCase())) {
            UiHelper.getInstance().toastMessage(context, "您输入的密码错误！");
            return false;
        }

        return true;
//		if(!SharedPreferencesHelper.getString(context, SharedPreferencesLifecycle.forever, "DeviceId").equals(this.getDeviceId())){
//
//			UiHelper.getInstance().showSubmitFailureDialog(context,"登录失败", "该账号已与其它设备绑定，\n故不能在此设备登录，\n如需解绑，请联系客服");
//			return false;
//        }
    }
}
