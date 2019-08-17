package com.example.zhuangguang.common.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/1/14  22:56
 * @desc ${数据库升级操作类}
 */
public class DBMigrationHelper {
    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'IDatabase MATCH WITH THE CURRENT PARAMETERS";

    public void onUpgrade(SQLiteDatabase db , Class<? extends AbstractDao<?,?>>... daoClasses){
        StandardDatabase standardDatabase = new StandardDatabase(db);
        generateTempTables(standardDatabase, daoClasses);//备份数据（表结构改变的表）
        dropAllTables(standardDatabase ,true , daoClasses);//删除旧表（结构修改/新增）
        createAllTables(standardDatabase,false,daoClasses);//创建 新表（结构修改/新增）
        restoreData(standardDatabase ,daoClasses);//恢复数据（结构修改）


    }

    private void restoreData(StandardDatabase standardDatabase, Class<? extends AbstractDao<?, ?>>[] daoClasses) {

    }

    private void createAllTables(StandardDatabase standardDatabase, boolean b, Class<? extends AbstractDao<?, ?>>[] daoClasses) {

    }

    private void dropAllTables(StandardDatabase standardDatabase, boolean b, Class<? extends AbstractDao<?,?>>[] daoClasses) {
    }


    private void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>[] daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String divider = "";
            String tablename = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            ArrayList<String> properties = new ArrayList<>();
            StringBuilder createTableStringBuilder = new StringBuilder();
            createTableStringBuilder.append("CREATE TABLE ").append(tempTableName).append(" (");

            for (int j = 0; j < daoConfig.properties.length; j++) {
                String columnName = daoConfig.properties[j].columnName;
                if (getColumns(db, tempTableName).contains(columnName)) {
                    properties.add(columnName);
                }
            }

            StringBuilder insertTableStringBuilder = new StringBuilder();

            insertTableStringBuilder.append("INSERT INTO ").append(tablename).append(" (");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");

            StringBuilder dropTableStringBuilder = new StringBuilder();

            //dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
            dropTableStringBuilder.append("DROP TABLE IF EXISTS ").append(tempTableName).append(";");// update by wragony on 2017-06-27
            db.execSQL(insertTableStringBuilder.toString());
            db.execSQL(dropTableStringBuilder.toString());


        }
    }

    private static List<String> getColumns(Database db ,String tableName){
        List<String> columns = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);
            if (cursor != null) {
                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return columns;

    }
}
