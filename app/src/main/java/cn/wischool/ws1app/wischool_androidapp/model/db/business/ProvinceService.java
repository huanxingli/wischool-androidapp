package cn.wischool.ws1app.wischool_androidapp.model.db.business;

import android.content.Context;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.RawResultsImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wischool.ws1app.wischool_androidapp.model.db.Province;

/**
 * Created by Administrator on 2016/2/28.
 */
public class ProvinceService extends BaseService {

    private Dao<Province, String> provinceDao;

    @Inject
    public ProvinceService(Context context) {
        super(context);
        try {
            provinceDao = databaseHelper().getProvinceDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有省份信息
     *
     * @return
     */
    public List<Province> getProvinces() {
        List<Province> list = new ArrayList<Province>();
        String sql = "select *from Info_Province";
        try {
            GenericRawResults<Province> results = provinceDao.queryRaw(sql,
                    new RawRowMapper<Province>() {
                        @Override
                        public Province mapRow(String[] columnNames, String[] resultColumns)
                                throws SQLException {
                            Province p = new Province();
                            p.setProId(resultColumns[0]);
                            p.setProName(resultColumns[1]);
                            return p;
                        }
                    });
            list = results.getResults();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据id获取省份信息
     *
     * @param id
     * @return
     */
    public Province getProvinceById(String id) {
        Province p = null;
        try {
            p = provinceDao.queryBuilder().where().eq("ProId", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }


    /**
     * 获取所有省份信息
     *
     * @return
     */
    public List<String> getProvince() {
        List<String> list = new ArrayList<String>();
        String sql = "select ProName from Info_Province";
        try {
            GenericRawResults<String> results = provinceDao.queryRaw(sql, new RawRowMapper<String>() {
                @Override
                public String mapRow(String[] strings, String[] strings1) throws SQLException {
                    String p = strings1[0];
                    return p;
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
