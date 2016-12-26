package cn.wischool.ws1app.wischool_androidapp.infobean;

/**
 * Created by Administrator on 2016/4/1.
 *
 * tab"我"中 "全部订单"、"积分和优惠"、"用户服务"
 * item,大标题,小标题的数据源
 */
public class PersonItemData {
    private int logoId;
    private String textBig;
    private String textSmall;

    public PersonItemData(int logoId, String textBig, String textSmall) {
        this.logoId = logoId;
        this.textBig = textBig;
        this.textSmall = textSmall;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }

    public String getTextSmall() {
        return textSmall;
    }

    public void setTextSmall(String textSmall) {
        this.textSmall = textSmall;
    }

    public String getTextBig() {
        return textBig;
    }

    public void setTextBig(String textBig) {
        this.textBig = textBig;
    }
}
