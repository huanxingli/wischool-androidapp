package cn.wischool.ws1app.wischool_androidapp.model.db;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.wischool.ws1app.wischool_androidapp.common.UiHelper;

/**
 * Created by Administrator on 2015/10/25.
 */
@DatabaseTable(tableName = "Info_User")
public class User {
    @DatabaseField
    private String appcode;

    @DatabaseField
    private String city;

    @DatabaseField
    private String clientid;

    @DatabaseField
    private int credit;

    @DatabaseField
    private String downtown;

    @DatabaseField
    private int honour;

    @DatabaseField
    private String id;

    @DatabaseField
    private int integration;

    @DatabaseField
    private String mac;

    @DatabaseField
    private String mood;

    @DatabaseField
    private String password;

    @DatabaseField
    private String phone;

    @DatabaseField
    private String pic;

    @DatabaseField
    private String province;

    @DatabaseField
    private String psw;

    @DatabaseField
    private String qq;

    @DatabaseField
    private String school;

    @DatabaseField
    private String sex;

    @DatabaseField
    private String showpic;

    @DatabaseField
    private int state;

    @DatabaseField
    private String stunum;

    @DatabaseField
    private String stupsw;

    @DatabaseField
    private String truename;

    @DatabaseField
    private String userid;

    @DatabaseField
    private String username;

    @DatabaseField
    private String wexin;

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDowntown() {
        return downtown;
    }

    public void setDowntown(String downtown) {
        this.downtown = downtown;
    }

    public int getHonour() {
        return honour;
    }

    public void setHonour(int honour) {
        this.honour = honour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShowpic() {
        return showpic;
    }

    public void setShowpic(String showpic) {
        this.showpic = showpic;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getStupsw() {
        return stupsw;
    }

    public void setStupsw(String stupsw) {
        this.stupsw = stupsw;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWexin() {
        return wexin;
    }

    public void setWexin(String wexin) {
        this.wexin = wexin;
    }

    public boolean veritf(Context context,String password){
        if (this.getState()==0){
            UiHelper.getInstance().toastMessage(context,"该用户不存在");
            UiHelper.getInstance().dialogMessage(context,"提示框","该用户不存在");
            return false;
        }
        if (!this.getPassword().equals(password)){
            UiHelper.getInstance().toastMessage(context,"用户密码不正确");
            UiHelper.getInstance().dialogMessage(context,"提示框","用户密码不正确");
            return false;
        }
        return true;
    }
}
