package com.jiang.dlj.iris;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqliteDataBase extends SQLiteOpenHelper {

    // 数据库名、表名
    private static final String DATABASE_NAME = "DLJEye.db";
    private static final String TABLE_NAME_USER_DATA = "user_data";
    // 数据库初始版本
    private static final int VERSION = 1;

    // 列名
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_UID = "uid";
    private static final String COLUMN_NAME_USER_FAVICON = "user_favicon";
    private static final String COLUMN_NAME_USER_NAME = "user_name";
    private static final String COLUMN_NAME_LEFT_FEATURE = "left_feature";
    private static final String COLUMN_NAME_RIGHT_FEATURE = "right_feature";
    private static final String COLUMN_NAME_LEFT_FEATURE_COUNT = "left_feature_count";
    private static final String COLUMN_NAME_RIGHT_FEATURE_COUNT = "right_feature_count";
    private static final String COLUMN_NAME_ENCROLL_TIME = "enroll_dateTime";

    // 创建用户表sql语句
    private static final String CREATE_USER_DATA_TABLE = "CREATE TABLE " + "if not exists "
            + TABLE_NAME_USER_DATA + " ("
            + COLUMN_NAME_ID + " integer primary key autoincrement,"
            + COLUMN_NAME_UID + " varchar(30), "
            + COLUMN_NAME_USER_FAVICON + " INTEGER, "
            + COLUMN_NAME_USER_NAME + " varchar(30), "
            + COLUMN_NAME_LEFT_FEATURE + " TEXT, "
            + COLUMN_NAME_RIGHT_FEATURE + " TEXT, "
            + COLUMN_NAME_LEFT_FEATURE_COUNT + " INTEGER, "
            + COLUMN_NAME_RIGHT_FEATURE_COUNT + " INTEGER, "
            + COLUMN_NAME_ENCROLL_TIME + " varchar(30))";

    // 删除用户表sql语句
    private static final String DROP_TABLE_NAME_USER_DATA = "drop table if exists "
            + TABLE_NAME_USER_DATA;

    private static SqliteDataBase sInstance;

    public static synchronized SqliteDataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SqliteDataBase(context.getApplicationContext());
        }
        return sInstance;
    }

    private SqliteDataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_NAME_USER_DATA);
        onCreate(db);
    }

    /**
     * 根据注册用户实体插入注册用户信息
     *
     * @param user_info
     */
    public void insertUserData(IrisUserInfo user_info) {
        if (user_info == null) {
            throw new IllegalArgumentException("user_info is null");
        }

        SQLiteDatabase db = sInstance.getWritableDatabase();
        final String insertSql = "insert into "
                + TABLE_NAME_USER_DATA + " ("
                + COLUMN_NAME_UID + ", "
                + COLUMN_NAME_USER_FAVICON + ", "
                + COLUMN_NAME_USER_NAME + ", "
                + COLUMN_NAME_LEFT_FEATURE + ", "
                + COLUMN_NAME_RIGHT_FEATURE + ", "
                + COLUMN_NAME_LEFT_FEATURE_COUNT + ", "
                + COLUMN_NAME_RIGHT_FEATURE_COUNT + ", "
                + COLUMN_NAME_ENCROLL_TIME + ") values(?,?,?,?,?,?,?,?)";
        Object[] args = new Object[]{user_info.m_Uid, user_info.m_UserFavicon, user_info.m_UserName,
                user_info.m_LeftTemplate, user_info.m_RightTemplate, user_info.m_LeftTemplate_Count,
                user_info.m_RightTemplate_Count, user_info.m_EnrollTime};
        db.execSQL(insertSql, args);
        db.close();
    }

    public void removeAll() {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String deleteSql = "delete from " + TABLE_NAME_USER_DATA;
//        Object[] args = new Object[]{};
        db.execSQL(deleteSql);
        db.close();
    }

    public void removeFirstUser() {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String sql = "delete from " + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_ID +
                " = (select min(" + COLUMN_NAME_ID + ") from " + TABLE_NAME_USER_DATA + ")";
        db.execSQL(sql);
        db.close();
    }

    /**
     * 根据uid删除一条记录
     *
     * @param uid
     */
    public void removeByUid(String uid) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String deleteSql = "delete from " + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_UID + " = ?";
        Object[] args = new Object[]{uid};
        db.execSQL(deleteSql, args);
        db.close();
    }

    /**
     * 根据用户名删除一条记录
     *
     * @param name
     */
    public void removeByName(String name) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String deleteSql = "delete from " + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_USER_NAME + " = ?";
        Object[] args = new Object[]{name};
        db.execSQL(deleteSql, args);
        db.close();
    }

    /**
     * 根据用户头像删除一条记录
     *
     * @param order
     */
    public void removeByFavicon(int order) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String deleteSql = "delete from " + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_USER_FAVICON + " = ?";
        Object[] args = new Object[]{order + ""};
        db.execSQL(deleteSql, args);
        db.close();
    }

    /**
     * 根据用户头像更新数据库操作
     *
     * @param order
     */
    public void updateNameByFavicon(String name, int order) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        String updateSql = "update " + TABLE_NAME_USER_DATA + " set "
                + COLUMN_NAME_USER_NAME + " = ?" + " where "
                + COLUMN_NAME_USER_FAVICON + " = " + order;

        Object[] args = new Object[]{name};

        db.execSQL(updateSql, args);
        db.close();
    }


    /**
     * 根据用户头像查询对应的用户名字
     *
     * @param order
     * @return 返回查询到的人名
     */
    public String queryNameByFavicon(int order) {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        final String querySql = "select " + COLUMN_NAME_USER_NAME + " from "
                + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_USER_FAVICON + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{order + ""});

        if (cursor == null) {
            throw new IllegalArgumentException("cursor is null");
        }

        String userName = null;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                userName = cursor.getString(cursor
                        .getColumnIndex(COLUMN_NAME_USER_NAME));
            }
            cursor.close();
            db.close();
        }
        return userName;
    }

    /**
     * 根据用户名字查询对应的用户头像
     *
     * @param name
     * @return 返回查询到的人名
     */
    public int queryFaviconByName(String name) {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        final String querySql = "select " + COLUMN_NAME_USER_FAVICON + " from "
                + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_USER_NAME + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{name});

        if (cursor == null) {
            throw new IllegalArgumentException("cursor is null");
        }

        int order = 0;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                order = cursor.getInt(cursor
                        .getColumnIndex(COLUMN_NAME_USER_FAVICON));
            }
            cursor.close();
            db.close();
        }
        return order;
    }

    /**
     * 根据左眼或者右眼标志获取虹膜特征信息
     *
     * @param leftTemplate
     * @param rightTemplate
     * @return 返回查询到的虹膜特征
     */
    public int queryOrderByTemplate(byte[] leftTemplate, byte[] rightTemplate) {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        final String querySql = "select " + COLUMN_NAME_USER_FAVICON + " from "
                + TABLE_NAME_USER_DATA + " where " + COLUMN_NAME_LEFT_FEATURE + " =? and " + COLUMN_NAME_RIGHT_FEATURE + " =?";
        Cursor cursor = db.rawQuery(querySql, new String[]{leftTemplate + "", rightTemplate + ""});

        if (cursor == null) {
            throw new IllegalArgumentException("cursor is null");
        }

        int order = 0;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                order = cursor.getInt(cursor
                        .getColumnIndex(COLUMN_NAME_USER_FAVICON));
            }
            cursor.close();
            db.close();
        }
        return order;
    }

    /**
     * 根据左眼标志获取虹膜特征信息集合
     *
     * @return 返回查询到的虹膜特征信息集合
     */
    public List<IrisUserInfo> queryLeftFeature() {
        List<IrisUserInfo> temp = this.queryAll();
        List<IrisUserInfo> userArray = new ArrayList<>();

        for (IrisUserInfo userInfo : temp) {
            if (userInfo.m_LeftTemplate_Count > 0) {
                userArray.add(userInfo);
            }
        }

        return userArray;
    }


    /**
     * 根据右眼标志获取虹膜特征信息集合
     *
     * @return 返回查询到的虹膜特征信息集合
     */
    public List<IrisUserInfo> queryRightFeature() {

        List<IrisUserInfo> temp = this.queryAll();
        List<IrisUserInfo> userArray = new ArrayList<>();

        for (IrisUserInfo userInfo : temp) {
            if (userInfo.m_RightTemplate_Count > 0) {
                userArray.add(userInfo);
            }
        }

        return userArray;
    }


    /**
     * 查询全部用户实体
     *
     * @return 返回查询到的用户实体集合
     */
    public List<IrisUserInfo> queryAll() {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        final String querySql = "select * from " + TABLE_NAME_USER_DATA;
        Cursor cursor = db.rawQuery(querySql, null);

        if (cursor == null) {
            throw new IllegalArgumentException("cursor is null");
        }

        List<IrisUserInfo> userArray = new ArrayList<>();

        IrisUserInfo user_info = null;
        while (cursor.moveToNext()) {
            user_info = new IrisUserInfo();
            user_info.m_Id = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID));
            user_info.m_Uid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_UID));
            user_info.m_UserFavicon = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_USER_FAVICON));
            user_info.m_UserName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_NAME));
            user_info.m_LeftTemplate = cursor.getBlob(cursor.getColumnIndex(COLUMN_NAME_LEFT_FEATURE));
            user_info.m_RightTemplate = cursor.getBlob(cursor.getColumnIndex(COLUMN_NAME_RIGHT_FEATURE));
            user_info.m_LeftTemplate_Count = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_LEFT_FEATURE_COUNT));
            user_info.m_RightTemplate_Count = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RIGHT_FEATURE_COUNT));
            user_info.m_EnrollTime = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ENCROLL_TIME));
            userArray.add(user_info);
        }
        cursor.close();
        db.close();

        return userArray;
    }

    public int getUserCount() {

        SQLiteDatabase db = sInstance.getReadableDatabase();

        final String querySql = "select count(*) from " + TABLE_NAME_USER_DATA;
        Cursor cursor = db.rawQuery(querySql, null);

        int result = 0;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
            cursor.close();
            db.close();
        }
        return result;
    }

    /**
     * 查询全部用户实体
     *
     * @return 返回查询到的用户实体集合
     */
    public boolean queryAllResult() {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        final String querySql = "select * from " + TABLE_NAME_USER_DATA;
        Cursor cursor = db.rawQuery(querySql, null);

        if (cursor.getCount() == 0) {
            return false;
        }

        return true;
    }

}
