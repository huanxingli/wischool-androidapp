package cn.wischool.ws1app.wischool_androidapp.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesHelper;
import cn.wischool.ws1app.wischool_androidapp.common.SharedPreferencesLifecycle;
import cn.wischool.ws1app.wischool_androidapp.common.StringUtils;

/**
 * Created by sunyuhong on 15/10/1.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    public static final int DATABASE_VERSION = 1;

    private static DatabaseHelper mDbHelper;

    private static SQLiteDatabase db;

    private static Context mContext;

    private Dao<Account, String> accountDao = null;
    private Dao<User,String> userDao = null;
    private Dao<Province,String> provinceDao = null;
    private Dao<College,String> collegeDao = null;
    private Dao<Department,Integer> departmentDao = null;

    private DatabaseHelper(Context context, String dbName) {
        super(context, dbName, null, DATABASE_VERSION);
        //随便调用一个getXXXXDao方法查询下数据，以致调用 onOpen 方法，从而拿到SQLiteDatabase对象this.db，防止this.dp=null，直接调用 execSql卡死
//        try {
//            getAccountDao().countOf();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static DatabaseHelper getInstance(Context context, String dbName) {
        if (mDbHelper == null) {
            mContext=context;
            mDbHelper = new DatabaseHelper(context, dbName);
        }

        return mDbHelper;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.db = db;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        onCreateTable(sqLiteDatabase, connectionSource);
    }




    public void onCreateTable(SQLiteDatabase db, ConnectionSource connectionSource)
    {
        db.beginTransaction();
        try {
            List<String> sqls = DatabaseSQL.getInstallSql();
            long millis = System.currentTimeMillis();
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            for (String sql : sqls){
                db.execSQL(sql);
            }
            db.setTransactionSuccessful();
            SharedPreferencesHelper.putString(mContext, SharedPreferencesLifecycle.forever, "dbVersionUpgrade", String.valueOf(DATABASE_VERSION));
            Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            //throw new RuntimeException(e);
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        String dbVersionUpgrade= SharedPreferencesHelper.getString(mContext, SharedPreferencesLifecycle.forever, "dbVersionUpgrade");
        if (!StringUtils.isEmpty(dbVersionUpgrade)){
            onUpgradeTable(db, connectionSource, oldVersion, newVersion);
        }else {
            dropAllTable(db);
            onCreateTable(db, connectionSource);
        }
    }

    public void onUpgradeTable(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        db.beginTransaction();
        try {
            HashMap<Integer, List<String>> upgradeSql = DatabaseSQL.getUpgradeSql();
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            for (Map.Entry<Integer,List<String>> entry: upgradeSql.entrySet()){
                if (entry.getKey()>oldVersion){
                    for (String sql : entry.getValue()){
                        db.execSQL(sql);
                    }
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "Can't upgrade databases", e);

            //throw new RuntimeException(e);
        }finally {
            db.endTransaction();
        }
    }


    public void dropAllTable(SQLiteDatabase db)
    {
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
            ArrayList<String> dropTables = new ArrayList();
            while (cursor.moveToNext()) {
                //遍历出表名
                String name = cursor.getString(0);
                dropTables.add(name);
            }
            for (String tableName : dropTables) {
                db.execSQL(String.format("drop table %s ", tableName));
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);

            //throw new RuntimeException(e);
        }finally {
            db.endTransaction();
        }
    }

    public boolean execSql(String[] sqls) {
        boolean isSuccess = false;
        try {
            long a = System.currentTimeMillis();
            db.beginTransaction();

            for (String sql : sqls) {
                db.execSQL(sql);
            }
            db.setTransactionSuccessful();
            isSuccess = true;
            long b = System.currentTimeMillis();
            Log.i("execSql", String.valueOf(b - a));
        } catch (Exception e) {
            isSuccess = false;
            Log.e("execSql", e.getMessage());
        } finally {

            db.endTransaction();
        }

        return isSuccess;
    }


    public void execSql(String sql) {
        db.execSQL(sql);
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();


        accountDao = null;
        userDao = null;
        provinceDao = null;
        collegeDao = null;
        departmentDao = null;


        mDbHelper = null;//这个要放在最后清空
    }

    public Dao<Account, String> getAccountDao() throws SQLException {
        if (accountDao == null) {
            accountDao = getDao(Account.class);
        }
        return accountDao;
    }

    public Dao<User,String> getUserDao() throws SQLException{
        if (userDao == null){
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Province,String> getProvinceDao() throws SQLException{
        if (provinceDao == null){
            provinceDao = getDao(Province.class);
        }
        return  provinceDao;
    }

    public Dao<College,String> getCollegeDao() throws SQLException{
        if (collegeDao == null){
            collegeDao = getDao(College.class);
        }
        return  collegeDao;
    }

    public Dao<Department,Integer> getDepartmentDao() throws SQLException{
        if (departmentDao == null){
            departmentDao = getDao(Department.class);
        }
        return departmentDao;
    }

}
