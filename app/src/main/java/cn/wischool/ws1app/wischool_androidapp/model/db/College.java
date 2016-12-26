package cn.wischool.ws1app.wischool_androidapp.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lihuanxing on 2015/10/13.
 */
@DatabaseTable(tableName = "Info_College")
public class College {

    @DatabaseField(id = true)
    private String ColId;

    @DatabaseField()
    private String ProId;

    @DatabaseField()
    private String ColName;

    public String getColId() {
        return ColId;
    }

    public void setColId(String colId) {
        ColId = colId;
    }

    public String getProId() {
        return ProId;
    }

    public void setProId(String proId) {
        ProId = proId;
    }

    public String getColName() {
        return ColName;
    }

    public void setColName(String colName) {
        ColName = colName;
    }
}
