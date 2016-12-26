package cn.wischool.ws1app.wischool_androidapp.model.db.business;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.model.db.College;

/**
 * Created by Administrator on 2016/2/28.
 */
public class CollegeService extends BaseService {

    private Dao<College,String> collegeDao;

    @Inject
    public CollegeService(Context context) {
        super(context);
        try {
            collegeDao = databaseHelper().getCollegeDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据省份的ID获取该省份的大学
     * @param id
     * @return
     */
    public List<College> getCollegesById(String id){
        List<College> list = new ArrayList<College>();
        try {
            list = collegeDao.queryBuilder().where().eq("ProId", id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据学校id获取该大学实体
     * @param id
     * @return
     */
    public College getCollegeById(String id){
        College college = null;
        try {
            college = collegeDao.queryBuilder().where().eq("ColId",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return college;
    }

    /**
     * 根据学校名称模糊查询
     * @param info
     * @return
     */
    public List<College> getCollegesByName(String info,String id){
        List<College> list = new ArrayList<College>();
//        String sql = "select *from Info_College where ColName like '%\"&info&\"%' ";
        String sql = "select *from Info_College where ProId='" + id +
                "' and ColName like '%" + info + "%'";

        try {
            GenericRawResults<College> results = collegeDao.queryRaw(sql, new RawRowMapper<College>() {
                @Override
                public College mapRow(String[] strings, String[] strings1) throws SQLException {
                    College college = new College();
                    college.setColId(strings1[0]);
                    college.setProId(strings1[1]);
                    college.setColName(strings1[2]);
                    return college;
                }
            });
            list = results.getResults();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
