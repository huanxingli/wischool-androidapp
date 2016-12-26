package cn.wischool.ws1app.wischool_androidapp.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2015/10/13.
 */
@DatabaseTable(tableName = "Info_Department")
public class Department {
    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField()
    private String ColId;

    @DatabaseField()
    private String DepName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String depName) {
        DepName = depName;
    }

    public String getColId() {
        return ColId;
    }

    public void setColId(String colId) {
        ColId = colId;
    }
}
