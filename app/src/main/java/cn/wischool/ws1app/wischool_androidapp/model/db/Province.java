package cn.wischool.ws1app.wischool_androidapp.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lihuanxing on 2015/10/13.
 */
@DatabaseTable(tableName = "Info_Province")
public class Province {
    @DatabaseField(id = true)
    private String ProId;

    @DatabaseField()
    private String ProName;

    public String getProId() {
        return ProId;
    }

    public void setProId(String proId) {
        ProId = proId;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }
}
