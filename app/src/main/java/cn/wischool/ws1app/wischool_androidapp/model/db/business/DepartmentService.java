package cn.wischool.ws1app.wischool_androidapp.model.db.business;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.model.db.Department;

/**
 * Created by Administrator on 2016/2/29.
 */
public class DepartmentService extends BaseService {

    private Dao<Department, Integer> departmentDao;

    @Inject
    public DepartmentService(Context context) {
        super(context);
        try {
            departmentDao = databaseHelper().getDepartmentDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id获得学院实体
     *
     * @param id
     * @return
     */
    public Department getDepartmentById(int id) {
        Department d = null;
        try {
            d = departmentDao.queryBuilder().where().eq("Id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }


    /**
     * 根据学校id获取该学校的学院
     * @param ColId
     * @return
     */
    public List<Department> getDepartmentsByColId(String ColId) {
        List<Department> list = new ArrayList<Department>();
        try {
            list = departmentDao.queryBuilder().where().eq("ColId",ColId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
